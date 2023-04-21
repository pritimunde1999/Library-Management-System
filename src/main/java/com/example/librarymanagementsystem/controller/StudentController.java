package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.DTO.ResponsrDto.FindStudentByIdResponseDto;
import com.example.librarymanagementsystem.DTO.ResponsrDto.UpdateStudentMobResponseDto;
import com.example.librarymanagementsystem.DTO.ResquestDto.StudentRequestDto;
import com.example.librarymanagementsystem.DTO.ResquestDto.UpdateStudentMobRequestDto;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.service.StudentService;
import com.example.librarymanagementsystem.service.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    //it is an interface still we can create object of it bcoz we have implementation of this interface
    @Autowired
    StudentServices studentServices;

    @PostMapping("/add")
    public String addStudent(@RequestBody StudentRequestDto studentRequestDto)
    {
       return studentServices.addStudent(studentRequestDto);
    }

    @PutMapping("/updateMobileNo")
    public UpdateStudentMobResponseDto updateMobileNo(@RequestBody UpdateStudentMobRequestDto updateStudentMobRequestDto) throws Exception {
        return studentServices.updateMobNo(updateStudentMobRequestDto);
    }

    @DeleteMapping("/deleteStudentById")
    public String deleteStudentById(@RequestParam("id") int id)
    {
        return studentServices.deleteStudentById(id);
    }

    @GetMapping("/getStudentById")
    public FindStudentByIdResponseDto findStudentById(@RequestParam("id") int id)
    {
        return studentServices.findStudentById(id);
    }

    @GetMapping("/findAllStudent")
    public List<FindStudentByIdResponseDto> findAllStudents()
    {
        return studentServices.findAllStudents();
    }
}
