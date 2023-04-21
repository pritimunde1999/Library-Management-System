package com.example.librarymanagementsystem.DTO.ResquestDto;

import com.example.librarymanagementsystem.enums.Department;
import jakarta.persistence.Column;
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
public class UpdateStudentMobRequestDto {

    private int id;
    private String mobile;


}
