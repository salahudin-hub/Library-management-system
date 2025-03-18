package com.example.library;
import com.example.library.exception.BookAlreadyReturnedException;
import com.example.library.exception.BookNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.library.exception.ResourceNotFoundException;
import java.util.List;
import java.time.LocalDate;

@Service

public class TransactionService {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository transactionRepository;

    //get all transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    //Borrow a book

    public Transaction borrowBook(Long bookId, Long userId) {
        Book book = bookService.getBookById(bookId);
        if (!book.isAvailable()) {
            throw new BookNotAvailableException("Book is not available");
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        Transaction transaction = new Transaction();
        transaction.setBookId(bookId);
        transaction.setUserId(userId);
        transaction.setBorrowDate(LocalDate.now());

        book.setAvailable(false);
        bookService.updateBook(book);

        return transactionRepository.save(transaction);
    }

    public Transaction returnBook(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + transactionId));

        if (transaction.getReturnDate() != null) {
            throw new BookAlreadyReturnedException("Book has already been returned");
        }

        Book book = bookService.getBookById(transaction.getBookId());
        book.setAvailable(true);
        bookService.updateBook(book);

        transaction.setReturnDate(LocalDate.now());
        return transactionRepository.save(transaction);
    }
}
