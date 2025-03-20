package com.example.library.repository;

import com.example.library.entity.UserActivity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserActivityRepository  extends MongoRepository<UserActivity, String> {
}
