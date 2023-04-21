package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.DTO.ResponsrDto.DeleteAuthorByIdResponseDto;
import com.example.librarymanagementsystem.DTO.ResponsrDto.FindAuthorByIdResponseDto;
import com.example.librarymanagementsystem.DTO.ResquestDto.AddAuthorRequestDto;
import com.example.librarymanagementsystem.model.Author;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AuthorServices {

    public String addAuthor(AddAuthorRequestDto addAuthorRequestDto);

    public FindAuthorByIdResponseDto getByMail(String email);

    public DeleteAuthorByIdResponseDto deleteAuthor(int id) throws Exception;

    public FindAuthorByIdResponseDto findAuthor(int id) throws Exception;

    public List<String> getAllAuthor();
}
