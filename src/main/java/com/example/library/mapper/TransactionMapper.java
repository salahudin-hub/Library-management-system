package com.example.library.mapper;

import com.example.library.dto.TransactionDTO;
import com.example.library.entity.Transaction;

public class TransactionMapper {
    public static TransactionDTO toDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setBookId(transaction.getBook().getId());
        transactionDTO.setUserId(transaction.getUser().getId());
        transactionDTO.setBorrowDate(transaction.getBorrowDate());
        transactionDTO.setReturnDate(transaction.getReturnDate());
        return transactionDTO;
    }

    public static Transaction toEntity(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionDTO.getId());
        // Note: You'll need to fetch the Book and User entities using their IDs
        // and set them in the Transaction entity.
        return transaction;
    }
}