package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.DTO.ResponsrDto.FindStudentByIdResponseDto;
import com.example.librarymanagementsystem.DTO.ResponsrDto.UpdateStudentMobResponseDto;
import com.example.librarymanagementsystem.DTO.ResquestDto.StudentRequestDto;
import com.example.librarymanagementsystem.DTO.ResquestDto.UpdateStudentMobRequestDto;
import com.example.librarymanagementsystem.enums.CardStatus;
import com.example.librarymanagementsystem.model.Card;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentService implements StudentServices{

    @Autowired
    StudentRepository studentRepository;
    @Override
    public String addStudent(StudentRequestDto studentRequestDto) {
        Student student = new Student();

        student.setAge(studentRequestDto.getAge());
        student.setName(studentRequestDto.getName());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setMobile(studentRequestDto.getMobile());
        student.setEmail(studentRequestDto.getEmail());


        //add new student so have to activate card for student
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATE);
        card.setValidTill("2025-04-15");
        card.setStudent(student);
        student.setCard(card);
        studentRepository.save(student);
        return "Student Added";
    }

    @Override
    public UpdateStudentMobResponseDto updateMobNo(UpdateStudentMobRequestDto updateStudentMobRequestDto) throws Exception {

        try{
            Student student = studentRepository.findById(updateStudentMobRequestDto.getId()).get();
            student.setMobile(updateStudentMobRequestDto.getMobile());
            Student updateStudent = studentRepository.save(student);

            // return type
            UpdateStudentMobResponseDto updateStudentMobResponseDto = new UpdateStudentMobResponseDto();
            updateStudentMobResponseDto.setName(updateStudent.getName());
            updateStudentMobResponseDto.setMobile(updateStudent.getMobile());
            return updateStudentMobResponseDto;
        }catch (Exception e)
        {
            throw new Exception("Invalid Student Id");
        }
    }

    //delete student by id
    @Override
    public String deleteStudentById(int id) {
        studentRepository.deleteById(id);
        return "Student Deleted";
    }


    //find student by id(its not directly possible bcoz of infinite loop .... infinite loop bcoz of parent child reln in that class.. so we use dto here)
    @Override
    public FindStudentByIdResponseDto findStudentById(int id) {
       Student student = studentRepository.findById(id).get();

       FindStudentByIdResponseDto findStudentByIdResponseDto = new FindStudentByIdResponseDto();
       findStudentByIdResponseDto.setId(id);
       findStudentByIdResponseDto.setName(student.getName());
       findStudentByIdResponseDto.setMobile(student.getMobile());

       return findStudentByIdResponseDto;
    }


    //find all students
    @Override
    public List<FindStudentByIdResponseDto> findAllStudents() {

       List<Student> list  = studentRepository.findAll();
       List<FindStudentByIdResponseDto> ansList = new ArrayList<>();

       for(Student student : list)
       {
           FindStudentByIdResponseDto findStudentByIdResponseDto = new FindStudentByIdResponseDto();
           findStudentByIdResponseDto.setId(student.getId());
           findStudentByIdResponseDto.setName(student.getName());
           findStudentByIdResponseDto.setMobile(student.getMobile());
           ansList.add(findStudentByIdResponseDto);
       }

       return ansList;
    }


    //update student by id




}
