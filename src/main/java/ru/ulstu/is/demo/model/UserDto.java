package ru.ulstu.is.demo.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {
    @NotNull
    @Size(min = 5, max = 50)
    private String login;
    private String name;
    private String password;
    private String password2;
    private String oldPassword;

    public UserDto() {
    }

    public UserDto(UserEntity userEntity) {
        this.login = userEntity.getLogin();
        this.name = userEntity.getName();
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword2() {
        return password2;
    }

    public String getOldPassword() {
        return oldPassword;
    }
}
