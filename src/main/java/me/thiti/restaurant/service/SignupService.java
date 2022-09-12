package me.thiti.restaurant.service;

import me.thiti.restaurant.dto.SignupDto;
import me.thiti.restaurant.model.User;
import me.thiti.restaurant.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public boolean isUsernameAvailable(String username) {
        return repository.findByUsername(username) == null;
    }

    public void createUser(SignupDto user) {
        User record = modelMapper.map(user, User.class);

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        record.setPassword(hashedPassword);

        repository.save(record);
    }

    public User getUser(String username) {
        return repository.findByUsername(username);
    }
}
