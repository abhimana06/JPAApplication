package com.abhi.jpaaudit.controller;

import com.abhi.jpaaudit.model.AuthenitcateRequest;
import com.abhi.jpaaudit.model.AuthenticateResponse;
import com.abhi.jpaaudit.model.MyUserDetails;
import com.abhi.jpaaudit.service.MyUserDetailService;
import com.abhi.jpaaudit.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private  JwtUtil jwtUtil;


    @PostMapping("authenticate")
    public ResponseEntity<AuthenticateResponse> createAuthenticationToken(@RequestBody AuthenitcateRequest authenitcateRequest) throws Exception{
        //first Authenticate User by using authenticationManager
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenitcateRequest.getUserName(), authenitcateRequest.getPassword())
            );
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Username or Password is Wrong");
        }
        //after authentication generate Token
        final UserDetails userDetails = myUserDetailService.loadUserByUsername(authenitcateRequest.getUserName());
        final String jwtToken = jwtUtil.generateToken(userDetails);
        return  ResponseEntity.ok(new AuthenticateResponse(jwtToken));
    }
}
