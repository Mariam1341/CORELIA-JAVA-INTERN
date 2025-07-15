package com.corelia.library.mapper;

import com.corelia.library.dto.author.AuthorRequestDTO;
import com.corelia.library.dto.author.AuthorResponseDTO;
import com.corelia.library.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toEntity(AuthorRequestDTO dto);
    AuthorResponseDTO toDto(Author entity);
}
