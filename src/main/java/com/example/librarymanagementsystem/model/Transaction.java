package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.enums.BookIssueStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String transactionNumber;

    @CreationTimestamp
    private LocalDate bookIssueDate;


    private LocalDate bookReturnDate;

    @Enumerated(EnumType.STRING)
    private BookIssueStatus isBookIssued;

    @ManyToOne
    @JoinColumn
    Card card;

    @ManyToOne
    @JoinColumn
    Book book;
}
