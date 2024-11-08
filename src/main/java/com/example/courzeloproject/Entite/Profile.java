package com.example.courzeloproject.Entite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "profile")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Profile {
    @Id
    private String id ;

    private String firstName;

    private String lastName;

    private Long phone;

    private String address;

    private String photo;

    @DBRef
    private User user;

}
