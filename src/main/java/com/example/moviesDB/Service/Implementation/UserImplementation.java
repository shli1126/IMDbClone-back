package com.example.moviesDB.Service.Implementation;
import com.example.moviesDB.Dto.UserDTO;
import com.example.moviesDB.Model.User;
import com.example.moviesDB.Repository.UserRepo;
import com.example.moviesDB.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserImplementation implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public String addUser(UserDTO userDTO) {

        User user = userRepo.insert(new User(
                userDTO.getId(),
                userDTO.getUserID(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword())
//                userDTO.getPassword()

        ));

        return user.getUsername();
    }
}
