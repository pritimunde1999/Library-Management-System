package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.DTO.ResponsrDto.MakePaymentResponseDto;
import com.example.librarymanagementsystem.DTO.ResquestDto.MakePaymentRequestDto;
import com.example.librarymanagementsystem.enums.CardStatus;
import com.example.librarymanagementsystem.enums.TransactionStatus;
import com.example.librarymanagementsystem.model.Card;
import com.example.librarymanagementsystem.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyTransactionByStudentService implements MoneyTransactionByStudentServices{

    @Autowired
    CardRepository cardRepository;

    @Override
    public MakePaymentResponseDto makePayment(MakePaymentRequestDto makePaymentRequestDto) throws Exception {
        Card card;
        try{
            card = cardRepository.findById(makePaymentRequestDto.getCardId()).get();
        }
        catch (Exception e)
        {
            throw new Exception("Invalid Card Id");
        }

        MakePaymentResponseDto makePaymentResponseDto = new MakePaymentResponseDto();


        //if no due amount
        if(card.getPaymentDue()==0) {
            card.setCardStatus(CardStatus.ACTIVATE);
            card.setPaymentDue(0);

           makePaymentResponseDto.setCardId(card.getId());
           makePaymentResponseDto.setStudentName(card.getStudent().getName());
           makePaymentResponseDto.setTransactionStatus(TransactionStatus.PAID);
           makePaymentResponseDto.setAmountDue(0);
           makePaymentResponseDto.setCardStatus(CardStatus.ACTIVATE);
           cardRepository.save(card);
           return makePaymentResponseDto;
        }


        //paid same  amount as due amount
        //card will get activate
        if(makePaymentRequestDto.getAmount()==card.getPaymentDue())
        {
            card.setPaymentDue(0);
            card.setSuccessfulPayments(makePaymentRequestDto.getAmount());
            card.setCardStatus(CardStatus.ACTIVATE);
            makePaymentResponseDto.setCardId(card.getId());
            makePaymentResponseDto.setStudentName(card.getStudent().getName());
            makePaymentResponseDto.setTransactionStatus(TransactionStatus.SUCCESS);
            makePaymentResponseDto.setAmountDue(0);
            makePaymentResponseDto.setCardStatus(CardStatus.ACTIVATE);
            cardRepository.save(card);
            return makePaymentResponseDto;
        }


        //If amount is less than due amount
        //card wont get activate
        if(makePaymentRequestDto.getAmount() < card.getPaymentDue())
        {
            int amountDue = card.getPaymentDue() - makePaymentRequestDto.getAmount();
            card.setPaymentDue(amountDue);
            card.setCardStatus(CardStatus.BLOCKED);

            makePaymentResponseDto.setCardId(card.getId());
            makePaymentResponseDto.setStudentName(card.getStudent().getName());
            makePaymentResponseDto.setTransactionStatus(TransactionStatus.SUCCESS);
            makePaymentResponseDto.setAmountDue(amountDue);
            makePaymentResponseDto.setCardStatus(CardStatus.BLOCKED);
            cardRepository.save(card);
            return makePaymentResponseDto;
        }

        return makePaymentResponseDto;
    }
}
