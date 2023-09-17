package com.example.moviesDB.auth.Controller;

import com.example.moviesDB.app.Model.Movie;
import com.example.moviesDB.auth.DTO.LoginDTO;
import com.example.moviesDB.auth.DTO.UserDTO;
import com.example.moviesDB.auth.Response.LoginResponse;
import com.example.moviesDB.auth.Response.WatchListResponse;
import com.example.moviesDB.auth.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public String saveUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping(path = "/watchlist/{username}")
    public List<Movie> getUserWatchList(@PathVariable String username) {
        List<Movie> movies = userService.getUserWatchList(username);
        movies.stream().forEach(movie -> log.info(movie.toString()));
        return movies;
    }


    @PostMapping(path = "/watchlist")
    public ResponseEntity<?> addUserWatchList(@RequestBody Map<String, String> payload) {

        WatchListResponse watchListResponse = userService.addUserWatchList(
                payload.get("username"),
                payload.get("imdbId")
        );
        return ResponseEntity.ok(watchListResponse);
    }

    @GetMapping("/login")
    public String getUsernameWithEmail(@RequestParam String email) {
        String username = userService.getUsernameWithEmail(email);
        return username;
    }

}
