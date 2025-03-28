package com.example.projectjett.dto;

public class StudentDTO {

	    private String id;
	    private String name;
	    private String contact;
	    private String course;
	    private String college;
	    private String email;
	    private String password;
	    private boolean isActive;
	    private String resetToken;
	    // Constructor
	    public StudentDTO(String id, String name,String password, String contact, String course, String college, String email, boolean isActive) {
	        this.id = id;
	        this.name = name;
	        this.contact = contact;
	        this.course = course;
	        this.college = college;
	        this.email = email;
	        
	        this.isActive = isActive;
	        this.password=password;
	    }

	    public StudentDTO() {
			super();
		}

		// Getters and Setters
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

		public String getResetToken() {
			return resetToken;
		}

		public void setResetToken(String resetToken) {
			this.resetToken = resetToken;
		}
	
}
