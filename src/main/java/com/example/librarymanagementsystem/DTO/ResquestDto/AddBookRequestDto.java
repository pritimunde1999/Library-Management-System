package com.example.librarymanagementsystem.DTO.ResquestDto;

import com.example.librarymanagementsystem.enums.Genre;
import com.example.librarymanagementsystem.model.Author;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddBookRequestDto {


    private String name;

    private int noOfPages;
    private Genre genre;

    private int price;

    private int authorId;
}
