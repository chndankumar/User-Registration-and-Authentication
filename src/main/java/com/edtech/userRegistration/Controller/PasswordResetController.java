package com.edtech.userRegistration.Controller;

import com.edtech.userRegistration.Dto.ResponseDto;
import com.edtech.userRegistration.Service.PasswordResetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/password")
@Tag(name = "Password Management", description = "APIs for Password Reset and Management")
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @Operation(summary = "Forgot Password", description = "Send an OTP to the user's email for password reset.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OTP sent successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid email format", content = @Content)
    })
    @PostMapping("/forgot-password")
    public ResponseEntity<ResponseDto> forgotPassword(@RequestParam String email) {
        passwordResetService.sendForgotPasswordOtp(email);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "OTP sent to email"));
    }

    @Operation(summary = "Reset Password", description = "Verify OTP and reset the user's password.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Password reset successful"),
            @ApiResponse(responseCode = "400", description = "Invalid OTP or email", content = @Content)
    })
    @PostMapping("/reset-password")
    public ResponseEntity<ResponseDto> resetPassword(
            @RequestParam String email,
            @RequestParam String otp,
            @RequestParam String newPassword) {
        passwordResetService.verifyOtpAndResetPassword(email, otp, newPassword);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "Password reset successful, all sessions invalidated"));
    }
}
