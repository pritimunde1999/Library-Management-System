package com.example.librarymanagementsystem.DTO.ResquestDto;

import com.example.librarymanagementsystem.enums.Department;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentRequestDto {

    private String name;
    private int age;
    private String mobile;
    private Department department;

    private String email;

}
