package com.example.library.mapper;

import com.example.library.dto.UserActivityDTO;

import com.example.library.entity.UserActivity;

public class UserActivityMapper {
    public static UserActivityDTO toDTO(UserActivity userActivity) {
        UserActivityDTO dto = new UserActivityDTO();
        dto.setId(userActivity.getId());
        dto.setUserId(userActivity.getUserId());
        dto.setActivityType(userActivity.getActivityType());
        dto.setActivityDetails(userActivity.getActivityDetails());
        dto.setTimestamp(userActivity.getTimestamp());
        return dto;
    }

    public static UserActivity toEntity(UserActivityDTO dto) {
        UserActivity userActivity = new UserActivity();
        userActivity.setUserId(dto.getUserId());
        userActivity.setActivityType(dto.getActivityType());
        userActivity.setActivityDetails(dto.getActivityDetails());
        userActivity.setTimestamp(dto.getTimestamp());
        return userActivity;
    }
}