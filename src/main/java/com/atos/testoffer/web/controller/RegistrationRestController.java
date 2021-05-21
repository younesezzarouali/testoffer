package com.atos.testoffer.web.controller;

import com.atos.testoffer.service.IUserService;
import com.atos.testoffer.web.dto.UserDto;
import com.atos.testoffer.web.util.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RegistrationRestController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private IUserService userService;

    public RegistrationRestController() {
        super();
    }

    /**
     * Web Service that Registre a new User
     */
    @PostMapping("/user/registration")
    public GenericResponse registerUserAccount(@Valid final UserDto accountDto,
                                               @RequestParam(required = false, defaultValue = "false") Boolean enabled) {
        LOGGER.debug("Registering user account with information: {}", accountDto);

        userService.registerNewUserAccount(accountDto, enabled);
        return new GenericResponse("success");
    }

    /**
     * Web Service that get a User by email
     */
    @GetMapping("/registredUser/{email}")
    public String getRegistredUser(final Model model, @PathVariable String email) {
        LOGGER.debug("Getting the user with email : {}", email);
        model.addAttribute("users", userService.findUserByEmail(email));
        return "users";
    }

}
