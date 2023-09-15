package com.example.moviesDB.auth.Repository;

import com.example.moviesDB.auth.Model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findUserByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    User findByUsername(String username);

}
