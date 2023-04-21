package com.example.librarymanagementsystem.DTO.ResponsrDto;

import com.example.librarymanagementsystem.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FindAuthorByIdResponseDto {

    private int id;
    private String name;
    private String email;
    //private List<Book> bookList;
}
