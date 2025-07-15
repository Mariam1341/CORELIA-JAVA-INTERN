package com.corelia.library.dto.book;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {
    private Long id;
    private String title;
    private String category;
    private String authorName;
}
