package com.example.demo.book.services;

import com.example.demo.book.model.entity.BookEntity;
import com.example.demo.book.model.dto.BookDto;
import com.example.demo.book.model.exception.BookCreationException;
import com.example.demo.book.repositories.BooksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BooksRepository booksRepository;

    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public BookDto.PostOutput createBook(BookDto.PostInput postInput) throws BookCreationException {


        if (postInput.getTitle() == null || postInput.getTitle().isBlank()) {
            throw new BookCreationException("Title should not be empty");
        }

        Optional<BookEntity> existedBook =
                booksRepository.findByTitleAndPages(postInput.getTitle(), postInput.getPages());

        if (existedBook.isPresent()) {
            throw new BookCreationException("Book already exists");
        }

        BookEntity book = BookEntity.builder()
                .title(postInput.getTitle())
                .pages(postInput.getPages())
                .build();

        booksRepository.save(book);

        return new BookDto.PostOutput(book.getTitle(), book.getPages());
    }

    public List<BookDto.getOutput> getAllBooks() {

        return booksRepository.findAll().stream().map(
                it -> BookDto.getOutput.builder().title(it.getTitle()).pages(it.getPages())
                        .build()
        ).toList();

    }

    public BookDto.getOutput getById(Long bookId) throws BookCreationException {

        Optional<BookEntity> bookEntity = booksRepository.findById(bookId);

        if (bookEntity.isEmpty()) {
            throw new BookCreationException("Book with id " + bookId + " does not exist !");
        }

        BookDto.getOutput getOutput = BookDto.getOutput.
                builder().id(bookEntity.get().getId()).title(bookEntity.get().getTitle()).pages(bookEntity.get().getPages())
                .build();

        return getOutput;
    }

    public BookDto.PostOutput updateBook(BookDto.PostInput bookEntity) throws BookCreationException {

        Optional<BookEntity> existingBook = booksRepository.findById(bookEntity.getId());

        if (existingBook.isEmpty()) {
            throw new BookCreationException("Book Does Not Exist !");
        }

        existingBook.get().setTitle(bookEntity.getTitle());
        existingBook.get().setPages(bookEntity.getPages());

        booksRepository.save(existingBook.get());

        BookDto.PostOutput postOutput = BookDto.PostOutput.builder().title(existingBook.get().getTitle())
                .pages(existingBook.get().getPages()).build();

        return postOutput;
    }

    public void deleteById(Long id) throws BookCreationException {
        Optional<BookEntity> bookEntity = booksRepository.findById(id);

        if (bookEntity.isEmpty()) {
            throw new BookCreationException("Book with id " + id + " does not exist");
        }

        booksRepository.deleteById(id);
    }

    public List<BookDto.getOutput> findBookByTitle(String title) throws BookCreationException {

        if (title == null || title.isEmpty()) {
            throw new BookCreationException("Book shold not be null or empty");
        }

        List<BookEntity> bookEntity = booksRepository.findByTitleStartsWith(title);

        if (bookEntity == null || bookEntity.isEmpty()) {
            throw new BookCreationException("Book with title " + title + " do not exist !");
        }

        return bookEntity.stream().map(it -> BookDto.getOutput.builder().title(it.getTitle()).build()).toList();
    }
}
