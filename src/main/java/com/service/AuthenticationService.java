package com.service;

import com.configs.jwt.JwtTokenUtil;
import com.entity.CustomUserDetails;
import com.entity.JwtRequest;
import com.entity.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {
    @Autowired
    UsersService usersService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    AuthenticationManager authenticationManager;

    public JwtResponse authenticate(JwtRequest jwtRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
            final CustomUserDetails userDetails= usersService.loadUserByUsername(jwtRequest.getUsername());
            final String token= jwtTokenUtil.generateToken(userDetails);
            return new JwtResponse(token,userDetails.getAuthorities().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
