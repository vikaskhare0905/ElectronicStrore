package com.bikkadIT.electronicStore.repositories;

import com.bikkadIT.electronicStore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

    User findByEmail();
    Optional<User> findByEmailAndPassword(String email, String password);
    List<User> findByNameContaining(String keywords);
}
