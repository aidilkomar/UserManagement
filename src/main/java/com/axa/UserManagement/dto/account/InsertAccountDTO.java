package com.axa.UserManagement.dto.account;


import javax.validation.constraints.Size;

public class InsertAccountDTO {

    private String username;

    private String password;

    public InsertAccountDTO() {
    }

    public InsertAccountDTO(String username, String password) {
        this.username = username;
        this.password = password;
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
