package com.example.courzeloproject.Repository;

import com.example.courzeloproject.Entite.ERole;
import com.example.courzeloproject.Entite.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends MongoRepository<Role,String> {
    Optional<Role> findByName(ERole name);
}
