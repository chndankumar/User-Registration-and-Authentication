package com.edtech.userRegistration.Service;

import com.edtech.userRegistration.Entity.Users;
import com.edtech.userRegistration.Repository.UserRegistrationRepo;
import com.edtech.userRegistration.Service.JwtService.JwtBlacklistService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PasswordResetService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final MailService mailService;
    private final UserRegistrationRepo userRegistrationRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtBlacklistService jwtBlacklistService;

    private static final String OTP_PREFIX = "otp:";

    public void sendForgotPasswordOtp(String email) {
        Users user = userRegistrationRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        mailService.sendOtpEmail(email);
    }

    public void verifyOtpAndResetPassword(String email, String otp, String newPassword) {
        String storedOtp = (String) redisTemplate.opsForValue().get(OTP_PREFIX + email);
        if (storedOtp == null || !storedOtp.equals(otp)) {
            throw new IllegalArgumentException("Invalid or expired OTP");
        }

        Users user = userRegistrationRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRegistrationRepo.save(user);

        redisTemplate.delete(OTP_PREFIX + email);

        jwtBlacklistService.revokeAllTokensForUser(user.getName());

    }
}
