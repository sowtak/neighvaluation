package com.zova0.neighvaluation.controller;

import com.zova0.neighvaluation.dto.LoginRequest;
import com.zova0.neighvaluation.dto.RegisterRequest;
import com.zova0.neighvaluation.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody RegisterRequest registerRequest) {
        authenticationService.signUp(registerRequest);
        return new ResponseEntity<>("ユーザー登録が完了しました。", HttpStatus.OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authenticationService.verifyAccount(token);
        return new ResponseEntity<>("アカウントが有効化されました", HttpStatus.OK);
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest) {
        authenticationService.login(loginRequest);
    }
}
