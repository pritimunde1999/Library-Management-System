package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.DTO.ResponsrDto.FindBookResponseDto;
import com.example.librarymanagementsystem.DTO.ResquestDto.AddBookRequestDto;
import com.example.librarymanagementsystem.model.Book;

import java.util.List;

public interface BookServices {

    public String addBook(AddBookRequestDto addBookRequestDto) throws Exception;

    public List<FindBookResponseDto> findAllBooks();

    public List<FindBookResponseDto> findBooksByAuthorId(int id) throws Exception;

    public int noOfBooksByAuthor(String name) throws Exception;
}
