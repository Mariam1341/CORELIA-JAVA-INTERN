package com.corelia.library.mapper;

import com.corelia.library.dto.author.AuthorRequestDTO;
import com.corelia.library.dto.author.AuthorResponseDTO;
import com.corelia.library.dto.book.BookResponseDTO;
import com.corelia.library.entity.Author;
import com.corelia.library.entity.Book;
import com.corelia.library.repository.BookRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = BookMapper.class)
public interface AuthorMapper {
        @Mapping(source = "books", target = "books", qualifiedByName = "mapBookTitlesToBooks")
        Author toEntity(AuthorRequestDTO dto, @Context BookRepository bookRepository);

        @Mapping(source = "books", target = "books", qualifiedByName = "mapBooksToTitles")
        AuthorResponseDTO toDto(Author author);

        @Named("mapBooksToTitles")
        default List<String> mapBooksToTitles(List<Book> books) {
            if (books == null) return null;
            return books.stream()
                    .map(Book::getTitle)
                    .collect(Collectors.toList());
        }

        @Named("mapBookTitlesToBooks")
        default List<Book> mapBookTitlesToBooks(List<String> titles, @Context BookRepository bookRepository) {
            if (titles == null) return null;
            return titles.stream()
                    .map(title -> bookRepository.findByTitle(title)
                            .orElseGet(() -> {
                                Book b = new Book();
                                b.setTitle(title);
                                return b;
                            }))
                    .collect(Collectors.toList());
        }


}
