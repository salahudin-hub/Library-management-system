package com.example.library.service;

import com.example.library.entity.UserActivity;
import com.example.library.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class UserActivityService {
    @Autowired
    private UserActivityRepository userActivityRepository;

    public void logUserActivity(String userId, String activityType, String activityDetails) {
        UserActivity userActivity = new UserActivity();
        userActivity.setUserId(userId);
        userActivity.setActivityType(activityType);
        userActivity.setActivityDetails(activityDetails);
        userActivity.setTimestamp(new Date());
        userActivityRepository.save(userActivity);
    }
}
