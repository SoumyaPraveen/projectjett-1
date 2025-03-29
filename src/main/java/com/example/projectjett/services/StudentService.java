package com.example.projectjett.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.projectjett.dto.StudentDTO;
import com.example.projectjett.exceptions.StudentValidationException;
import com.example.projectjett.models.Student;
import com.example.projectjett.repositories.StudentRepository;
import com.example.projectjett.utils.JwtUtil;

import org.springframework.util.StringUtils;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private JwtUtil jwtutil;

	@Autowired
	private EmailService emailService;

	public void validateStudent(Student student) throws StudentValidationException {
		System.out.println(student.getContact());
		if (studentRepository.existsByContact(student.getContact())) {
			throw new StudentValidationException("Contact number already exists");
		}

		if (studentRepository.existsByEmail(student.getEmail())) {
			throw new StudentValidationException("Email already exists");
		}

	}

	private void validateMandatoryFields(Student student) throws StudentValidationException {
		if (!StringUtils.hasText(student.getName())) {
			throw new StudentValidationException("Name is required");
		}
		if (!StringUtils.hasText(student.getContact())) {
			throw new StudentValidationException("Contact number is required");
		}
		if (!StringUtils.hasText(student.getEmail())) {
			throw new StudentValidationException("Email is required");
		}
		if (!StringUtils.hasText(student.getCourse())) {
			throw new StudentValidationException("Course is required");
		}
		if (!StringUtils.hasText(student.getPassword())) {
			throw new StudentValidationException("Password is required");
		}
		if (!StringUtils.hasText(student.getCollege())) {
			throw new StudentValidationException("College Name is required");
		}
	}

	public Student saveStudent(StudentDTO studentDto) throws StudentValidationException {

		Student student = new Student();
		student.setId(studentDto.getId());
		student.setName(studentDto.getName());
		student.setActive(true);
		student.setCollege(studentDto.getCollege());
		student.setContact(studentDto.getContact());
		student.setCourse(studentDto.getCourse());
		student.setEmail(studentDto.getEmail());
		student.setPassword(studentDto.getPassword());
		validateMandatoryFields(student);
		validateStudent(student);
		return studentRepository.save(student);

	}

	public String loginStudent(String email, String password) {
		Student studentop = studentRepository.findByEmail(email);

		if (studentop==null) {
			System.out.println("no email");
			throw new RuntimeException("Please Enter your Registered Email");
		}
	
		if (password.equals(studentop.getPassword())) { // Check hashed password
			return jwtutil.generateToken(email,studentop.getId()); // Generate JWT token
		} else {
			throw new RuntimeException("Incorrect password");
		}

	}

	public String forgotPwdStudent(String email) {
		System.out.println("email"+email);
		Student studentop = studentRepository.findByEmail(email);

		if (studentop==null) {
			System.out.println("no email");
			throw new RuntimeException("Please Enter your Registered Email");
		}

		else {
			  String token = jwtutil.generateToken(email,studentop.getId());
			
			studentop.setResetToken(token);
			studentRepository.save(studentop);
			System.out.println(token);
			// Send Reset Email
			String resetLink = "http://localhost:5173/reset-password/" + token;
			emailService.sendEmail(email, "Reset Your Password", "Click the link to reset: " + resetLink);

		}
		return email;

	}
	
	public String resetPassword(String token,String password) {
		System.out.println("in service "+password);
		 DecodedJWT decodedJWT = jwtutil.verifyToken(token);

	        // Check if the token is expired
	        if (JwtUtil.isTokenExpired(decodedJWT)) {
	        	 throw new RuntimeException("Token Expired");
	        }

	        
	        String userEmail = jwtutil.extractUserInfo(decodedJWT); 
		    if (userEmail == null) {
		        throw new RuntimeException("User not found");
		    }
		    
	        // Verify the user and update the password (e.g., find user by email)
	        Student student = studentRepository.findByEmail(userEmail);
	        if (student == null) {
	            throw new RuntimeException("User not found");
	        }

	        
	        student.setPassword(password);  // Hash the password before saving it
	        studentRepository.save(student);
	    
		return token;
		
	}

}
