package com.example.projectjett.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectjett.dto.FeedbackDTO;
import com.example.projectjett.services.FeedbackService;
import com.example.projectjett.utils.JwtUtil;

@RestController
@CrossOrigin(origins = "http://localhost:5173/student-home")
@RequestMapping("/api")
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;
	 @Autowired
	    private JwtUtil jwtUtil; 
	@PostMapping("/addfeedback")
	
	public ResponseEntity<String> addFeedback(@RequestHeader("Authorization") String token,@RequestBody FeedbackDTO feedbackDTO) {
		  if (token.startsWith("Bearer ")) {
		        token = token.substring(7);
		    }
		String studentId = jwtUtil.extractStudentId(token);
		feedbackDTO.setStudent(studentId);
		System.out.println("Feedback added"+feedbackDTO.getstudentId());
		feedbackService.addFeedback(feedbackDTO);
	    return ResponseEntity.ok("feedback added successfully!");
	} 
}
