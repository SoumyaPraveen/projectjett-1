package com.example.projectjett.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.projectjett.models.Student;

public interface StudentRepository extends MongoRepository<Student,String> {

	boolean existsByContact(String contact);

	boolean existsByEmail(String email);

	Student  findByEmail(String email);
	Optional<Student> findByResetToken(String resetToken);

}
