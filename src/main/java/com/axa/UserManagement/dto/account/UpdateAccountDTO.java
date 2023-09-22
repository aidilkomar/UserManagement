package com.axa.UserManagement.dto.account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateAccountDTO {

    private Long id;
    private String username;
    private String password;

    public UpdateAccountDTO() {
    }

    public UpdateAccountDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UpdateAccountDTO(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
