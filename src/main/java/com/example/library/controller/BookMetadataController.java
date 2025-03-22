package com.example.library.controller;

import com.example.library.dto.BookMetadataDTO;
import com.example.library.mapper.BookMetadataMapper;
import com.example.library.service.BookMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book-metadata")
public class BookMetadataController {
    @Autowired
    private BookMetadataService bookMetadataService;

    @PostMapping("/{bookId}/reviews")
    public BookMetadataDTO addReview(@PathVariable String bookId, @RequestBody BookMetadataDTO.ReviewDTO reviewDTO) {
        return BookMetadataMapper.toDTO(
                bookMetadataService.addReview(bookId, BookMetadataMapper.toEntity(reviewDTO))
        );
    }

    @GetMapping("/{bookId}")
    public BookMetadataDTO getBookMetadata(@PathVariable String bookId) {
        return BookMetadataMapper.toDTO(bookMetadataService.getBookMetadata(bookId));
    }
}