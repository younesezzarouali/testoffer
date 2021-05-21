package com.atos.testoffer.service;

import com.atos.testoffer.persistence.dao.UserRepository;
import com.atos.testoffer.persistence.model.User;
import com.atos.testoffer.web.dto.UserDto;
import com.atos.testoffer.web.error.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // API

    @Override
    public User registerNewUserAccount(final UserDto accountDto, final Boolean enabled) {
        if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + accountDto.getEmail());
        }
        final User user = new User();

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setAge(accountDto.getAge());
        user.setEnabled(enabled);
        return userRepository.save(user);
    }


    @Override
    public User findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public Optional<User> getUserByID(final long id) {
        return userRepository.findById(id);
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }

}
