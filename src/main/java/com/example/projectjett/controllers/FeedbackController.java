package com.example.projectjett.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectjett.dto.FeedbackDTO;
import com.example.projectjett.services.FeedbackService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;

	@PostMapping("/addfeedback")
	
	public ResponseEntity<String> addFeedback(@RequestBody FeedbackDTO feedbackDTO) {
		System.out.println("Feedback added"+feedbackDTO.getstudentId());
		feedbackService.addFeedback(feedbackDTO);
	    return ResponseEntity.ok("feedback added successfully!");
	} 
}
