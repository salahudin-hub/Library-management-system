package com.example.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transactions")

public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    //get all transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();

    }
    @PostMapping("borrow")
    public Transaction borrowBook(@RequestParam Long bookId, @RequestParam Long userId) {
        return transactionService.borrowBook(bookId, userId);
    }
    @PostMapping("/return/{transactionId}")
    public Transaction returnBook(@PathVariable Long transactionId) {
        return transactionService.returnBook(transactionId);
    }
}
