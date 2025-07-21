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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
