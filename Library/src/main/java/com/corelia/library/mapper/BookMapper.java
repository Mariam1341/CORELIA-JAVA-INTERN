package com.corelia.library.mapper;

import com.corelia.library.dto.book.BookRequestDTO;
import com.corelia.library.dto.book.BookResponseDTO;
import com.corelia.library.entity.Author;
import com.corelia.library.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper instance = Mappers.getMapper(BookMapper.class);
    Book toEntity(BookRequestDTO dto);

    @Mapping(source = "author.getName()" ,target = "authorName")
    default BookResponseDTO toDto(Book book) {
        if (book == null) return null;
        return BookResponseDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .category(book.getCategory())
                .authorName(book.getAuthor() != null ? book.getAuthor().getName() : null)
                .build();
    }

}
