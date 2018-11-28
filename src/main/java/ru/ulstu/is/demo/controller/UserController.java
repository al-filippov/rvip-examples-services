package ru.ulstu.is.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ulstu.is.demo.model.UserDto;
import ru.ulstu.is.demo.model.UserListDto;
import ru.ulstu.is.demo.response.Response;
import ru.ulstu.is.demo.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/1.0/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public Response<UserListDto> getUsers(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                          @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        return new Response<>(userService.getUsers(page, size));
    }

    @GetMapping("/{login}")
    public Response<UserDto> getUser(@PathVariable("login") String login) {
        return new Response<>(userService.getUser(login));
    }

    @PostMapping("/")
    public Response<UserDto> createUser(@RequestBody @Valid UserDto user) {
        return new Response<>(userService.createUser(user));
    }

    @PatchMapping("/{login}")
    public Response<UserDto> updateUser(@PathVariable("login") String login,
                                        @RequestBody @Valid UserDto user) {
        return new Response<>(userService.updateUser(login, user));
    }

    @PatchMapping("/update-password/{login}")
    public Response<UserDto> updateUserPassword(@PathVariable("login") String login,
                                                @RequestBody UserDto user) {
        return new Response<>(userService.updateUserPassword(login, user));
    }

    @DeleteMapping("/{login}")
    public Response<UserDto> deleteUser(@PathVariable("login") String login) {
        return new Response<>(userService.deleteUser(login));
    }
}
