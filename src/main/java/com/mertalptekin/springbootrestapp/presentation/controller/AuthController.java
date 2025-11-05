package com.mertalptekin.springbootrestapp.presentation.controller;


import com.mertalptekin.springbootrestapp.application.auth.RegisterRequest;
import com.mertalptekin.springbootrestapp.application.auth.TokenRequest;
import com.mertalptekin.springbootrestapp.application.auth.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Kullanıcı oluşturma token üretme, token refresh etme ve benzeri işlemler burada yapılacak.
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


    @PostMapping("token")
    public ResponseEntity<TokenResponse> token(@RequestBody TokenRequest request){
        return ResponseEntity.ok().build();
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){

        // Kullanıcı veritabanın kaydı

        return ResponseEntity.ok().build();
    }

}
