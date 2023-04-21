package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.DTO.ResponsrDto.DeleteAuthorByIdResponseDto;
import com.example.librarymanagementsystem.DTO.ResponsrDto.FindAuthorByIdResponseDto;
import com.example.librarymanagementsystem.DTO.ResquestDto.AddAuthorRequestDto;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.service.AuthorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorServices authorServices;

    @PostMapping("/add")
    public String addAuthor(@RequestBody AddAuthorRequestDto addAuthorRequestDto)
    {
        return authorServices.addAuthor(addAuthorRequestDto);
    }

    @GetMapping("/findByEmail")
    public FindAuthorByIdResponseDto getByMail(@RequestParam("email") String email)
    {
        return authorServices.getByMail(email);
    }

    @DeleteMapping("/deleteAuthorById")
    public DeleteAuthorByIdResponseDto deleteAuthor(@RequestParam("id") int id) throws Exception {
         return authorServices.deleteAuthor(id);
    }

    @GetMapping("/getAuthorById")
    public FindAuthorByIdResponseDto findAuthor(@RequestParam("id") int id) throws Exception {
        return authorServices.findAuthor(id);
    }

    @GetMapping("/getAllAuthor")
    public List<String> getAllAuthor()
    {
        return authorServices.getAllAuthor();
    }
}
