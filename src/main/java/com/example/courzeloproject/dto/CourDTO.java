package com.example.courzeloproject.dto;

import com.example.courzeloproject.Entite.Niveau;
import com.example.courzeloproject.Entite.TypeCour;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourDTO {

    private String idCour;
    private String nomCour;
    private String description;
    private Date date;
    private Niveau niveau;
    private TypeCour typeCour;
    private String photo;
    private double prix;
    private int test;
    private String nomDomaine;
}