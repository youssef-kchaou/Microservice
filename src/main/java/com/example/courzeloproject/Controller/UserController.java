package com.example.courzeloproject.Controller;

import com.example.courzeloproject.Entite.ERole;
import com.example.courzeloproject.Entite.Profile;
import com.example.courzeloproject.Entite.User;
import com.example.courzeloproject.Service.IProfileService;
import com.example.courzeloproject.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    IUserService userService ;

    @GetMapping("/{role}")
    public List<User> getFormateur(@PathVariable("role") ERole role){
        return userService.getUsersByRole(role) ;
    }
    @GetMapping("/connect/{verificationCode}")
    public String verifyUser(@PathVariable String verificationCode) {
        if (userService.verify(verificationCode)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

}
