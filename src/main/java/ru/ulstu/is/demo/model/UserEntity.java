package ru.ulstu.is.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min = 5, max = 50)
    @Column(nullable = false, unique = true, length = 50)
    private String login;
    @NotNull
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String name;
    @NotNull
    @Size(min = 5, max = 20)
    @Column(nullable = false, length = 20)
    private String password;

    public UserEntity() {
    }

    public UserEntity(String login, String name, String password) {
        this.login = login;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
