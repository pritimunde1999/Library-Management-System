package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.DTO.ResponsrDto.IssueBookResponseDto;
import com.example.librarymanagementsystem.DTO.ResponsrDto.ReturnBookResponseDto;
import com.example.librarymanagementsystem.DTO.ResquestDto.IssueBookRequestDto;
import com.example.librarymanagementsystem.service.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionServices transactionServices;

    @PostMapping("/issueBook")
    public IssueBookResponseDto issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto) throws Exception {
        return transactionServices.issueBook(issueBookRequestDto);
    }


    @PutMapping("returnBook")
    public ReturnBookResponseDto returnBook(@RequestBody IssueBookRequestDto issueBookRequestDto) throws Exception {
        return  transactionServices.returnBook(issueBookRequestDto);
    }
}
