package com.codegym.englishbackend.repositories;

import com.codegym.englishbackend.entity.User;
import com.codegym.englishbackend.entity.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
