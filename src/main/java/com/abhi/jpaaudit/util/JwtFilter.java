package com.abhi.jpaaudit.util;

import com.abhi.jpaaudit.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
         final String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
         if(!StringUtils.isEmpty(authorizationHeader) && authorizationHeader.startsWith("Bearer ")){
             jwtToken = authorizationHeader.substring(7);
             username = jwtUtil.extractUserName(jwtToken);
         }
        //Spring Security will do all this work, but here we are doing for every request and setting the SecurityContextHolder
         if(!StringUtils.isEmpty(username) && SecurityContextHolder.getContext().getAuthentication()==null){
             UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
             if(jwtUtil.validateToken(jwtToken, userDetails)){
                 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                 usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                 SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
             }
         }
         //handing the control to next chain
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
