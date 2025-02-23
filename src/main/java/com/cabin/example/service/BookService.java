package com.cabin.example.service;

import com.cabin.example.dto.BookCreateDTO;
import com.cabin.example.dto.BookResponseDTO;
import com.cabin.example.dto.BookUpdateDTO;
import com.cabin.example.entity.Book;
import com.cabin.example.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public BookResponseDTO save(BookCreateDTO book) {
        Book newBook = new Book();
        newBook.setTitle(book.getTitle());
        newBook.setPrice(book.getPrice());

        Book saveBook = bookRepository.save(newBook);
        return new BookResponseDTO(saveBook.getId(), saveBook.getTitle(), saveBook.getPrice(), saveBook.getPublishDate(), saveBook.getCreatedAt());
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findByPublishedDateAfter(LocalDate date) {
        return bookRepository.findByPublishedDateAfter(date);
    }

    public Page<BookResponseDTO> findByIsDeletedFalse(Pageable pageable) {
        Page<Book> byIsDeletedFalse = bookRepository.findByIsDeletedFalse(pageable);
        return byIsDeletedFalse.map(book -> new BookResponseDTO(book.getId(), book.getTitle(), book.getPrice(), book.getPublishDate(), book.getCreatedAt()));
    }

    @Transactional
    public BookResponseDTO updateBook(Long id, BookUpdateDTO bookUpdateDTO) throws Exception {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new Exception("Book not found with id: " + id));

        // Update only the fields that are allowed to change
        if (bookUpdateDTO.getTitle() != null) {
            book.setTitle(bookUpdateDTO.getTitle());
        }
        if (bookUpdateDTO.getPrice() != null) {
            book.setPrice(bookUpdateDTO.getPrice());
        }

        // No need to explicitly call save() if the entity is managed within a transaction.
        // However, calling save() is fine if you want to be explicit.
        Book updatedBook = bookRepository.save(book);

        return new BookResponseDTO(
                updatedBook.getId(),
                updatedBook.getTitle(),
                updatedBook.getPrice(),
                updatedBook.getPublishDate(),
                updatedBook.getCreatedAt()
        );
    }
}