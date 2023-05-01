package com.minh.foodmng.config;

import com.minh.foodmng.user.User;
import com.minh.foodmng.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    final private UserDetailsService userDetailsService;
    final private UserRepository userRepository;
    final private jwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authentication");
        final String id;
        final String jwToken;

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwToken = authHeader.substring(7);
        if (!jwtUtil.validateToken(jwToken)) {
            filterChain.doFilter(request, response);
            return;
        }
        id = jwtUtil.extractId(jwToken);
        User u = userRepository.findById(UUID.fromString(id)).get();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
        filterChain.doFilter(request, response);
    }
}
