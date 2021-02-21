package com.codegym.englishbackend.jwt;

import com.codegym.englishbackend.entity.User;
import com.codegym.englishbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {
    static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    static {
        inMemoryUserList.add(new JwtUserDetails(1L, "in28minutes",
                "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<JwtUserDetails> findFirst = inMemoryUserList.stream().
//                filter(user -> user.getUsername().equals(username)).findFirst();
//
//        if (!findFirst.isPresent()) {
//            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
//        }
//        return findFirst.get();
        Optional<User> user = userRepository.findByUserName(username);
        Optional<JwtUserDetails> findFirst = Optional.of(new JwtUserDetails(user.get().getId(), username, passwordEncoder.encode(user.get().getPassWord()), "ROLE_USER_2"));
        return findFirst.get();
    }
}
