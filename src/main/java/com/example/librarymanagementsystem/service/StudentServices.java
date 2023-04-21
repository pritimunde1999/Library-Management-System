package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.DTO.ResponsrDto.FindStudentByIdResponseDto;
import com.example.librarymanagementsystem.DTO.ResponsrDto.UpdateStudentMobResponseDto;
import com.example.librarymanagementsystem.DTO.ResquestDto.StudentRequestDto;
import com.example.librarymanagementsystem.DTO.ResquestDto.UpdateStudentMobRequestDto;
import com.example.librarymanagementsystem.model.Student;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface StudentServices {

    public String addStudent(StudentRequestDto student);

    public UpdateStudentMobResponseDto updateMobNo(@RequestBody UpdateStudentMobRequestDto updateStudentMobRequestDto) throws Exception;

    public String deleteStudentById(int id);

    public FindStudentByIdResponseDto findStudentById(int id);

    public List<FindStudentByIdResponseDto> findAllStudents();
}
