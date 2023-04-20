package com.anywrgroup.schoolmanager.dto;

import com.anywrgroup.schoolmanager.dto.enumeration.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserDTO implements Serializable {

    private  Long userId;

    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotNull
    private Role role;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
