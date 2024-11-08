package com.example.courzeloproject.Service;



import com.example.courzeloproject.Entite.User;
import com.example.courzeloproject.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepo userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String emailOrIdent) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmailOrUsername(emailOrIdent, emailOrIdent);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with email or identification: " + emailOrIdent);
        } else {
            return UserDetailsImpl.build(user);
        }
    }
}
