package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.DTO.ResponsrDto.FindBookResponseDto;
import com.example.librarymanagementsystem.DTO.ResquestDto.AddBookRequestDto;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.service.BookService;
import com.example.librarymanagementsystem.service.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookServices bookService;

    @PostMapping("/add")
    public String addBook(@RequestBody AddBookRequestDto addBookRequestDto) throws Exception {
        return bookService.addBook(addBookRequestDto);
    }

    // find all books
    @GetMapping("/findAllBooks")
    public List<FindBookResponseDto> findAllBooks()
    {
        return bookService.findAllBooks();
    }

    //find all the books of particular authorId
    @GetMapping("/findBooksByAuthorId")
    public List<FindBookResponseDto> findBooksByAuthorId(@RequestParam("id") int id) throws Exception {
        return bookService.findBooksByAuthorId(id);
    }

    //number of books writtem by author
    @GetMapping("/noOfBooksByAuthor")
    public int noOfBooksByAuthor(@RequestParam("name") String name) throws Exception {
        return bookService.noOfBooksByAuthor(name);
    }
}
