package com.example.librarymanagementsystem.DTO.ResponsrDto;

import com.example.librarymanagementsystem.enums.BookIssueStatus;
import com.example.librarymanagementsystem.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IssueBookResponseDto {

    private String transactionNumber;
    private BookIssueStatus bookIssueStatus;
    private String bookName;
}
