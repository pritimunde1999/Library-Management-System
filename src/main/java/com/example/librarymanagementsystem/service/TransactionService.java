package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.DTO.ResponsrDto.IssueBookResponseDto;
import com.example.librarymanagementsystem.DTO.ResponsrDto.ReturnBookResponseDto;
import com.example.librarymanagementsystem.DTO.ResquestDto.IssueBookRequestDto;
import com.example.librarymanagementsystem.enums.BookIssueStatus;
import com.example.librarymanagementsystem.enums.CardStatus;
import com.example.librarymanagementsystem.enums.TransactionStatus;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.Card;
import com.example.librarymanagementsystem.model.Transaction;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.CardRepository;
import com.example.librarymanagementsystem.repository.TransactionRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService implements TransactionServices{

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    private JavaMailSender emailSender;


    @Override
    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception {


        //find card
        Card card;
        try{
            card = cardRepository.findById(issueBookRequestDto.getCardId()).get();
        } catch(Exception e)
        {
            throw new Exception("Invalid card Id");
        }

        //student cannot issue more than 2 books
        if(card.getBookList().size()>=2)
        {
            throw new Exception("You already issue 2 boooks. To issue new book you have to return atleast one book");
        }

         //make a new transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIsBookIssued(BookIssueStatus.ISSUED);


        //find book
        Book book;
        try {
            book = bookRepository.findById(issueBookRequestDto.getBookId()).get();
        }catch (Exception e){
            transaction.setIsBookIssued(BookIssueStatus.CANNOT_ISSUE);
            transactionRepository.save(transaction);
            throw new Exception("Invalid Book Id");
        }

        // if payment is due
        //card will get blocked until payment is done
        if(card.getPaymentDue()!=0)
        {
            card.setCardStatus(CardStatus.BLOCKED);
            transaction.setIsBookIssued(BookIssueStatus.CANNOT_ISSUE);
            transactionRepository.save(transaction);
            throw new Exception("Card is Blocked..Please complete due payments!");
        }


        //if current date goes beyond valid till date card will get expired
        String todaysDate = String.valueOf(LocalDate.now());
        if(card.getValidTill().compareTo(todaysDate)<1)
        {
            card.setCardStatus(CardStatus.EXPIRED);
            transaction.setIsBookIssued(BookIssueStatus.CANNOT_ISSUE);
            transactionRepository.save(transaction);
            throw new Exception("Card is Expired..Please update the card!");
        }

        //if card is not activate
        //student cannot issue a book
        if(card.getCardStatus() != CardStatus.ACTIVATE)
        {
            transaction.setIsBookIssued(BookIssueStatus.CANNOT_ISSUE);
            transactionRepository.save(transaction);
            throw new Exception("Card is Not Activate");
        }


        // if book is already issued to someone that book cant be issue to another student
        if(book.isBookIssued())
        {
            transaction.setIsBookIssued(BookIssueStatus.CANNOT_ISSUE);
            transactionRepository.save(transaction);
            throw new Exception("Book is already Issued");
        }


        //if book is issue to student

        //save card in transaction
        transaction.setBook(book);

        //save book in transaction
        transaction.setCard(card);

        //set book is issued now
        book.setBookIssued(true);

        //set card in book
        book.setCard(card);

        //transaction list in book
        book.getTransactionList().add(transaction);


        card.getBookList().add(book);
        card.getTransactionList().add(transaction);

        cardRepository.save(card);

        IssueBookResponseDto issueBookResponseDto =new IssueBookResponseDto();
        issueBookResponseDto.setBookName(book.getName());
        issueBookResponseDto.setTransactionNumber(transaction.getTransactionNumber());
        issueBookResponseDto.setBookIssueStatus(BookIssueStatus.ISSUED);

        String text = "Dear "+ card.getStudent().getName()+ ",\n" +
                "\n" +
                "I am writing to acknowledge the book that you have recently borrowed from the library. The book titled " + book.getName() +" has been issued to you on "+ java.time.LocalDate.now() +", and the due date for the return of the book is "+ java.time.LocalDate.now().plusDays(7)+ ".\n" +
                "\n" +
                "Please ensure that you return the book on or before the due date to avoid any late fees or penalties. If you wish to renew the book, please visit the library's website or contact us at the circulation desk to see if renewal is possible.\n" +
                "\n" +
                "Please take care of the book and keep it in good condition. Any damages to the book will be charged to your account, and it may affect your borrowing privileges.\n" +
                "\n" +
                "If you have any questions or concerns regarding the book or library policies, please do not hesitate to contact us. We are always happy to assist you.\n" +
                "\n" +
                "Thank you for using our library services, and we hope that you enjoy reading the book.\n" +
                "\n" +
                "Sincerely,\n" +
                "\n" +
                "S.M.Karnale.";


        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply.library.management.2023@gmail.com");
        message.setTo(card.getStudent().getEmail());
        message.setSubject("Book Issued");
        message.setText(text);
        emailSender.send(message);

        return issueBookResponseDto;
    }

    @Override
    public ReturnBookResponseDto returnBook(IssueBookRequestDto issueBookRequestDto) throws Exception {


        Card card;
        try{
            card = cardRepository.findById(issueBookRequestDto.getCardId()).get();
        } catch(Exception e)
        {
            throw new Exception("Invalid card Id");
        }

        Book book;
        try {
            book = bookRepository.findById(issueBookRequestDto.getBookId()).get();
        }catch (Exception e){

            throw new Exception("Invalid Book Id");
        }

        //if the particular card not contain that particular book
        if(!card.getBookList().contains(book))
        {
            throw new Exception("Book does not belong to this card");
        }


        //get transaction history of the card.. so we can update that
        List<Transaction> transactionList = card.getTransactionList();
        Transaction transaction = null;
        for(Transaction transaction1 : transactionList)
        {
            if(transaction1.getBook().equals(book))
            {
                transaction = transaction1;
            }
        }

        //set todays date as return date
        transaction.setBookReturnDate(java.time.LocalDate.now());
        LocalDate issue = transaction.getBookIssueDate();
        LocalDate returnD = transaction.getBookReturnDate();
        System.out.print(returnD);

        //for fine ..get difference between issue date and return date
        int daysBetween = (int)ChronoUnit.DAYS.between(issue, returnD);
        String text ="";
        SimpleMailMessage message = new SimpleMailMessage();

        // if diff is greater that 7 days but less that equal to 37 days then calculate fine as per day fine is Rs.5
        //card will get blocked
        if(daysBetween>7 && daysBetween<=37)
        {
            int days = daysBetween-7;
            int fine = card.getPaymentDue()+ days*5;
            card.setPaymentDue(fine);
            card.setCardStatus(CardStatus.BLOCKED);
            message.setSubject("Penalty for Delayed Book Return");

            text =  "Dear "+ card.getStudent().getName()+ ",\n" +
                    "\n" +
                    "I am writing this email to inform you that as per our library records, the book titled " + book.getName() + " issued to you on "+ transaction.getBookIssueDate() +" was due to be returned on "+ transaction.getBookReturnDate() + ".\n" +
                    "\n" +
                    "We understand that unforeseen circumstances can arise, but it is essential that library books are returned on time so that other students can also utilize them. As per our library rules, a penalty is charged for delayed returns.\n" +
                    "\n" +
                    "We regret to inform you that you have been charged a penalty of Rs."+ fine +" for the delayed return of the book. Please note that this penalty is in addition to any overdue fines that may have accrued during the delay.\n" +
                    "\n" +
                    "We request you to kindly settle the penalty amount at the library desk at the earliest possible time. Failure to do so will result in the suspension of your library privileges until the outstanding amount is cleared.\n" +
                    "\n" +
                    "We hope that you will take this matter seriously and ensure that future library books are returned on time to avoid such penalties.\n" +
                    "\n" +
                    "Thank you for your cooperation.\n" +
                    "\n" +
                    "Sincerely,\n" +
                    "S.M.Karnale.";
        }

        //if after 7 days if 1month get passed then fine will be Rs.500
        //card will get blocked
        if(daysBetween>37)
        {
            int fine = card.getPaymentDue()+500;
            card.setPaymentDue(fine);
            card.setCardStatus(CardStatus.BLOCKED);

            message.setSubject("Penalty for Delayed Book Return");

            text =  "Dear "+ card.getStudent().getName()+ ",\n" +
                    "\n" +
                    "I am writing this email to inform you that as per our library records, the book titled " + book.getName() + " issued to you on "+ transaction.getBookIssueDate() +" was due to be returned on "+ transaction.getBookReturnDate() + ".\n" +
                    "\n" +
                    "We understand that unforeseen circumstances can arise, but it is essential that library books are returned on time so that other students can also utilize them. As per our library rules, a penalty is charged for delayed returns.\n" +
                    "\n" +
                    "We regret to inform you that you have been charged a penalty of Rs."+ fine +" for the delayed return of the book. Please note that this penalty is in addition to any overdue fines that may have accrued during the delay.\n" +
                    "\n" +
                    "We request you to kindly settle the penalty amount at the library desk at the earliest possible time. Failure to do so will result in the suspension of your library privileges until the outstanding amount is cleared.\n" +
                    "\n" +
                    "We hope that you will take this matter seriously and ensure that future library books are returned on time to avoid such penalties.\n" +
                    "\n" +
                    "Thank you for your cooperation.\n" +
                    "\n" +
                    "Sincerely,\n" +
                    "S.M.Karnale.";
        }


        if(text.equals(""))
        {
            message.setSubject("Acknowledgement of Book Return");

            text =  "Dear "+ card.getStudent().getName()+ ",\n" +
                    "\n" +
                    "I am writing to acknowledge the return of the book " + book.getName() + " which you borrowed from our library on "+ transaction.getBookIssueDate() + ". I am pleased to inform you that the book has been returned on time and in good condition.\n" +
                    "\n" +
                    "We appreciate your promptness in returning the book on the due date. Your responsible behavior is a testament to your commitment to maintaining the integrity of the library system. Your timely return ensures that the book is available for other students who require it.\n" +
                    "\n" +
                    "Please note that the due date for returning books is an important aspect of library management. Your cooperation in adhering to this deadline is appreciated.\n" +
                    "\n" +
                    "We hope that you found the book helpful and informative. Should you require further assistance or have any queries, please do not hesitate to contact us.\n" +
                    "\n" +
                    "Thank you for using our library services. We look forward to seeing you soon.\n" +
                    "\n" +
                    "Best regards,\n" +
                    "\n" +
                    "S.M.Karnale." ;
        }

        book.setBookIssued(false);
        card.getBookList().remove(book);
        transaction.setIsBookIssued(BookIssueStatus.RETURNED);

        cardRepository.save(card);
        bookRepository.save(book);
        ReturnBookResponseDto returnBookResponseDto = new ReturnBookResponseDto();
        returnBookResponseDto.setStudentName(card.getStudent().getName());
        returnBookResponseDto.setBookName(book.getName());


        message.setFrom("noreply.library.management.2023@gmail.com");
        message.setTo(card.getStudent().getEmail());
        message.setText(text);
        emailSender.send(message);

        return returnBookResponseDto;
    }


    //return book api
}
