package com.example.demo.book.controllers;

import com.example.demo.book.model.dto.BookDto;
import com.example.demo.book.model.entity.BookEntity;
import com.example.demo.book.model.exception.BookCreationException;
import com.example.demo.book.services.BookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto.PostOutput createBook(@Valid @RequestBody BookDto.PostInput postInput) throws BookCreationException {
        return bookService.createBook(postInput);
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto.getOutput> getAll() {
        return bookService.getAllBooks();
    }

    @GetMapping("/getById")
    @ResponseStatus(HttpStatus.OK)
    public BookDto.getOutput getById(@RequestParam Long bookId) {
        return bookService.getById(bookId);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public BookDto.PostOutput update(@RequestBody BookDto.PostInput bookEntity) throws BookCreationException {
        return bookService.updateBook(bookEntity);
    }

    @DeleteMapping("/Delete")
    public void deleteById(@RequestParam Long bookId) throws BookCreationException {
        bookService.deleteById(bookId);
    }

    @GetMapping("/findByTitle")
    public List<BookDto.getOutput> searchBookByTitle(@RequestParam String title){
        return bookService.findBookByTitle(title);
    }

}
