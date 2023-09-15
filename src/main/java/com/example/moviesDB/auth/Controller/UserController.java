package com.example.moviesDB.auth.Controller;


import com.example.moviesDB.app.Model.Movie;
import com.example.moviesDB.app.Model.Review;
import com.example.moviesDB.auth.DTO.LoginDTO;
import com.example.moviesDB.auth.DTO.UserDTO;
import com.example.moviesDB.auth.Response.LoginResponse;
import com.example.moviesDB.auth.Response.WatchListResponse;
import com.example.moviesDB.auth.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping(path="/save")
    public String saveUser(@RequestBody UserDTO userDTO) {
        String id = userService.addUser(userDTO);
        return id;
    }

    @PostMapping(path="/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping(path="/watchlist/{username}")
    public List<Movie> getUserWatchList(@PathVariable String username) {
        List<Movie> movies = userService.getUserWatchList(username);
        System.out.println(movies);
        return movies;
    }


    @PostMapping(path="/watchlist")
    public ResponseEntity<?> addUserWatchList(@RequestBody Map<String, String> payload) {

        WatchListResponse watchListResponse = userService.addUserWatchList(
                payload.get("username"),
                payload.get("imdbId")
        );
        return ResponseEntity.ok(watchListResponse);
    }



}
