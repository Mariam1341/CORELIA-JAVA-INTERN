package com.corelia.library.dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO<T> {
    private int status;
    private String message;
    private T data;
}
