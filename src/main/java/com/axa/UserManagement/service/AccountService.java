package com.axa.UserManagement.service;

import com.axa.UserManagement.dto.account.AccountGridDTO;
import com.axa.UserManagement.dto.account.InsertAccountDTO;
import com.axa.UserManagement.dto.account.UpdateAccountDTO;

import java.util.List;

public interface AccountService {

    List<AccountGridDTO> getGridAccount(String username);

    String saveAccount(InsertAccountDTO dto);

    UpdateAccountDTO getUpdateAccount(String username);

    Long updateAccount(UpdateAccountDTO dto);

    void deleteAccount(Long id);
}
