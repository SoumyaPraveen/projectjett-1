package com.example.projectjett.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.projectjett.models.Student;
import com.example.projectjett.repositories.StudentRepository;
import com.example.projectjett.services.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private StudentRepository studentReposirtory;
    private final JwtUtil jwtUtil;
  
    @Autowired
    private CustomUserDetailsService userDetailsService;
    // Inject UserDetailsService
    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                  HttpServletResponse response, 
                                  FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        
        // Skip if no Bearer token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        
        try {
            String userEmail = jwtUtil.extractUsername(token);
            System.out.println("=== DEBUG START ===");
            System.out.println("Raw email from token: '" + userEmail + "'");
            System.out.println("Trimmed email: '" + userEmail.trim() + "'");

            // Test repository lookup directly
            Student user = studentReposirtory.findByEmail(userEmail.trim());
            System.out.println("User found in DB: " + user.getId());

            if (user!=null) {
                System.out.println("User status - Active: " + user.isActive());
            } else {
                System.out.println("Available emails in DB: " + 
                		studentReposirtory.findAll().stream()
                        .map(Student::getEmail)
                        .collect(Collectors.toList()));
            }
            System.out.println("=== DEBUG END ===");
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Load UserDetails (including roles)
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                
                // Validate token + UserDetails
                if (jwtUtil.validateToken(token, userDetails)) {
                    // Create full authentication with authorities
                    UsernamePasswordAuthenticationToken authToken = 
                        new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities() // Include real authorities
                        );
                    authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid/Expired JWT");
            return;
        }
        
        filterChain.doFilter(request, response);
    }
}

