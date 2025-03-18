package com.example.library.service;

import com.example.library.dto.BookDTO;
import com.example.library.mapper.BookMapper;
import com.example.library.repository.BookRepository;
import com.example.library.entity.Book;
import com.example.library.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

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
        Book book = BookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return BookMapper.toDTO(savedBook);
    }

    // Update a book
    public BookDTO updateBook(Long id,BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setAvailable(bookDTO.isAvailable());

        Book updatedBook = bookRepository.save(existingBook);
        return BookMapper.toDTO(updatedBook);
    }

    // Delete a book
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}