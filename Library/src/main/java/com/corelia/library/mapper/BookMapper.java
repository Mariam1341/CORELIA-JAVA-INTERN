package com.corelia.library.mapper;

import com.corelia.library.dto.book.BookRequestDTO;
import com.corelia.library.dto.book.BookResponseDTO;
import com.corelia.library.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper instance = Mappers.getMapper(BookMapper.class);
    Book toEntity(BookRequestDTO dto);

    @Mapping(source =  "author.name" ,target = "authorName")
    BookResponseDTO toDto(Book entity);
}
