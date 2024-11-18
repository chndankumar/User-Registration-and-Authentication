package com.edtech.userRegistration.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender javaMailSender;
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String OTP_KEY_PREFIX = "otp:";
    private static final String USER_KEY_PREFIX = "user:";

    public String generateOtp() {
        int otp = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(otp);
    }

    @Async
    public void sendOtpEmail(String email) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try{
            String otp = generateOtp();

            redisTemplate.opsForValue().set(OTP_KEY_PREFIX + email, otp, 300, TimeUnit.SECONDS);

            log.info("OTP successfully saved to redis with email: {}", email);

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(email);
            helper.setSubject("Your OTP for Registration");
            helper.setText("<p>Hello,</p><p>Your OTP for registration is: <b>" + otp + "</b></p>", true);

            javaMailSender.send(mimeMessage);
            log.info("OTP email sent successfully to {}", email);

        } catch (MessagingException e) {
            log.error("Failed to send OTP email to {}", email, e);
            throw new RuntimeException(e);
        }

    }

}
