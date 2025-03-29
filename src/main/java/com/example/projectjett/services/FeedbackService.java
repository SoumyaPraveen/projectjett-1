package com.example.projectjett.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projectjett.dto.FeedbackDTO;
import com.example.projectjett.dto.StudentDTO;
import com.example.projectjett.exceptions.StudentValidationException;
import com.example.projectjett.models.Feedback;
import com.example.projectjett.models.Student;
import com.example.projectjett.repositories.FeedbackRepository;
@Service
public class FeedbackService {
@Autowired
private FeedbackRepository feedbackRepo;
	

public Feedback addFeedback(FeedbackDTO feedbackDto) {

	Feedback feedback = new Feedback();
	feedback.setId(feedbackDto.getId());
	
	feedback.setStudent(feedbackDto.getstudentId());
	feedback.setRating(feedbackDto.getRating());
System.out.println(feedbackDto.getRating());
	return feedbackRepo.save(feedback);
}
}
