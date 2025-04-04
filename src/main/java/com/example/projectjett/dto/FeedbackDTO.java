package com.example.projectjett.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.example.projectjett.models.Student;

public class FeedbackDTO {
	
	private String id;
	private String  studentId;
	private String feedback;
	private Float rating;
	public String getId() {
		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String  getstudentId() {
		return studentId;
	}
	public void setStudent(String  studentId) {
		this.studentId = studentId;
	}
	public FeedbackDTO(String id, String  studentId,Float rating) {
		super();
		this.id = id;
		this.rating = rating;
		this.studentId = studentId;
	}
	public FeedbackDTO() {
		super();
		}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public Float getRating() {
		return rating;
	}
	public void setRating(Float rating) {
		this.rating = rating;
	}
	
	
}
