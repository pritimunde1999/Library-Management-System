package com.example.librarymanagementsystem.DTO.ResponsrDto;

import com.example.librarymanagementsystem.enums.Genre;
import com.example.librarymanagementsystem.model.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindBookResponseDto {

    private int id;
    private String name;

    private Genre genre;

    private int price;

    private boolean isIssued;

}
