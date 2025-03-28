package com.example.projectjett.services;


import com.example.projectjett.models.Student;
import com.example.projectjett.repositories.StudentRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;

    public CustomUserDetailsService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(email);
        if (student == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return new User(
            student.getEmail(),
            student.getPassword(),
            Collections.emptyList() // No roles for now
        );
    }
}
