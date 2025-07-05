package com.me.post_share_api.dto.post_dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDTO {

    @NotBlank(message = "No se aceptan publicaciones vacías.")
    @Size(min = 1, max = 2000, message = "La publicación debe de tener entre 1 a 2000 carácteres.")
    private String content;

}
