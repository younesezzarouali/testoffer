package com.atos.testoffer.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RegistrationControllerIntegrationTest.class,
        UserServiceIntegrationTest.class
})
public class IntegrationSuite {
}
