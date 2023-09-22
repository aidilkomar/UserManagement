package com.axa.UserManagement.service;

import com.axa.UserManagement.dto.account.AccountGridDTO;
import com.axa.UserManagement.dto.account.InsertAccountDTO;
import com.axa.UserManagement.dto.account.UpdateAccountDTO;
import com.axa.UserManagement.entity.Account;
import com.axa.UserManagement.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountGridDTO> getGridAccount(String username) {
        List<AccountGridDTO> grid = accountRepository.findAll(username);
        return grid;
    }

    @Override
    public String saveAccount(InsertAccountDTO dto) {
        Account entity = new Account(dto.getUsername(), dto.getUsername());
        Account respond = accountRepository.save(entity);
        return respond.getUsername();
    }

    @Override
    public UpdateAccountDTO getUpdateAccount(String username) {
        Optional<Account> nullableEntity = accountRepository.findByUsername(username);
        Account entity = nullableEntity.get();
        UpdateAccountDTO accountDTO = new UpdateAccountDTO(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword()
        );
        return accountDTO;
    }

    @Override
    public Long updateAccount(UpdateAccountDTO dto) {
        Account entity = new Account(dto.getId(), dto.getUsername(), dto.getPassword());
        Account respond = accountRepository.save(entity);
        return respond.getId();
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
