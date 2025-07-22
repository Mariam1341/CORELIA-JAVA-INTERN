package com.corelia.library.dto.author;


import com.corelia.library.entity.Book;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequestDTO {
    @NotBlank(message = "Author name is required")
    private String name;
    @Email(message = "Email should be valid")
    private String email;
    private List<String> books;

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }
}
