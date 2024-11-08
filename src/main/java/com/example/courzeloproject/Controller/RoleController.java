package com.example.courzeloproject.Controller;

import com.example.courzeloproject.Entite.ERole;
import com.example.courzeloproject.Entite.Role;
import com.example.courzeloproject.Repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    RoleRepo roleRepository;
    /* ajouter les roles principal*/
    @GetMapping("/add")
    public String add() {
        Role role1 = new Role();
        role1.setId(0L);
        role1.setName(ERole.ROLE_SUPERADMIN);
        Role role2 = new Role();
        role2.setId(2L);
        role2.setName(ERole.ROLE_ADMIN);
        Role role3 = new Role();
        role3.setId(3L);
        role3.setName(ERole.ROLE_FORMATEUR);
        Role role4 = new Role();
        role4.setId(4L);
        role4.setName(ERole.ROLE_PARTICIPANT);
        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
        roleRepository.save(role4);
        return "OK";
    }
}
