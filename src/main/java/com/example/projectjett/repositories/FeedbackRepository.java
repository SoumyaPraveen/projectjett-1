package com.example.projectjett.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.projectjett.models.Feedback;

public interface FeedbackRepository extends MongoRepository<Feedback,String> {

}
