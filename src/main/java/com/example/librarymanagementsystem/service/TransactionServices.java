package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.DTO.ResponsrDto.IssueBookResponseDto;
import com.example.librarymanagementsystem.DTO.ResponsrDto.ReturnBookResponseDto;
import com.example.librarymanagementsystem.DTO.ResquestDto.IssueBookRequestDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface TransactionServices {

    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception;

    public ReturnBookResponseDto returnBook(@RequestBody IssueBookRequestDto issueBookRequestDto) throws Exception;
}
