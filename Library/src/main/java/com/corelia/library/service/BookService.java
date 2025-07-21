package com.corelia.library.service;

import com.corelia.library.dto.book.BookRequestDTO;
import com.corelia.library.dto.book.BookResponseDTO;
import com.corelia.library.entity.Author;
import com.corelia.library.entity.Book;

import com.corelia.library.exception.custom.AuthorNotFoundException;
import com.corelia.library.exception.custom.BookNotFoundException;
import com.corelia.library.mapper.BookMapper;
import com.corelia.library.repository.AuthorRepository;
import com.corelia.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;

    public BookResponseDTO createBook(BookRequestDTO dto){
        Book book = bookMapper.toEntity(dto,authorRepository);
        Author author = authorRepository.findByName(dto.getAuthorName())
                .orElseThrow(()-> new AuthorNotFoundException("Author not found with this name: " + dto.getAuthorName()));
        book.setAuthor(author);
        return bookMapper.toDto(bookRepository.save(book));
    }

    public BookResponseDTO updateBook(Long id, BookRequestDTO dto){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with this ID: "+ id));
        Author author = authorRepository.findByName(dto.getAuthorName())
                .orElseThrow(()-> new AuthorNotFoundException("Author not found with this name: " + dto.getAuthorName()));

        book.setAuthor(author);
        book.setCategory(dto.getCategory());
        book.setTitle(dto.getTitle());

        return bookMapper.toDto(bookRepository.save(book));
    }
    public void deleteBook(Long id){
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with ID: " + id);
        }
        bookRepository.deleteById(id);
    }
    public BookResponseDTO getBookById(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with this ID: "+ id));
        return bookMapper.toDto(book);
    }

    public BookResponseDTO getBookByTitle(String title){
        Book book = bookRepository.findByTitle(title)
                .orElseThrow(() -> new BookNotFoundException("Book not found with this title : "+ title));
        return bookMapper.toDto(book);
    }
    public List<BookResponseDTO> getAllBooks(){
        List<BookResponseDTO> ls = bookRepository.findAll().stream().map(b-> bookMapper.toDto(b)).toList();
        return ls;
    }
    public List<BookResponseDTO> getBooksByAuthorName(String authorName) {
        List<Book> books = bookRepository.findBooksByAuthorName(authorName);

        if (books.isEmpty()) {
            throw new BookNotFoundException("No books found for author: " + authorName);
        }

        return books.stream()
                .map(book -> bookMapper.toDto(book))
                .toList();
    }

}
