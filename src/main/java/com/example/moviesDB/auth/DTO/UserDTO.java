package com.example.moviesDB.auth.DTO;

import com.example.moviesDB.app.Model.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

public class UserDTO {
    @Id
    private ObjectId id;
    private int userID;
    private String username;
    private String email;
    private String password;
    @DocumentReference
    private List<Movie> watchList;

    public UserDTO(ObjectId id, int userID, String username, String email, String password, List<Movie> watchList) {
        this.id = id;
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.watchList = watchList;
    }

    public List<Movie> getWatchList() {
        return watchList;
    }

    public void setWatchList(List<Movie> watchList) {
        this.watchList = watchList;
    }

    public UserDTO() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", userID=" + userID +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", watchList=" + watchList +
                '}';
    }
}
