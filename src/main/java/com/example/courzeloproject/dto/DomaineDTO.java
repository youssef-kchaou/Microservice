package com.example.courzeloproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomaineDTO {
    private String id;
    private String nom;
    private String description;
    private String photo;
}