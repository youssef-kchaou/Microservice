package com.example.courzeloproject.Controller;

import com.example.courzeloproject.Entite.ERole;
import com.example.courzeloproject.Entite.Role;
import com.example.courzeloproject.Entite.User;
import com.example.courzeloproject.Repository.RoleRepo;
import com.example.courzeloproject.Repository.UserRepo;
import com.example.courzeloproject.Security.jwt.JwtUtils;
import com.example.courzeloproject.Service.UserDetailsImpl;
import com.example.courzeloproject.Service.UserServiceImpl;
import com.example.courzeloproject.payload.request.LoginEmailRequest;
import com.example.courzeloproject.payload.request.SignupEmailRequest;
import com.example.courzeloproject.payload.response.MessageResponse;
import jakarta.validation.Valid;

import com.example.courzeloproject.payload.response.JwtResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ParticipantController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepository;
    @Autowired
    UserServiceImpl userService;

    @Autowired
    RoleRepo roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Value("http://localhost:4200/login/verifyCode/")
    private String login;
    @PostMapping("/signinWithEmail")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginEmailRequest loginEmailRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginEmailRequest.getEmail(), loginEmailRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        User user = userRepository.findById(userDetails.getId()) ;
        return ResponseEntity.ok(new JwtResponse(jwt,
                user,
                roles));
    }

    @PostMapping("/signupWithEmail")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupEmailRequest signupEmailRequest) {
        if (userRepository.existsByEmail(signupEmailRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: email is already taken!"));
        }
        /* Create new user's account */

        User user = new User(signupEmailRequest.getEmail(),
                encoder.encode(signupEmailRequest.getPassword()));

        Set<ERole> strRoles = signupEmailRequest.getRole();
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_PARTICIPANT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        String randomCode = RandomStringUtils.random(6,true,true);
        System.out.println("Code de verification = "+ randomCode);
        user.setVerificationCode(randomCode);
         user.setEnabled(false);
        userRepository.save(user);
        String siteURL = login + user.getVerificationCode();
        this.userService.sendVerificationEmail(user, siteURL);
        return ResponseEntity.ok(new MessageResponse(user.toString()));
    }
}
