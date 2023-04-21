package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.DTO.ResponsrDto.FindBookResponseDto;
import com.example.librarymanagementsystem.DTO.ResquestDto.AddBookRequestDto;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements BookServices{

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public String addBook(AddBookRequestDto addBookRequestDto) throws Exception {

        Book book = new Book();

        book.setName(addBookRequestDto.getName());
        book.setGenre(addBookRequestDto.getGenre());
        book.setNoOfPages(addBookRequestDto.getNoOfPages());
        book.setPrice(addBookRequestDto.getPrice());

        Author author;
         try
         {
             author = authorRepository.findById(addBookRequestDto.getAuthorId()).get();
         }catch(Exception e)
         {
             throw new Exception("Author does not exists");
         }

         List<Book> booklist = author.getBooks();
         System.out.println("BookList " + booklist);
         booklist.add(book);
         System.out.println("BookList " + booklist);
         book.setAuthor(author); // from postman we are only taking id ... not complete author.. therefore we set author
         authorRepository.save(author);

         return "Book added";
    }

    @Override
    public List<FindBookResponseDto> findAllBooks() {

        List<Book> bookList = bookRepository.findAll();
        List<FindBookResponseDto> list = new ArrayList<>();

        for(Book book : bookList)
        {
            FindBookResponseDto findBookResponseDto = new FindBookResponseDto();
            findBookResponseDto.setId(book.getId());
            findBookResponseDto.setName(book.getName());
            findBookResponseDto.setGenre(book.getGenre());
            findBookResponseDto.setPrice(book.getPrice());

            findBookResponseDto.setIssued(book.isBookIssued());
            list.add(findBookResponseDto);
        }

        return list;
    }

    @Override
    public List<FindBookResponseDto> findBooksByAuthorId(int id) throws Exception {
        Author author;
        try
        {
            author = authorRepository.findById(id).get();
        }
        catch (Exception e)
        {
            throw new Exception("Invalid Author Id");
        }

        List<Book> bookList = author.getBooks();
        List<FindBookResponseDto> list = new ArrayList<>();

        for(Book book : bookList)
        {
            FindBookResponseDto findBookResponseDto = new FindBookResponseDto();
            findBookResponseDto.setId(book.getId());
            findBookResponseDto.setName(book.getName());
            findBookResponseDto.setGenre(book.getGenre());
            findBookResponseDto.setPrice(book.getPrice());

            findBookResponseDto.setIssued(book.isBookIssued());
            list.add(findBookResponseDto);
        }

        return list;
    }

    @Override
    public int noOfBooksByAuthor(String name) throws Exception {
        Author author;
        try {
            author = authorRepository.findByName(name);
        }
        catch (Exception e){
            throw new Exception("Invalid Author Name");
        }
        List<Book> bookList = author.getBooks();

        return bookList.size();
    }


}
