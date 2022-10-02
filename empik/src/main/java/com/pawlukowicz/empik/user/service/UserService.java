package com.pawlukowicz.empik.user.service;

import com.pawlukowicz.empik.user.config.GithubApiConfig;
import com.pawlukowicz.empik.user.dto.GithubUserResponseDTO;
import com.pawlukowicz.empik.user.dto.UserDTO;
import com.pawlukowicz.empik.user.model.User;
import com.pawlukowicz.empik.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class UserService {

    private static final double CALCULATIONS_NUMERATOR = 6;
    private static final int INCREASE_REPOS_NUMBER_BY_2 = 2;

    @Autowired private RestTemplate restTemplate;
    @Autowired private GithubApiConfig githubApiConfig;
    @Autowired private UserRepository userRepository;

    public UserDTO getUserByLogin(String login) {
        log.info("Getting user '{}' from github API", login);
        final String url = githubApiConfig.getBaseUrl() + githubApiConfig.getUserUrl() + login;

        try {
            increaseRequestCountForLogin(login);
            ResponseEntity<GithubUserResponseDTO> result = restTemplate.getForEntity(url, GithubUserResponseDTO.class);
            return mapToUserDTO(result.getBody());
        } catch(HttpClientErrorException httpClientErrorException) {
            log.info("User '{}' not found in github users API", login);
            throw httpClientErrorException;
        }
    }

    private UserDTO mapToUserDTO(GithubUserResponseDTO githubUserResponseDTO) {
        double calculations = CALCULATIONS_NUMERATOR / githubUserResponseDTO.getFollowers() * (INCREASE_REPOS_NUMBER_BY_2 + githubUserResponseDTO.getPublic_repos());
        return new UserDTO(
                githubUserResponseDTO.getId(),
                githubUserResponseDTO.getLogin(),
                githubUserResponseDTO.getName(),
                githubUserResponseDTO.getType(),
                githubUserResponseDTO.getAvatar_url(),
                githubUserResponseDTO.getCreated_at(),
                calculations
        );
    }

    private void increaseRequestCountForLogin(String login) {
        User user = userRepository.findByLogin(login);
        if(user != null) {
            user.incrementRequestCount();
            userRepository.save(user);
        } else {
            userRepository.save(new User(login));
        }
    }
}
