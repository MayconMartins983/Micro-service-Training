package com.example.controleservice.config.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.springframework.util.StringUtils.hasText;

@Log4j2
public class AutheticationJwtFilter extends OncePerRequestFilter {

    //Essa classe tem como objetivo interceptar todas as requisições e verificar se o token ta valido e etc, ou seja faz este filtro

    @Autowired
    private JwtProvider jwtProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String tokenFromRequest = getTokenHeader(request); //Pega token do header
            boolean tokenIsValid = jwtProvider.validateJwt(tokenFromRequest);

            if (isNotBlank(tokenFromRequest) && tokenIsValid) {
                String userId = jwtProvider.getUserIdFromTokenJwt(tokenFromRequest); //Pega id do usuario do token
                String roles = jwtProvider.getClaimNameJwt(tokenFromRequest, "roles"); //Pega role pelo Claim customizada do token
                UserDetails userDetails = UserDetailsImpl.buildUserDetails(UUID.fromString(userId), roles);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Cannot set User Authentication: {}", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenHeader(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}
