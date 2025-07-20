package com.corelia.library.controller;

import com.corelia.library.dto.ResponseDTO;
import com.corelia.library.dto.author.AuthorRequestDTO;
import com.corelia.library.dto.author.AuthorResponseDTO;
import com.corelia.library.entity.Author;
import com.corelia.library.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<ResponseDTO<AuthorResponseDTO>> addAuthor(@RequestBody @Valid AuthorRequestDTO dto) {
        AuthorResponseDTO author = authorService.addAuthor(dto);
        return ResponseEntity.ok(
                ResponseDTO.<AuthorResponseDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Author added successfully")
                        .data(author)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<AuthorResponseDTO>> getAuthor(@PathVariable Long id) {
        AuthorResponseDTO author = authorService.getAuthorById(id);
        return ResponseEntity.ok(
                ResponseDTO.<AuthorResponseDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Author retrieved successfully")
                        .data(author)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<Author>>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(
                ResponseDTO.<List<Author>>builder()
                        .status(HttpStatus.OK.value())
                        .message("All authors retrieved")
                        .data(authors)
                        .build()
        );
    }
//    @GetMapping
//    public ResponseEntity<ResponseDTO<List<AuthorResponseDTO>>> getAllAuthors() {
//        List<AuthorResponseDTO> authors = authorService.getAllAuthors();
//        return ResponseEntity.ok(
//                ResponseDTO.<List<AuthorResponseDTO>>builder()
//                        .status(HttpStatus.OK.value())
//                        .message("All authors retrieved")
//                        .data(authors)
//                        .build()
//        );
//    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<AuthorResponseDTO>> updateAuthor(@PathVariable Long id, @RequestBody AuthorRequestDTO dto) {
        AuthorResponseDTO updated = authorService.updateAuthor(id, dto);
        return ResponseEntity.ok(
                ResponseDTO.<AuthorResponseDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Author updated successfully")
                        .data(updated)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .status(HttpStatus.OK.value())
                        .message("Author deleted successfully")
                        .data(null)
                        .build()
        );
    }



}

