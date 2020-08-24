package com.garwan.eshop.config;

import com.garwan.eshop.model.EshopUser;
import com.garwan.eshop.service.JwtTokenUtil;
import com.garwan.eshop.service.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    public JwtRequestFilter(JwtUserDetailsService jwtUserDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");

        String jwtToken = extractJwtToken(requestTokenHeader);
        String username = jwtToken != null ? getUsernameFromToken(jwtToken) : null;

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            EshopUser userDetails = jwtUserDetailsService.loadUserByUsername(username);
            validateTokenAndSetAuthentication(jwtToken, userDetails, request);
        }
        chain.doFilter(request, response);
    }

    private void validateTokenAndSetAuthentication(
        String jwtToken,
        EshopUser userDetails,
        HttpServletRequest request
    ) {
        if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

    private String getUsernameFromToken(String jwtToken) {
        try {
            return jwtTokenUtil.getUsernameFromToken(jwtToken);
        } catch (IllegalArgumentException e) {
            logger.warn("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
            logger.warn("JWT Token has expired");
        }
        return null;
    }

    private String extractJwtToken(String requestTokenHeader) {
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            return requestTokenHeader.substring(7);
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
            return null;
        }
    }
}
