package com.atos.testoffer.test;

import com.atos.testoffer.persistence.model.User;
import com.atos.testoffer.service.IUserService;
import com.atos.testoffer.spring.ServiceConfig;
import com.atos.testoffer.spring.TestDbConfig;
import com.atos.testoffer.spring.TestIntegrationConfig;
import com.atos.testoffer.validation.EmailExistsException;
import com.atos.testoffer.web.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestDbConfig.class, ServiceConfig.class, TestIntegrationConfig.class})
public class UserServiceIntegrationTest {

    @Autowired
    private IUserService userService;


    @Test
    public void givenNewUser_whenRegistered_thenCorrect() throws EmailExistsException {
        final String userEmail = UUID.randomUUID().toString();
        final UserDto userDto = createUserDto(userEmail);

        final User user = userService.registerNewUserAccount(userDto, true);

        assertNotNull(user);
        assertNotNull(user.getEmail());
        assertEquals(userEmail, user.getEmail());
        assertNotNull(user.getId());
        assertNotNull(user.getAge());
    }

    @Test
    public void givenDetachedUser_whenServiceLoadById_thenCorrect() throws EmailExistsException {
        final User user = registerUser();
        final User user2 = userService.getUserByID(user.getId()).get();
        assertEquals(user, user2);
    }

    @Test
    public void givenDetachedUser_whenServiceLoadByEmail_thenCorrect() throws EmailExistsException {
        final User user = registerUser();
        final User user2 = userService.findUserByEmail(user.getEmail());
        assertEquals(user, user2);
    }

    //

    private UserDto createUserDto(final String email) {
        final UserDto userDto = new UserDto();
        userDto.setEmail(email);
        userDto.setPassword("SecretPassword");
        userDto.setFirstName("First");
        userDto.setLastName("Last");
        userDto.setAge(18);
        return userDto;
    }

    private User registerUser() {
        final String email = UUID.randomUUID().toString();
        final UserDto userDto = createUserDto(email);
        final User user = userService.registerNewUserAccount(userDto, true);
        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals(email, user.getEmail());
        return user;
    }

}
