package com.example.courzeloproject.Repository;

import com.example.courzeloproject.Entite.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    User findById(String id) ;
    @Query("{'roles.name': ?0}")
    List<User>  findByRolesName(String role) ;

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User getById(long l);
    User findByVerificationCode(String code);
    User findUserByEmailOrUsername(String email, String Ident);
}
