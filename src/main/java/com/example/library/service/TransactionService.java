package com.example.library.service;

import com.example.library.dto.BookDTO;
import com.example.library.dto.TransactionDTO;
import com.example.library.dto.UserDTO;
import com.example.library.mapper.BookMapper;
import com.example.library.mapper.TransactionMapper;
import com.example.library.mapper.UserMapper;
import com.example.library.repository.TransactionRepository;
import com.example.library.entity.Book;
import com.example.library.entity.Transaction;
import com.example.library.entity.User;
import com.example.library.exception.BookAlreadyReturnedException;
import com.example.library.exception.BookNotAvailableException;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserActivityService userActivityService; // Inject the NoSQL service

    // Get all transactions
    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(TransactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Borrow a book
    public TransactionDTO borrowBook(Long bookId, Long userId) {
        // Fetch the BookDTO from the BookService
        BookDTO bookDTO = bookService.getBookById(bookId);

        // Convert BookDTO to Book entity using BookMapper
        Book book = BookMapper.toEntity(bookDTO);

        // Check if the book is available
        if (!book.isAvailable()) {
            throw new BookNotAvailableException("Book is not available");
        }

        // Fetch the UserDTO from the UserService
        UserDTO userDTO = userService.getUserById(userId);

        // Convert UserDTO to User entity using UserMapper
        User user = UserMapper.toEntity(userDTO);

        // Create the transaction
        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setUser(user);
        transaction.setBorrowDate(LocalDate.now());

        // Mark the book as unavailable
        book.setAvailable(false);
        bookService.updateBook(bookId, BookMapper.toDTO(book)); // Update the book using the BookDTO

        // Save the transaction
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Log the activity in MongoDB
        userActivityService.logUserActivity(
                userId.toString(), // Convert userId to String
                "borrow", // Activity type
                "book_id: '" + bookId + "'" // Activity details
        );

        return TransactionMapper.toDTO(savedTransaction);
    }

    // Return a book
    public TransactionDTO returnBook(Long transactionId) {
        // Fetch the transaction
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + transactionId));

        // Check if the book has already been returned
        if (transaction.getReturnDate() != null) {
            throw new BookAlreadyReturnedException("Book has already been returned");
        }

        // Fetch the Book entity from the transaction
        Book book = transaction.getBook();

        // Mark the book as available
        book.setAvailable(true);

        // Convert the Book entity to BookDTO for updating
        BookDTO bookDTO = BookMapper.toDTO(book);
        bookService.updateBook(book.getId(), bookDTO); // Update the book using the BookDTO

        // Update the return date
        transaction.setReturnDate(LocalDate.now());

        // Save the updated transaction
        Transaction updatedTransaction = transactionRepository.save(transaction);

        // Log the activity in MongoDB
        userActivityService.logUserActivity(
                transaction.getUser().getId().toString(), // Convert userId to String
                "return", // Activity type
                "book_id: '" + transaction.getBook().getId() + "'" // Activity details
        );

        return TransactionMapper.toDTO(updatedTransaction);
    }
}