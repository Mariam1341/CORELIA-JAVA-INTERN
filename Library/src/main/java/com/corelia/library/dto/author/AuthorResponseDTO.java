package com.corelia.library.dto.author;

import com.corelia.library.entity.Book;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponseDTO {
    private Long id;
    private String name;
    private String email;
    private List<Book> books;
}
