package com.Project.backend.Dto;

import lombok.Data;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String descripttion;
    private String content;
}
