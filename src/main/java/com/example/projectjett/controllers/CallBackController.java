package com.example.projectjett.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectjett.dto.CallbackDTO;
import com.example.projectjett.services.CallbackComponentService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class CallBackController {
	
	@Autowired
    private CallbackComponentService callbackComponentService;
	
	@GetMapping("testapi")
	public String testApi() {
		return "Api Works";
	}
	  @PostMapping("/save")
	    public ResponseEntity<String> saveCallbackComponent(@RequestBody CallbackDTO callbackDTO) {
	        callbackComponentService.saveCallbackComponent(callbackDTO);
	        return ResponseEntity.ok("Callback Component saved successfully!");
	    }
	  @GetMapping("/test")
	    public ResponseEntity<String> test() {
	        return ResponseEntity.ok("Apis are working!");
	    }
}
