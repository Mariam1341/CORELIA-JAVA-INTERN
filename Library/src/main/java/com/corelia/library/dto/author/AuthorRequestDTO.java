package com.corelia.library.dto.author;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequestDTO {
    @NotBlank(message = "Author name is required")
    private String name;
}
