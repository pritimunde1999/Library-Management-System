package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.DTO.ResponsrDto.MakePaymentResponseDto;
import com.example.librarymanagementsystem.DTO.ResquestDto.MakePaymentRequestDto;

public interface MoneyTransactionByStudentServices {


    MakePaymentResponseDto makePayment(MakePaymentRequestDto makePaymentRequestDto) throws Exception;
}
