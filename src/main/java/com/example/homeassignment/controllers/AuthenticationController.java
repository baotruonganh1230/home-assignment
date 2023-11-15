package com.example.homeassignment.controllers;

import com.example.homeassignment.config.JWTTokenHelper;
import com.example.homeassignment.requests.AuthenticationRequest;
import com.example.homeassignment.responses.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
//@RequestMapping("/api/v1")
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws InvalidKeySpecException, NoSuchAlgorithmException {

        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        logger.info("Authentication name is: " + authentication.getName());

        String jwtToken=jwtTokenHelper.generateToken(authentication.getName());

        LoginResponse loginResponse=new LoginResponse();
        loginResponse.setToken(jwtToken);

        Cookie cookie = new Cookie("jwt_token", jwtToken);
        cookie.setMaxAge(720);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/"); // global cookie accessible every where

        //add cookie to response
        response.addCookie(cookie);
        response.addHeader("Body", "username=" + authenticationRequest.getUsername() +
                "; password=" + authenticationRequest.getPassword());

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

}
