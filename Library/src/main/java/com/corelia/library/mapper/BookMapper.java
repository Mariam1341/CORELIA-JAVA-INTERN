package com.corelia.library.mapper;

import com.corelia.library.dto.book.BookRequestDTO;
import com.corelia.library.dto.book.BookResponseDTO;
import com.corelia.library.entity.Author;
import com.corelia.library.entity.Book;
import com.corelia.library.repository.AuthorRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public interface BookMapper {


    @Mapping(source = "author", target = "authorName", qualifiedByName = "mapAuthorName")
    BookResponseDTO toDto(Book book);

    @Mapping(source = "authorName", target = "author", qualifiedByName = "mapAuthorByName")
    Book toEntity(BookRequestDTO dto, @Context AuthorRepository authorRepository);

    @Named("mapAuthorName")
    default String mapAuthorName(Author author) {
        return author != null ? author.getName() : null;
    }

    @Named("mapAuthorByName")
    default Author mapAuthorByName(String name, @Context AuthorRepository authorRepository) {
        return authorRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Author not found with name: " + name));
    }
}