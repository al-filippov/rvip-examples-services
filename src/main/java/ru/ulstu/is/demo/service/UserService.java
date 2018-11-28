package ru.ulstu.is.demo.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.ulstu.is.demo.model.UserDto;
import ru.ulstu.is.demo.model.UserEntity;
import ru.ulstu.is.demo.model.UserListDto;
import ru.ulstu.is.demo.repository.UserRepository;
import ru.ulstu.is.demo.validation.UserValidation;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserValidation userValidation;

    public UserService(UserRepository userRepository, UserValidation userValidation) {
        this.userRepository = userRepository;
        this.userValidation = userValidation;
    }

    private UserEntity getUserByLoginOrThrow(String login) {
        return userRepository.findFirst1ByLogin(login)
                .orElseThrow(() -> new RuntimeException(String.format("User %s is not found", login)));
    }

    public UserListDto getUsers(int page, int size) {
        return new UserListDto(userRepository.findAll(
                new PageRequest(page, size, Sort.by("login").ascending())).getContent());
    }

    public UserDto getUser(String login) {
        return new UserDto(getUserByLoginOrThrow(login));
    }

    public UserDto createUser(UserDto userDto) {
        userValidation.checkUserDto(userDto);
        if (userRepository.existsByLogin(userDto.getLogin())) {
            throw new RuntimeException(String.format("User %s is already exists", userDto.getLogin()));
        }
        final UserEntity userEntity = new UserEntity(userDto.getLogin(), userDto.getName(), userDto.getPassword());
        userRepository.save(userEntity);
        return new UserDto(userEntity);
    }

    public UserDto updateUser(String login, UserDto userDto) {
        userValidation.checkUserDtoName(userDto);
        final UserEntity userEntity = getUserByLoginOrThrow(login);
        userEntity.setName(userDto.getName());
        userRepository.save(userEntity);
        return new UserDto(userEntity);
    }

    public UserDto updateUserPassword(String login, UserDto userDto) {
        userValidation.checkUserDtoPassword(userDto);
        final UserEntity userEntity = getUserByLoginOrThrow(login);
        if (!userEntity.getPassword().equals(userDto.getOldPassword())) {
            throw new RuntimeException("Wrong password");
        }
        userEntity.setPassword(userDto.getPassword());
        userRepository.save(userEntity);
        return new UserDto(userEntity);
    }

    public UserDto deleteUser(String login) {
        final UserEntity userEntity = getUserByLoginOrThrow(login);
        userRepository.delete(userEntity);
        return new UserDto(userEntity);
    }
}
