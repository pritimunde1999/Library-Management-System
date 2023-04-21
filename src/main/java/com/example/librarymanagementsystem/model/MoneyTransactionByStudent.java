package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.enums.TransactionStatus;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MoneyTransactionByStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String transactionNum;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    private int moneyByStudent;

    @CreationTimestamp
    private Date date;

    @ManyToOne
    @JoinColumn
    Card card;

}
