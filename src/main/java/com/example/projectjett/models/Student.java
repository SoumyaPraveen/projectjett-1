package com.example.projectjett.models;


import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "students") 

public class Student {
	 @Id
	    private String id;  
	
	    private String name;
	    private String contact;
	    private String course;
	    private String college;
	    private String email;
	    private String password;
	    private boolean isActive;
	    private String resetToken;
	    @CreatedDate
	    private Date createdAt; 

	    @LastModifiedDate
	    private Date updatedAt; 

		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getContact() {
			return contact;
		}
		public void setContact(String contact) {
			this.contact = contact;
		}
		public String getCourse() {
			return course;
		}
		public void setCourse(String course) {
			this.course = course;
		}
		public String getCollege() {
			return college;
		}
		public void setCollege(String college) {
			this.college = college;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public boolean isActive() {
			return isActive;
		}
		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}
	
	    public Date getCreatedAt() {
	        return createdAt;
	    }

	    public Date getUpdatedAt() {
	        return updatedAt;
	    }
		public String getResetToken() {
			return resetToken;
		}
		public void setResetToken(String resetToken) {
			this.resetToken = resetToken;
		}
	    
	    

}
