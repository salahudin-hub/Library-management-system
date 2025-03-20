package com.example.library.repository;

import java.util.Optional;
import com.example.library.entity.BookMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookMetadataRepository extends MongoRepository<BookMetadata, String> {

    Optional<BookMetadata> findByBookId(String bookId);
}

