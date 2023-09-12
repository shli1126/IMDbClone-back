package com.example.moviesDB.auth.Service;
import com.example.moviesDB.auth.DTO.LoginDTO;
import com.example.moviesDB.auth.DTO.UserDTO;
import com.example.moviesDB.auth.Model.User;
import com.example.moviesDB.auth.Repository.UserRepository;
import com.example.moviesDB.auth.Response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(UserDTO userDTO) {

        User user = userRepo.insert(new User(
                userDTO.getId(),
                userDTO.getUserID(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword())
        ));

        return user.getUsername();
    }


    public LoginResponse loginUser(LoginDTO loginDTO) {
        User newUser = userRepo.findByEmail(loginDTO.getEmail());
        if (newUser != null) {
            String password = loginDTO.getPassword();
            String encodePassword = newUser.getPassword();
            Boolean isPasswordMatch = passwordEncoder.matches(password, encodePassword);
            if (isPasswordMatch) {
                Optional<User> user = userRepo.findUserByEmailAndPassword(loginDTO.getEmail(), encodePassword);
                if (user.isPresent()) {
                    return new LoginResponse("Login Success", true);
                }
                else {
                    return new LoginResponse("Login Fail", false);
                }
            }
            else {
                return new LoginResponse("Wrong Password", false);
            }
        }
        else {
            return new LoginResponse("Email not found", false);
        }
    }
}

