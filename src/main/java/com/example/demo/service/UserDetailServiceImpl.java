package com.example.demo.service;

import com.example.demo.exception.IdNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService, UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserDetailServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User " + username + " has not been found");
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
                user.getEnabled(), true, true, true,
                AuthorityUtils.createAuthorityList(user.getRole()));
    }

//    @Override
//    public UserDetailsAdapter loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if (Objects.isNull(user)) {
//            throw new UsernameNotFoundException("User " + username + " has not been found");
//        }
//        return new UserDetailsAdapter(user);
//    }

    @Override
    public User createNewUser(User user) {
        if (Objects.isNull(userRepository.findByUsername(user.getUsername()))) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            if (Objects.isNull(user.getEnabled())) {
                user.setEnabled(true);
            }
            // TODO: SET ROLE WHEN CREATING NEW USER
//            if (Objects.isNull(user.getRole())) {
//                user.setRole("ROLE_USER");
//            }
            return userRepository.save(user);
        }
        return null;
    }

//    @Override
//    public User createNewUser(User user) {
//        user.setUsername(user.getUsername());
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setEmail(user.getEmail());
//        user.setRole("ROLE_ADMIN");
//        user.setEnabled(true);
//        return userRepository.save(user);
//    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> user.setPassword(null));
        return users;
    }

    @Override
    public User getUserById(Long id) throws IdNotFoundException {
        if (id == null) {
            throw new IdNotFoundException("Id has not been found");
        } else {
            return userRepository.findById(id).orElse(null);
        }
    }


}

