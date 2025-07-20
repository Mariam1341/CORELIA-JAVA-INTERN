package com.corelia.library.mapper;

import com.corelia.library.dto.book.BookRequestDTO;
import com.corelia.library.dto.book.BookResponseDTO;
import com.corelia.library.entity.Author;
import com.corelia.library.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "author", target = "authorName", qualifiedByName = "mapAuthorName")
    BookResponseDTO toDto(Book book);

    Book toEntity(BookRequestDTO dto);

    @Named("mapAuthorName")
    default String mapAuthorName(Author author) {
        return author != null ? author.getName() : null;
    }
}