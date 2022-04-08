package uz.pdp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.payload.LoginDTO;
import uz.pdp.security.JwtProvider;
import uz.pdp.service.AuthService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    JwtProvider jwtProvider;


    @PostMapping("login")
    public HttpEntity<?> login(@RequestBody LoginDTO dto) {
        String token = jwtProvider.generateToken(dto.getUserName());
        return ResponseEntity.ok().body(token);
    }




}
