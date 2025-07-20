package com.corelia.library.mapper;

import com.corelia.library.dto.author.AuthorRequestDTO;
import com.corelia.library.dto.author.AuthorResponseDTO;
import com.corelia.library.dto.book.BookResponseDTO;
import com.corelia.library.entity.Author;
import com.corelia.library.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = BookMapper.class)
public interface AuthorMapper {
    @Mapping(source = "books", target = "books", qualifiedByName = "mapBookTitles")
    AuthorResponseDTO toDto(Author author);

    @Named("mapBookTitles")
    default List<String> mapBookTitles(List<Book> books) {
        if (books == null) return null;
        return books.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    Author toEntity(AuthorRequestDTO dto);

}
