package com.example.library.repository;

import com.example.library.entity.UserActivity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserActivityRepository  extends MongoRepository<UserActivity, String> {
    List<UserActivity> findByUserId(String userId);

}
