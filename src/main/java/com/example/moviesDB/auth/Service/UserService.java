package com.example.moviesDB.auth.Service;
import com.example.moviesDB.app.Model.Movie;
import com.example.moviesDB.app.Repository.MovieRepository;
import com.example.moviesDB.auth.DTO.LoginDTO;
import com.example.moviesDB.auth.DTO.UserDTO;
import com.example.moviesDB.auth.Model.User;
import com.example.moviesDB.auth.Repository.UserRepository;
import com.example.moviesDB.auth.Response.LoginResponse;
import com.example.moviesDB.auth.Response.WatchListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MovieRepository movieRepo;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(UserDTO userDTO) {

        User user = userRepo.insert(new User(
                userDTO.getId(),
                userDTO.getUserID(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getWatchList()
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

    public List<Movie> getUserWatchList(String username) {
        User user = userRepo.findByUsername(username);
        return user.getWatchList();
    }

    public WatchListResponse addUserWatchList(String username, String imdbId) {
        Movie movie = movieRepo.findMovieByImdbId(imdbId).orElse(null);
        System.out.println(movie);
        if (movie != null) {
            mongoTemplate.update(User.class)
                    .matching(Criteria.where("username").is(username))
                    .apply(new Update().push("watchList").value(movie))
                    .first();
            return new WatchListResponse("Added movie to watch list", true);
        }
        else {
            return new WatchListResponse("Movie not found", false);
        }
    }


}

