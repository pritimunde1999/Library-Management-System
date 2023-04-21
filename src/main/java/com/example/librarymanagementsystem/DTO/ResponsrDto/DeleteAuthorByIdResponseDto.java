package com.example.librarymanagementsystem.DTO.ResponsrDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DeleteAuthorByIdResponseDto {

    String text;

    private int id;
    private String name;

}
