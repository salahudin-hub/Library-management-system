package com.example.library.mapper;

import com.example.library.dto.BookMetadataDTO;
import com.example.library.entity.BookMetadata;

import java.util.stream.Collectors;


public class BookMetadataMapper {
    public static BookMetadataDTO toDTO(BookMetadata bookMetadata) {
        BookMetadataDTO dto = new BookMetadataDTO();
        dto.setId(bookMetadata.getId());
        dto.setBookId(bookMetadata.getBookId());
        dto.setReviews(bookMetadata.getReviews().stream()
                .map(review -> {
                    BookMetadataDTO.ReviewDTO reviewDTO = new BookMetadataDTO.ReviewDTO();
                    reviewDTO.setUserId(review.getUserId());
                    reviewDTO.setRating(review.getRating());
                    reviewDTO.setComment(review.getComment());
                    return reviewDTO;
                })
                .collect(Collectors.toList()));
        dto.setTags(bookMetadata.getTags());
        dto.setAverageRating(bookMetadata.getAverageRating());
        return dto;
    }

    public static BookMetadata toEntity(BookMetadataDTO dto) {
        BookMetadata bookMetadata = new BookMetadata();
        bookMetadata.setBookId(dto.getBookId());
        bookMetadata.setReviews(dto.getReviews().stream()
                .map(reviewDTO -> {
                    BookMetadata.Review review = new BookMetadata.Review();
                    review.setUserId(reviewDTO.getUserId());
                    review.setRating(reviewDTO.getRating());
                    review.setComment(reviewDTO.getComment());
                    return review;
                })
                .collect(Collectors.toList()));
        bookMetadata.setTags(dto.getTags());
        bookMetadata.setAverageRating(dto.getAverageRating());
        return bookMetadata;
    }


    public static BookMetadata.Review toEntity(BookMetadataDTO.ReviewDTO reviewDTO) {
        BookMetadata.Review review = new BookMetadata.Review();
        review.setUserId(reviewDTO.getUserId());
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        return review;
    }
}