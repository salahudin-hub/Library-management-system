package com.example.library.service;

import com.example.library.entity.UserActivity;
import com.example.library.repository.UserActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class UserActivityService {
    @Autowired
    private UserActivityRepository userActivityRepository;

    public UserActivity logUserActivity(String userId, String activityType, String activityDetails) {
        UserActivity userActivity = new UserActivity();
        userActivity.setUserId(userId);
        userActivity.setActivityType(activityType);
        userActivity.setActivityDetails(activityDetails);
        userActivity.setTimestamp(new Date());
        return userActivityRepository.save(userActivity);
    }

    public List<UserActivity> getUserActivities(String userId) {
        return userActivityRepository.findByUserId(userId);
    }
}
