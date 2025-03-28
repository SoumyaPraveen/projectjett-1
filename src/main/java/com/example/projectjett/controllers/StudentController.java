package com.example.projectjett.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.projectjett.dto.StudentDTO;
import com.example.projectjett.exceptions.StudentValidationException;
import com.example.projectjett.services.StudentService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")

public class StudentController {

	@Autowired
	StudentService studentService;
	
@PostMapping("/registerstudent")
public ResponseEntity<String> saveStudent(@RequestBody StudentDTO studentDTO) throws StudentValidationException {
	studentService.saveStudent(studentDTO);
    return ResponseEntity.ok("Student registered successfully!");
}

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody StudentDTO stud) {
	System.out.println("print");
	try {
	    String token = studentService.loginStudent(stud.getEmail(), stud.getPassword()); // Validate password and generate token
	    return ResponseEntity.ok(token); // Return the JWT token
	}
	 catch (RuntimeException e) {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
     }

}


@PostMapping("/studentforgotpassword")
public ResponseEntity<?> studentForgotPassword(@RequestBody StudentDTO stud) {
	System.out.println("print");
	try {
	    String token = studentService.forgotPwdStudent(stud.getEmail()); // Validate password and generate token
	    return ResponseEntity.ok("Reset link sent to email");
	}
	 catch (RuntimeException e) {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
     }
	
}

@PostMapping("/studentresetpassword")
public ResponseEntity<?> studentResetPassword(@RequestBody StudentDTO stud) {
	System.out.println("print");
	try {
	 studentService.resetPassword(stud.getResetToken(),stud.getPassword()); // Validate password and generate token
	    return ResponseEntity.ok("Reset link sent to email");
	}
 catch (JWTVerificationException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
} catch (Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
}
	
}
}