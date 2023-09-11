package com.example.moviesDB.Repository;

import com.example.moviesDB.Model.User;
import com.example.moviesDB.app.Model.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, ObjectId> {
}
