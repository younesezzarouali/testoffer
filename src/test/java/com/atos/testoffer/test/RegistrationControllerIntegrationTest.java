package com.atos.testoffer.test;

import com.atos.testoffer.Application;
import com.atos.testoffer.persistence.model.User;
import com.atos.testoffer.spring.TestDbConfig;
import com.atos.testoffer.spring.TestIntegrationConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, TestDbConfig.class, TestIntegrationConfig.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class RegistrationControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PersistenceContext
    private EntityManager entityManager;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        User user = new User();
        user.setEmail(UUID.randomUUID().toString() + "@example.com");
        user.setPassword(UUID.randomUUID().toString());
        user.setFirstName("First");
        user.setLastName("Last");

        entityManager.persist(user);


        /*
            flush managed entities to the database to populate identifier field
         */
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    public void testRegistrationValidation_invalidAge() throws Exception {

        final MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("firstName", "younes");
        param.add("lastName", "ezzarouali");
        param.add("email", "test@test.com");
        param.add("age", "0");
        param.add("password", "1234");

        ResultActions resultActions = this.mockMvc.perform(post("/user/registration").params(param));
        resultActions.andExpect(status().is(400));
    }
}
