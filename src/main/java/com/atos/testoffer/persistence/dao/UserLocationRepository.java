package com.atos.testoffer.persistence.dao;

import com.atos.testoffer.persistence.model.User;
import com.atos.testoffer.persistence.model.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
    UserLocation findByCountryAndUser(String country, User user);
}
