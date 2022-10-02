package com.pawlukowicz.empik.user.service;

import com.pawlukowicz.empik.user.config.GithubApiConfig;
import com.pawlukowicz.empik.user.dto.GithubUserResponseDTO;
import com.pawlukowicz.empik.user.dto.UserDTO;
import com.pawlukowicz.empik.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock private RestTemplate restTemplate;

    @Mock private GithubApiConfig githubApiConfig;

    @Mock private UserRepository userRepository;

    @InjectMocks private UserService userServiceUnderTest;

    @Test
    @DisplayName("Should return correct Users calculations")
    void shouldReturnCorrectUsersCalculations() {
        // Given
        String login = "KarolPawlukowicz";

        // When
        GithubUserResponseDTO githubUserResponseDTO = new GithubUserResponseDTO(
                60007028L,
                "KarolPawlukowicz",
                "Karol Pawlukowicz",
                "User",
                "https://avatars.githubusercontent.com/u/60007028?v=4",
                "2020-01-17T14:45:54Z",
                12,
                4
        );
        final String url = githubApiConfig.getBaseUrl() + githubApiConfig.getUserUrl() + login;

        Mockito
                .when(restTemplate.getForEntity(
                        url, GithubUserResponseDTO.class))
          .thenReturn(new ResponseEntity(githubUserResponseDTO, HttpStatus.OK));

        // Then
        UserDTO expectedUser = new UserDTO(
                60007028L,
                "KarolPawlukowicz",
                "Karol Pawlukowicz",
                "User",
                "https://avatars.githubusercontent.com/u/60007028?v=4",
                "2020-01-17T14:45:54Z",
                21.0
        );
        UserDTO actualUser = userServiceUnderTest.getUserByLogin(login);
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    @DisplayName("Should return incorrect Users calculations")
    void shouldReturnIncorrectUsersCalculations() {
        // Given
        String login = "KarolPawlukowicz";

        // When
        GithubUserResponseDTO githubUserResponseDTO = new GithubUserResponseDTO(
                60007028L,
                "KarolPawlukowicz",
                "Karol Pawlukowicz",
                "User",
                "https://avatars.githubusercontent.com/u/60007028?v=4",
                "2020-01-17T14:45:54Z",
                12,
                4
        );
        final String url = githubApiConfig.getBaseUrl() + githubApiConfig.getUserUrl() + login;

        Mockito
                .when(restTemplate.getForEntity(
                        url, GithubUserResponseDTO.class))
                .thenReturn(new ResponseEntity(githubUserResponseDTO, HttpStatus.OK));

        // Then
        UserDTO expectedUser = new UserDTO(
                60007028L,
                "KarolPawlukowicz",
                "Karol Pawlukowicz",
                "User",
                "https://avatars.githubusercontent.com/u/60007028?v=4",
                "2020-01-17T14:45:54Z",
                27.0
        );
        UserDTO actualUser = userServiceUnderTest.getUserByLogin(login);
        Assertions.assertNotEquals(expectedUser, actualUser);
    }
}