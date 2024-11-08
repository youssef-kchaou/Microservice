package com.example.courzeloproject.Repository;

import com.example.courzeloproject.Entite.Profile;
import com.example.courzeloproject.Entite.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepo extends MongoRepository<Profile,String> {
    @Query("{'user._id': ?0}")
    Optional<Profile> findByIdUser(String userId);
}
