package com.codegym.englishbackend.controllers;

import com.codegym.englishbackend.exception.ResourceNotFoundException;
import com.codegym.englishbackend.model.User;
import com.codegym.englishbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

import static com.codegym.englishbackend.controllers.ResponseUtil.resourceUri;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> findUserById(
            @PathVariable final long userId
    ) {
        return userRepository.findById(userId)
                .map(user -> ResponseEntity.ok().location(resourceUri(userId))
                        .body(user))
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "UserID " + userId + " not found"
                        )
                );
    }

    @GetMapping("/users")
    public Page<User> getAllPosts(@PageableDefault(value = 5) Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User request) {
        return Optional.of(request)
                .map(userRepository::save)
                .map(user -> ResponseEntity.created(resourceUri(user.getId()))
                        .body(user)
                ).orElseThrow(IllegalArgumentException::new);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable final long userId,
            @Valid @RequestBody User request
    ) {
        return userRepository.findById(userId)
                .map(
                        user -> {
                            user.setUserName(request.getUserName());
                            user.setPassWord(request.getPassWord());
                            user.setFirstName(request.getFirstName());
                            user.setLastName(request.getLastName());
                            user.setDescription(request.getDescription());
                            user.setCreateAt(request.getCreateAt());
                            user.setUpdateAt(request.getUpdateAt());
                            return user;
                        }
                )
                .map(userRepository::save)
                .map(user -> ResponseEntity.ok().location(resourceUri(userId))
                        .body(user))
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "UserID " + userId + " not found"
                        )
                );
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(
            @PathVariable Long userId
    ) {
        return userRepository.findById(userId)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity
                            .ok()
                            .build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        "UserID " + userId + " not found"
                ));
    }
}
