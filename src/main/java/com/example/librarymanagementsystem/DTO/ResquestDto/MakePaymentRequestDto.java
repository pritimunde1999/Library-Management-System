package com.example.librarymanagementsystem.DTO.ResquestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MakePaymentRequestDto {

    private int cardId;

    private int amount;
}
