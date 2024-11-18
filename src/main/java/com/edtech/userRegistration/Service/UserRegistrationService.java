package com.edtech.userRegistration.Service;

import com.edtech.userRegistration.Dto.OtpVerificationRequest;
import com.edtech.userRegistration.Dto.UserDto;
import com.edtech.userRegistration.Dto.UserLoginDto;
import com.edtech.userRegistration.Entity.Users;
import com.edtech.userRegistration.Exception.UserAlreadyExistsException;
import com.edtech.userRegistration.Mapper.UserMapper;
import com.edtech.userRegistration.Repository.UserRegistrationRepo;
import com.edtech.userRegistration.Service.JwtService.JwtBlacklistService;
import com.edtech.userRegistration.Service.JwtService.JwtTokenGenerator;
import com.edtech.userRegistration.Service.JwtService.JwtTokenValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j
public class UserRegistrationService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final UserRegistrationRepo userRegistrationRepo;
    private final AuthenticationManager authManager;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtBlacklistService jwtBlacklistService;
    private final JwtTokenValidator jwtTokenValidator;
    private final JwtTokenGenerator jwtTokenGenerator;

    private static final String OTP_KEY_PREFIX = "otp:";
    private static final String USER_KEY_PREFIX = "user:";

    public void registerUser(UserDto userDto) {

        Optional<Users> optionalUser = userRegistrationRepo.findByEmail(userDto.getEmail());

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("Email already registered with given mail" + userDto.getEmail());
        }

        String email = userDto.getEmail();
        redisTemplate.opsForValue().set(USER_KEY_PREFIX + email, userDto, 60, TimeUnit.MINUTES);

        mailService.sendOtpEmail(email);
        log.info("OTP email sent successfully to {}", email);
    }

    public String userLogin(UserLoginDto userLoginDto){
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                                userLoginDto.getEmail(),
                                userLoginDto.getPassord()));

        if(authentication.isAuthenticated()){
            return jwtTokenGenerator.generateToken(userLoginDto.getEmail());
        }else{
            return "null";
        }
    }

    public void logout(String token) {
        long expiration = jwtTokenValidator.extractExpiration(token).getTime() - System.currentTimeMillis();
        jwtBlacklistService.blacklistToken(token, expiration);
    }

    public boolean verifyOtpAndRegister(OtpVerificationRequest otpVerificationRequest) {

        if (!StringUtils.hasText(otpVerificationRequest.getOtp())) {
            throw new IllegalArgumentException("OTP cannot be null or empty");
        }
        String email = otpVerificationRequest.getEmail();
        String otp = otpVerificationRequest.getOtp();

        String otpKey = OTP_KEY_PREFIX + email;
        String storedOtp = (String) redisTemplate.opsForValue().get(otpKey);

        try {
            if (storedOtp != null && storedOtp.equals(otp)) {
                UserDto userDto = (UserDto) redisTemplate.opsForValue().get(USER_KEY_PREFIX + email);
                completeRegistration(userDto);

                redisTemplate.delete(USER_KEY_PREFIX + email);
                redisTemplate.delete(otpKey);
                log.info("OTP verified and registration completed for email: {}", email);
                return true;
            } else {
                log.warn("Invalid OTP for email: {}", email);
                return false;
            }
        } catch (DataAccessException e) {
            log.error("Failed to access OTP in Redis for email: {}", email, e);
            throw new ServiceException("An error occurred while verifying OTP. Please try again.");
        }

    }

    private void completeRegistration(UserDto userDto) {

         if (userDto != null) {
                Users user = UserMapper.mapToUser(userDto, new Users());
                userRegistrationRepo.save(user);
                log.info("User successfully registered with email: {}", user.getEmail());
         }else {
                log.error("UserDto is null for registration");
                throw new ServiceException("Registration data is incomplete. Please try re-registering.");
         }
    }

}
