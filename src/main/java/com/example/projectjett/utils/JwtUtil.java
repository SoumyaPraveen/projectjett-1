package com.example.projectjett.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "your-secret-key";  // You should change this in production
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    // Generate JWT Token without encryption (just signing)
    public String generateToken(String username,String studentId) {
        return JWT.create()
                .withSubject(username) // This could be the user's email or ID
                .withClaim("studentId", studentId)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY)); // HMAC256 signing, no encryption
    }

    // Validate JWT Token
    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET_KEY)) // Check the signing algorithm
                .build()
                .verify(token); // If verification passes, the token is valid
            return true;
        } catch (Exception e) {
            return false; // Return false if the token is invalid or expired
        }
    }
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username != null && 
                username.equals(userDetails.getUsername()) && 
                validateToken(token)); // Calls the basic validation
    }

    // Extract username (or other claims) from the JWT
    public String extractUsername(String token) {
        System.out.println("Token claims: " + JWT.decode(token).getSubject());

        return JWT.decode(token).getSubject();
    }
    public static boolean isTokenExpired(DecodedJWT decodedJWT) {
        Date expiration = decodedJWT.getExpiresAt();
        return expiration.before(new Date());
    }
    public  DecodedJWT verifyToken(String token) throws JWTVerificationException {
        // Create a verifier based on the secret key
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                                  .build();
        
        // Decode and verify the token
        return verifier.verify(token);
    }
    public String extractStudentId(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("studentId").asString(); // Extract studentId claim
    }

    // Method to extract user info from token (Example: email, userId)
    public  String extractUserInfo(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject(); // Extracts the subject (e.g., username or email)
    }
    
    
}






// middle ware
//auth

