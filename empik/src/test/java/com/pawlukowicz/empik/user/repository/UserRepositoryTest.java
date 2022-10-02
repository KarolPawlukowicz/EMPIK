package com.pawlukowicz.empik.user.repository;

import com.pawlukowicz.empik.user.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {

    @Autowired private UserRepository userRepositoryUnderTest;

    @Test
    @DisplayName("Should find user by email")
    void shouldFindUserByEmail() {
        // Given
        String login = "KarolPawlukowicz";
        User expectedUser = new User(
               1L,
               "KarolPawlukowicz",
               1
        );
        userRepositoryUnderTest.save(expectedUser);

        // When
        User actualUser = userRepositoryUnderTest.findByLogin(login);

        // Then
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    @DisplayName("Should not find user by email")
    void shouldNotFindUserByEmail() {
        // Given
        String login = "KarolPawlukowicz";
        User expectedUser = new User(
                1L,
                "KarolPawlukowicz",
                1
        );

        // When
        User actualUser = userRepositoryUnderTest.findByLogin(login);

        // Then
        Assertions.assertNotEquals(expectedUser, actualUser);
    }
}