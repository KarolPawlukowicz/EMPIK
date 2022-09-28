package com.pawlukowicz.empik.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    long id;
    String login;
    String name;
    String type;
    String avatarUrl;
    String createdAt;
    double calculations;
}
