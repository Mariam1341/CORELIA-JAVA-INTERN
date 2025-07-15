package com.corelia.library.service;

import com.corelia.library.dto.book.BookRequestDTO;
import com.corelia.library.dto.book.BookResponseDTO;
import com.corelia.library.entity.Author;
import com.corelia.library.entity.Book;

import com.corelia.library.exception.custom.BookNotFoundException;
import com.corelia.library.mapper.BookMapper;
import com.corelia.library.repository.AuthorRepository;
import com.corelia.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;

    public BookResponseDTO addBook(BookRequestDTO dto){
        Book book = bookMapper.toEntity(dto);
        Author author = authorRepository.findByName(book.getAuthor().getName());
        book.setAuthor(author);
        return bookMapper.toDto(bookRepository.save(book));
    }

    public BookResponseDTO updateBook(Long id, BookRequestDTO dto){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with this ID: "+ id));
        Author author = authorRepository.findByName(book.getAuthor().getName());
        book.setAuthor(author);

        return null;
//        return bookMapper.toDto(bookRepository.save());
    }




}
