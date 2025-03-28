package com.example.projectjett.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.projectjett.models.CallbackComponent;

public interface CallbackComponentRepository extends MongoRepository<CallbackComponent, String> {
    // You can define custom query methods here if needed
}
