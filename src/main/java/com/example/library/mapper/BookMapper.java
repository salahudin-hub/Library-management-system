package com.example.library.mapper;

import com.example.library.dto.BookDTO;
import com.example.library.entity.Book;

public class BookMapper {
    public static BookDTO toDTO(Book book) {


        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthorId(book.getAuthor().getId());
        bookDTO.setAvailable(book.isAvailable());
        return bookDTO;
    }

    public static Book toEntity(BookDTO bookDTO) {

        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());

        book.setAvailable(bookDTO.isAvailable());
        return book;
    }
}