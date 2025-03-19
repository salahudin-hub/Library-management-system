package com.example.library.mapper;

import com.example.library.dto.BookDTO;
import com.example.library.entity.Book;
import com.example.library.entity.Author;

public class BookMapper {
    public static BookDTO toDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthorId(book.getAuthor().getId()); // Map authorId
        bookDTO.setAvailable(book.isAvailable());
        return bookDTO;
    }

    public static Book toEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        // Fetch the Author entity using authorId (you’ll need to inject AuthorRepository)
        book.setAvailable(bookDTO.isAvailable());
        return book;
    }
}