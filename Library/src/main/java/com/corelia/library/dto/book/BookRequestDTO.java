package com.corelia.library.dto.book;

import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    private String category;

    private String authorName;

}
