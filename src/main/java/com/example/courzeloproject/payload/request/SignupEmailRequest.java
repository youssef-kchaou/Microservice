package com.example.courzeloproject.payload.request;

import com.example.courzeloproject.Entite.ERole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignupEmailRequest {
    @Size(max = 50)
    @Email
    private String email;


    @NotBlank
    private String password;

    private Set<ERole> role;
}
