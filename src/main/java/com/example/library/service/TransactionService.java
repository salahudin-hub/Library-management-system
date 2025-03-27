package com.example.library.service;

import com.example.library.dto.BookDTO;
import com.example.library.dto.TransactionDTO;
import com.example.library.dto.UserDTO;
import com.example.library.mapper.BookMapper;
import com.example.library.mapper.TransactionMapper;
import com.example.library.mapper.UserMapper;
import com.example.library.repository.TransactionRepository;
import com.example.library.repository.BookRepository;
import com.example.library.entity.Book;
import com.example.library.entity.Transaction;
import com.example.library.entity.User;
import com.example.library.exception.BookAlreadyReturnedException;
import com.example.library.exception.BookNotAvailableException;
import com.example.library.exception.ResourceNotFoundException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionService {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserActivityService userActivityService;

    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(TransactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TransactionDTO borrowBook(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Hibernate.initialize(book.getAuthor());

        if (!book.isAvailable()) {
            throw new BookNotAvailableException("Book is not available");
        }

        UserDTO userDTO = userService.getUserById(userId);
        User user = UserMapper.toEntity(userDTO);

        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setUser(user);
        transaction.setBorrowDate(LocalDate.now());

        book.setAvailable(false);
        bookRepository.save(book);

        Transaction savedTransaction = transactionRepository.save(transaction);

        userActivityService.logUserActivity(
                userId.toString(),
                "borrow",
                "book_id: '" + bookId + "'"
        );

        return TransactionMapper.toDTO(savedTransaction);
    }

    public TransactionDTO returnBook(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        if (transaction.getReturnDate() != null) {
            throw new BookAlreadyReturnedException("Book already returned");
        }

        Book book = transaction.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        transaction.setReturnDate(LocalDate.now());
        Transaction updatedTransaction = transactionRepository.save(transaction);

        userActivityService.logUserActivity(
                transaction.getUser().getId().toString(),
                "return",
                "book_id: '" + book.getId() + "'"
        );

        return TransactionMapper.toDTO(updatedTransaction);
    }
}