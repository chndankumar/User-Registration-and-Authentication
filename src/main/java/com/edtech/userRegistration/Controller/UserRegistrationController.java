package com.edtech.userRegistration.Controller;

import com.edtech.userRegistration.Dto.OtpVerificationRequest;
import com.edtech.userRegistration.Dto.ResponseDto;
import com.edtech.userRegistration.Dto.UserDto;
import com.edtech.userRegistration.Dto.UserLoginDto;
import com.edtech.userRegistration.Service.UserRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User Registration", description = "APIs for User Registration and Authentication")
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @Operation(summary = "Register User", description = "Register a new user and send an OTP for verification.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OTP sent successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerUser(@RequestBody UserDto userDto) {
        userRegistrationService.registerUser(userDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto("200", "OTP sent to your email. Please verify within 5 minutes."));
    }

    @Operation(summary = "Verify OTP", description = "Verify OTP and complete user registration.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registration successful"),
            @ApiResponse(responseCode = "400", description = "Invalid OTP or request body", content = @Content)
    })
    @PostMapping("/verifyOtp")
    public ResponseEntity<ResponseDto> verifyOtp(@RequestBody OtpVerificationRequest request) {
        boolean result = userRegistrationService.verifyOtpAndRegister(request);
        String message = result ? "Registration successful. You can now login." : "OTP not matched.";
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", message));
    }

    @Operation(summary = "Login User", description = "Authenticate a user and return a token.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        String token = userRegistrationService.userLogin(userLoginDto);
        if (token != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto("200", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDto("401", "Password does not match."));
        }
    }

    @Operation(summary = "Logout User", description = "Invalidate a user's session by logging them out.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Logout successful"),
            @ApiResponse(responseCode = "400", description = "Invalid authorization header", content = @Content)
    })
    @PostMapping("/logout")
    public ResponseEntity<ResponseDto> logout(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        userRegistrationService.logout(token);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto("200", "Successfully logged out."));
    }
}
