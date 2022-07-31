package com.example.springbootsecuritywithjwt.services.user;

import com.example.springbootsecuritywithjwt.models.UserInfo;
import com.example.springbootsecuritywithjwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.xml.bind.ValidationException;
import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        final UserInfo user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + userName);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(),
                new ArrayList<>());
    }

    public UserInfo createUser(final String userName, final String password) throws ValidationException {
        if (userRepository.existsByUserName(userName)) {
            throw new ValidationException("Username already exist.");
        }

        final String encodedPassword = new BCryptPasswordEncoder().encode(password);
        final UserInfo newUser = new UserInfo(userName, encodedPassword);
        userRepository.save(newUser);
        return newUser;
    }
}
