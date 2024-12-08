package com.example.Practice_SpringBoot.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgressRequest {
    private Long userId;
    private Long courseId;
    private Long moduleId;
    private Integer progressPercentage;
}