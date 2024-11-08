package com.example.courzeloproject.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginEmailRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;


}
