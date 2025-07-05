package com.me.post_share_api.dto.user_dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "El username no puede estar vacío.")
    @Size(min = 8, max = 255, message = "El usernane debe de tener como mínimo 8 caracteres hasta un máximo de 255.")
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacía.")
    @Size(min = 8, max = 255, message = "La contraseña debe de tener como mínimo 8 caracteres hasta un máximo de 255.")
    private String password;

}
