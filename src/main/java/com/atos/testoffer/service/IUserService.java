package com.atos.testoffer.service;

import com.atos.testoffer.persistence.model.User;
import com.atos.testoffer.web.dto.UserDto;

import java.util.Optional;

public interface IUserService {

    User registerNewUserAccount(UserDto accountDto, Boolean enabled);

    User findUserByEmail(String email);

    Optional<User> getUserByID(long id);
}
