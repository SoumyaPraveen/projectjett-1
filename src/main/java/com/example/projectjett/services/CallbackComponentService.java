package com.example.projectjett.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projectjett.dto.CallbackDTO;
import com.example.projectjett.models.CallbackComponent;
import com.example.projectjett.repositories.CallbackComponentRepository;
@Service
public class CallbackComponentService {
	 @Autowired
	    private CallbackComponentRepository repository;

	    public CallbackComponent saveCallbackComponent(CallbackDTO callbackComponentDTO) {
	        // Convert DTO to Entity
	        CallbackComponent callbackComponent = new CallbackComponent();
	        callbackComponent.setName(callbackComponentDTO.getName());
	        callbackComponent.setContact(callbackComponentDTO.getContact());
	        callbackComponent.setCourse(callbackComponentDTO.getCourse());
	        callbackComponent.setCollege(callbackComponentDTO.getCollege());
	        callbackComponent.setProjectType(callbackComponentDTO.getProjectType());
	        callbackComponent.setTechnologies(callbackComponentDTO.getTechnologies());
	        callbackComponent.setEmail(callbackComponentDTO.getEmail());

	        // Save the entity to the database
	        return repository.save(callbackComponent);
	    }
}
