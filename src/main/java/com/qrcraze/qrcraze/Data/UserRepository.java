package com.qrcraze.qrcraze.Data;

import com.qrcraze.qrcraze.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findBygoogleID(String googleID);
}
