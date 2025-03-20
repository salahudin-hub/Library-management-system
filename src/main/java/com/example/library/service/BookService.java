package com.example.library.service;

import com.example.library.dto.BookDTO;
import com.example.library.entity.Book;
import com.example.library.entity.Author;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.mapper.BookMapper;
import com.example.library.repository.BookRepository;
import com.example.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    // Get all books
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get a book by ID
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return BookMapper.toDTO(book);
    }

    // Add a new book
    public BookDTO addBook(BookDTO bookDTO) {
        // Debug log: Print the received isAvailable value
        System.out.println("Received isAvailable in addBook: " + bookDTO.isAvailable());

        // Fetch the Author entity using authorId
        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + bookDTO.getAuthorId()));

        // Create the Book entity
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(author);
        book.setAvailable(bookDTO.isAvailable());

        // Debug log: Print the isAvailable value before saving
        System.out.println("Book entity isAvailable before saving: " + book.isAvailable());

        // Save the book
        Book savedBook = bookRepository.save(book);

        // Debug log: Print the isAvailable value after saving
        System.out.println("Book entity isAvailable after saving: " + savedBook.isAvailable());

        return BookMapper.toDTO(savedBook);
    }

    // Update a book
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        // Debug log: Print the received isAvailable value
        System.out.println("Received isAvailable in updateBook: " + bookDTO.isAvailable());

        // Fetch the existing book
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        // Fetch the Author entity using authorId
        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + bookDTO.getAuthorId()));

        // Update the book details
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(author);
        existingBook.setAvailable(bookDTO.isAvailable());

        // Debug log: Print the isAvailable value before saving
        System.out.println("Book entity isAvailable before saving: " + existingBook.isAvailable());

        // Save the updated book
        Book updatedBook = bookRepository.save(existingBook);

        // Debug log: Print the isAvailable value after saving
        System.out.println("Book entity isAvailable after saving: " + updatedBook.isAvailable());

        return BookMapper.toDTO(updatedBook);
    }

    // Delete a book
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}