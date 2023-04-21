package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.DTO.ResponsrDto.DeleteAuthorByIdResponseDto;
import com.example.librarymanagementsystem.DTO.ResponsrDto.FindAuthorByIdResponseDto;
import com.example.librarymanagementsystem.DTO.ResquestDto.AddAuthorRequestDto;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService implements AuthorServices{

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public String addAuthor(AddAuthorRequestDto addAuthorRequestDto) {
        Author author = new Author();
        author.setName(addAuthorRequestDto.getName());
        author.setAge(addAuthorRequestDto.getAge());
        author.setEmail(addAuthorRequestDto.getEmail());

        authorRepository.save(author);
        return "Author added";
    }

    @Override
    public FindAuthorByIdResponseDto getByMail(String email) {
       Author author = authorRepository.findByEmail(email);
       FindAuthorByIdResponseDto findAuthorByIdResponseDto = new FindAuthorByIdResponseDto();
       findAuthorByIdResponseDto.setName(author.getName());
       findAuthorByIdResponseDto.setId(author.getId());
       findAuthorByIdResponseDto.setEmail(author.getEmail());
      // findAuthorByIdResponseDto.setBookList(author.getBooks());

       return findAuthorByIdResponseDto;
    }

    @Override
    public DeleteAuthorByIdResponseDto deleteAuthor(int id) throws Exception {

        Author author;
        try {
            author = authorRepository.findById(id).get();
        }
        catch (Exception e)
        {
            throw new Exception("Invalid author id");
        }

        DeleteAuthorByIdResponseDto deleteAuthorByIdResponseDto = new DeleteAuthorByIdResponseDto();
        deleteAuthorByIdResponseDto.setId(author.getId());
        deleteAuthorByIdResponseDto.setName(author.getName());
        deleteAuthorByIdResponseDto.setText("Author Deleted");

        authorRepository.deleteById(id);
        return deleteAuthorByIdResponseDto;
    }

    @Override
    public FindAuthorByIdResponseDto findAuthor(int id) throws Exception {
        Author author;
        try {
            author = authorRepository.findById(id).get();
        }
        catch (Exception e){
            throw new Exception("Invalid Author Id");
        }

        FindAuthorByIdResponseDto findAuthorByIdResponseDto = new FindAuthorByIdResponseDto();
        findAuthorByIdResponseDto.setId(author.getId());
        findAuthorByIdResponseDto.setName(author.getName());
        findAuthorByIdResponseDto.setEmail(author.getEmail());
        //findAuthorByIdResponseDto.setBookList(author.getBooks());

        return findAuthorByIdResponseDto;

    }

    @Override
    public List<String> getAllAuthor() {

        List<Author> list = authorRepository.findAll();
        List<String> authorName = new ArrayList<>();

        for(Author author : list)
        {
            authorName.add(author.getName());
        }

        return authorName;
    }


}
