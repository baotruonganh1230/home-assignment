package com.example.homeassignment.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    @Size(max=255, message = "Title length should be less than 255")
    private String title;

    @Size(max=255, message = "Description length should be less than 255")
    private String description;

    private Boolean completed;
}
