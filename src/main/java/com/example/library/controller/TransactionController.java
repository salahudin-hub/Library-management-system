package com.example.library.controller;

import com.example.library.dto.TransactionDTO;
import com.example.library.entity.Transaction;
import com.example.library.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    @GetMapping
    public List<TransactionDTO> getAllTransactions() {
        return transactionService.getAllTransactions();
    }


    @PostMapping("/borrow")
    public TransactionDTO borrowBook(@RequestParam Long bookId, @RequestParam Long userId) {
        return transactionService.borrowBook(bookId, userId);
    }


    @PostMapping("/return/{transactionId}")
    public TransactionDTO returnBook(@PathVariable Long transactionId) {
        return transactionService.returnBook(transactionId);
    }
}