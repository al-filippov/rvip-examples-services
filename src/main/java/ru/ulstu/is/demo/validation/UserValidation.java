package ru.ulstu.is.demo.validation;

import org.springframework.util.StringUtils;
import ru.ulstu.is.demo.model.UserDto;

public class UserValidation {
    public void checkUserDto(UserDto userDto) {
        checkUserDtoName(userDto);
        checkUserDtoPassword(userDto);
    }

    public void checkUserDtoName(UserDto userDto) {
        final String name = userDto.getName();
        if (StringUtils.isEmpty(name) || name.length() < 5 || name.length() > 50) {
            throw new RuntimeException("User name is invalid");
        }
    }

    public void checkUserDtoPassword(UserDto userDto) {
        final String password = userDto.getPassword();
        if (StringUtils.isEmpty(password) || password.length() < 2 || password.length() > 20) {
            throw new RuntimeException("User password is invalid");
        }
        if (!password.equals(userDto.getPassword2())) {
            throw new RuntimeException("Passwords is not equals");
        }
    }
}
