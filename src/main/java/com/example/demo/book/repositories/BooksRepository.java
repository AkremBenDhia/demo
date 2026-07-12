package com.example.demo.book.repositories;

import com.example.demo.book.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findByTitleAndPages(String title, Long pages);

    List<BookEntity> findByTitle(String title);

    List<BookEntity> findByTitleStartsWith(String string);
}
