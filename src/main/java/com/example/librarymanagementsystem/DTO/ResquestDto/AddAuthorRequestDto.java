package com.example.librarymanagementsystem.DTO.ResquestDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddAuthorRequestDto {
    private String name;
    private int age;
    private String email;
}
