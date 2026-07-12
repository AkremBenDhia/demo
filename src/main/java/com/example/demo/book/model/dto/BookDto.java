package com.example.demo.book.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


public class BookDto {

    @Data
    @AllArgsConstructor
    @Builder
    @NotNull @NotBlank
    public static class PostInput {
        private Long id;
        private String title;
        private Long pages;
    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class PostOutput {
        private String title;
        private Long pages;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class getOutput {
        private Long id;
        private String title;
        private Long pages;
    }
}
