package com.pawlukowicz.empik.user.controller;

import com.pawlukowicz.empik.user.dto.UserDTO;
import com.pawlukowicz.empik.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired private UserService userService;

    @GetMapping("/{login}")
    public UserDTO getUserByLogin(final @PathVariable String login) {
        return userService.getUserByLogin(login);
    }

}
