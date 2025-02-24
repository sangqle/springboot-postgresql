package com.cabin.example.controller;

import com.cabin.example.api.ApiResponse;
import com.cabin.example.api.PageResponse;
import com.cabin.example.dto.BookCreateDTO;
import com.cabin.example.dto.BookResponseDTO;
import com.cabin.example.dto.BookUpdateDTO;
import com.cabin.example.entity.Book;
import com.cabin.example.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<BookResponseDTO>>> findAll(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page, @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {

        PageResponse<BookResponseDTO> byIsDeletedFalse = bookService.findByIsDeletedFalse(PageRequest.of(page, size));
        return ResponseEntity.ok(ApiResponse.success(byIsDeletedFalse));
    }

    @GetMapping("/{id}")
    public Optional<Book> findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    // create a book
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    public BookResponseDTO create(@RequestBody @Valid BookCreateDTO bookRequestDTO) {
        return bookService.save(bookRequestDTO);
    }

    @PutMapping("/{id}")
    public BookResponseDTO update(@PathVariable(name = "id") Long id, @RequestBody @Valid BookUpdateDTO bookRequestDTO) throws Exception {
        return bookService.updateBook(id, bookRequestDTO);
    }

    // delete a book
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @GetMapping("/find/title/{title}")
    public List<Book> findByTitle(@PathVariable String title) {
        return bookService.findByTitle(title);
    }

    @GetMapping("/find/date-after/{date}")
    public List<Book> findByPublishedDateAfter(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return bookService.findByPublishedDateAfter(date);
    }

}