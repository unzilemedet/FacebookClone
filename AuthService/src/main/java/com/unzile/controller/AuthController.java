package com.unzile.controller;

import com.unzile.dto.request.LoginRequestDto;
import com.unzile.dto.request.RegisterRequestDto;
import com.unzile.dto.response.LoginResponseDto;
import com.unzile.dto.response.RegisterResponseDto;
import com.unzile.exception.AuthException;
import com.unzile.exception.ErrorType;
import com.unzile.rabbitmq.producer.CreateUserProducer;
import com.unzile.repository.entity.Auth;
import com.unzile.service.AuthService;
import com.unzile.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    //private final TokenCreator tokenCreator;

    private final JwtTokenManager jwtTokenManager;


    @GetMapping("/getpage")
    public ResponseEntity<String> getPage(){
        return ResponseEntity.ok("Auth Service e ulaştınız");
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto dto){
        Optional<Auth> auth = authService.doLogin(dto);
        if(auth.isEmpty())
            return ResponseEntity.ok(LoginResponseDto.builder()
                            .statusCode(4000)
                            .message("Kullanıcı adı veya şifre hatalı")
                    .build());

        return ResponseEntity.ok(LoginResponseDto.builder()
                        .statusCode(2001)
                        .message(jwtTokenManager.createToken(auth.get().getId()).get())
                .build());
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        if(!dto.getPassword().equals(dto.getRepassowrd()))
            throw new AuthException(ErrorType.ERROR_PASSWORD);
        authService.register(dto);
        return ResponseEntity.ok(RegisterResponseDto.builder()
                        .statusCode(2000)
                        .message("Kayıt işlemi baraşıyla şekilde gerçekleşti. Lütfen E-Posta" +
                                " adresinize gelen aktivasyon linkine tıklayınız.")
                .build());
    }

}
