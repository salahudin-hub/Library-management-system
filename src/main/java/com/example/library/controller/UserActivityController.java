package com.example.library.controller;

import com.example.library.dto.UserActivityDTO;
import com.example.library.mapper.UserActivityMapper;
import com.example.library.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user-activities")
public class UserActivityController {
    @Autowired
    private UserActivityService userActivityService;

    @PostMapping
    public UserActivityDTO logUserActivity(@RequestBody UserActivityDTO userActivityDTO) {
        return UserActivityMapper.toDTO(
                userActivityService.logUserActivity(
                        userActivityDTO.getUserId(),
                        userActivityDTO.getActivityType(),
                        userActivityDTO.getActivityDetails()
                )
        );
    }

    @GetMapping
    public List<UserActivityDTO> getUserActivities(@RequestParam String userId) {
        return userActivityService.getUserActivities(userId).stream()
                .map(UserActivityMapper::toDTO)
                .collect(Collectors.toList());
    }
}