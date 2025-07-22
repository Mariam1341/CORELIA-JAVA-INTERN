package com.corelia.library.service;



import com.corelia.library.dto.author.AuthorRequestDTO;
import com.corelia.library.dto.author.AuthorResponseDTO;
import com.corelia.library.dto.book.BookRequestDTO;
import com.corelia.library.dto.book.BookResponseDTO;
import com.corelia.library.entity.Author;
import com.corelia.library.entity.Book;
import com.corelia.library.exception.custom.AuthorNotFoundException;
import com.corelia.library.exception.custom.BookNotFoundException;
import com.corelia.library.mapper.AuthorMapper;
import com.corelia.library.mapper.BookMapper;
import com.corelia.library.repository.AuthorRepository;
import com.corelia.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final BookMapper bookMapper;
    private final BookService bookService;

    private final AuthorMapper authorMapper;

    public AuthorResponseDTO addAuthor(AuthorRequestDTO dto){
        Author author = authorMapper.toEntity(dto, bookRepository);
        return authorMapper.toDto(authorRepository.save(author));
    }
    public AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO dto){
        Author author = authorRepository.findById(id)
                .orElseThrow(()-> new AuthorNotFoundException("Author not found with this id:" + id));

        author.setEmail(dto.getEmail());
        author.setName(dto.getName());

        return authorMapper.toDto(authorRepository.save(author));
    }
    public void deleteAuthor(Long id){
        authorRepository.deleteById(id);
    }

    public AuthorResponseDTO getAuthorById(Long id){
        return authorMapper.toDto(authorRepository.findById(id)
                .orElseThrow(()-> new AuthorNotFoundException("Author not found with this id:" + id)));
    }
    public AuthorResponseDTO getAuthorByName(String name){
        return authorMapper.toDto(authorRepository.findByName(name)
                .orElseThrow(()-> new AuthorNotFoundException("Author not found with this id:" + name)));
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();

    }


}
