package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.DTO.ResponsrDto.MakePaymentResponseDto;
import com.example.librarymanagementsystem.DTO.ResquestDto.MakePaymentRequestDto;
import com.example.librarymanagementsystem.service.MoneyTransactionByStudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactionByStudent")
public class MoneyTransactionByStudentController {

    @Autowired
    MoneyTransactionByStudentServices moneyTransactionByStudentServices;

    @PostMapping("/makePayment")
    public MakePaymentResponseDto makePayment(MakePaymentRequestDto makePaymentRequestDto) throws Exception {
        return moneyTransactionByStudentServices.makePayment(makePaymentRequestDto);
    }
}
