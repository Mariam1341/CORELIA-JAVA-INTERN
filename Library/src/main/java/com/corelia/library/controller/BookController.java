package com.corelia.library.controller;

import com.corelia.library.dto.ResponseDTO;
import com.corelia.library.dto.book.BookRequestDTO;
import com.corelia.library.dto.book.BookResponseDTO;
import com.corelia.library.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ResponseDTO<BookResponseDTO>> createBook(@RequestBody @Valid BookRequestDTO dto) {
        BookResponseDTO book = bookService.createBook(dto);
        return ResponseEntity.ok(
                ResponseDTO.<BookResponseDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Book created successfully")
                        .data(book)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<BookResponseDTO>> getBook(@PathVariable Long id) {
        BookResponseDTO book = bookService.getBookById(id);
        return ResponseEntity.ok(
                ResponseDTO.<BookResponseDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Book retrieved successfully")
                        .data(book)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<BookResponseDTO>>> getAllBooks() {
        List<BookResponseDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(
                ResponseDTO.<List<BookResponseDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("All books retrieved")
                        .data(books)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<BookResponseDTO>> updateBook(@PathVariable Long id, @RequestBody BookRequestDTO dto) {
        BookResponseDTO updated = bookService.updateBook(id, dto);
        return ResponseEntity.ok(
                ResponseDTO.<BookResponseDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Book updated successfully")
                        .data(updated)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .status(HttpStatus.OK.value())
                        .message("Book deleted successfully")
                        .data(true)
                        .build()
        );
    }

    @GetMapping("/author/{name}")
    public ResponseEntity<ResponseDTO<List<BookResponseDTO>>> getBooksByAuthor(@PathVariable String name) {
        List<BookResponseDTO> books = bookService.getBooksByAuthorName(name);
        return ResponseEntity.ok(
                ResponseDTO.<List<BookResponseDTO>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Books retrieved for author: " + name)
                        .data(books)
                        .build()
        );
    }
}

