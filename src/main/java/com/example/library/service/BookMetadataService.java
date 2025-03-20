package com.example.library.service;

import com.example.library.entity.BookMetadata;
import com.example.library.repository.BookMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class BookMetadataService {
    @Autowired
    private BookMetadataRepository bookMetadataRepository;

    public void addReview(String bookId, BookMetadata.Review review) {
        BookMetadata bookMetadata = bookMetadataRepository.findByBookId(bookId)
                .orElse(new BookMetadata());
        bookMetadata.setBookId(bookId);
        bookMetadata.getReviews().add(review);
        bookMetadataRepository.save(bookMetadata);
    }
}
