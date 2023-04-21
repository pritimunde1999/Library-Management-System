package com.example.librarymanagementsystem.DTO.ResponsrDto;

import com.example.librarymanagementsystem.enums.CardStatus;
import com.example.librarymanagementsystem.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MakePaymentResponseDto {

    private TransactionStatus transactionStatus;

    private String studentName;
    private int cardId;

    private int amount;
    private int amountDue;
    private CardStatus cardStatus;

}

