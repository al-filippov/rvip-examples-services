package ru.ulstu.is.demo.model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserListDto {
    private List<UserDto> items;

    public UserListDto(List<UserEntity> items) {
        this.items = Optional.ofNullable(items).orElse(Collections.emptyList()).stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    public List<UserDto> getItems() {
        return items;
    }

    public int getCount() {
        return items.size();
    }
}
