package com.example.librarymanagementsystem.DTO.ResponsrDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindStudentByIdResponseDto {

    private int id;
    private String name;
    private String mobile;
}
