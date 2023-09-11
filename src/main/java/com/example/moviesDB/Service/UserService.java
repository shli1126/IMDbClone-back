package com.example.moviesDB.Service;

import com.example.moviesDB.Dto.UserDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String addUser(UserDTO userDTO);
}
