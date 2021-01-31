package com.example.demo.service;

import com.example.demo.exception.IdNotFoundException;
import com.example.demo.model.Booking;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserDetailServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String loginUsername) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(loginUsername);
        if (Objects.isNull(user)) {
            user = userRepository.findByEmail(loginUsername);
            if (Objects.isNull(user)) {
                throw new UsernameNotFoundException("User " + loginUsername + " has not been found");
            }
        }
        return new org.springframework.security.core.userdetails.User(loginUsername, user.getPassword(),
                user.getEnabled(), true, true, true,
                AuthorityUtils.createAuthorityList(user.getRole()));
    }


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


    @Override
    public User updateUser(User user) {
        log.info("User successfully updated");
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserByBooking(Booking booking) {
        if (Objects.nonNull(booking)) {
            return userRepository.findUserByBookingsContains(booking);
        }
        return null;
    }


    @Override
    public Boolean deleteUserById(Long id) throws IdNotFoundException {
        if (userRepository.findById(id).isEmpty()) {
            throw new IdNotFoundException("Couldn't find any match with this id " + id);
        } else {
            log.info("User successfully deleted");
            userRepository.deleteById(id);
            return true;
        }
    }
}


