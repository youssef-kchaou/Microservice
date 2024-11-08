package com.example.courzeloproject.Service;

import com.example.courzeloproject.Entite.ERole;
import com.example.courzeloproject.Entite.User;

import java.util.List;

public interface IUserService {
    public User addUser(User u) ;
  //  public List<User> getUserByRole(String role) ;
    public boolean verify(String verificationCode) ;
    public List<User> getUsersByRole(ERole roleName) ;


}
