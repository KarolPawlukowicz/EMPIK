package com.pawlukowicz.empik.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GithubUserResponseDTO {
    long id;
    String login;
    String name;
    String type;
    String avatar_url;
    String created_at;
    int public_repos;
    int followers;
}
