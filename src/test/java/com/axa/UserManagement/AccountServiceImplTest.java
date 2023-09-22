package com.axa.UserManagement;

import com.axa.UserManagement.dto.account.AccountGridDTO;
import com.axa.UserManagement.dto.account.InsertAccountDTO;
import com.axa.UserManagement.dto.account.UpdateAccountDTO;
import com.axa.UserManagement.entity.Account;
import com.axa.UserManagement.repository.AccountRepository;
import com.axa.UserManagement.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetGridAccount() {
        String username = "testUser";
        List<AccountGridDTO> expectedGrid = new ArrayList<>();
        when(accountRepository.findAll(username)).thenReturn(expectedGrid);

        List<AccountGridDTO> actualGrid = accountService.getGridAccount(username);

        assertEquals(expectedGrid, actualGrid);
    }

    @Test
    void testSaveAccount() {
        InsertAccountDTO insertAccountDTO = new InsertAccountDTO("testUser", "testUser");
        Account accountEntity = new Account(insertAccountDTO.getUsername(), insertAccountDTO.getPassword());
        when(accountRepository.save(any(Account.class))).thenReturn(accountEntity);

        String accountId = accountService.saveAccount(insertAccountDTO);

        assertNotNull(accountId);
    }

    @Test
    void testGetUpdateAccount() {
        String username = "testUser";
        Account accountEntity = new Account(1L, "testUser", "password");
        when(accountRepository.findByUsername(username)).thenReturn(Optional.of(accountEntity));

        UpdateAccountDTO updateAccountDTO = accountService.getUpdateAccount(username);

        assertNotNull(updateAccountDTO);
        assertEquals(accountEntity.getId(), updateAccountDTO.getId());
        assertEquals(accountEntity.getUsername(), updateAccountDTO.getUsername());
        assertEquals(accountEntity.getPassword(), updateAccountDTO.getPassword());
    }

    @Test
    void testUpdateAccount() {
        UpdateAccountDTO updateAccountDTO = new UpdateAccountDTO(1L, "testUser", "newPassword");
        Account accountEntity = new Account(1L, "testUser", "newPassword");
        when(accountRepository.save(any(Account.class))).thenReturn(accountEntity);

        Long accountId = accountService.updateAccount(updateAccountDTO);

        assertNotNull(accountId);
    }

    @Test
    void testDeleteAccount() {
        Long accountId = 1L;

        accountService.deleteAccount(accountId);

        verify(accountRepository, times(1)).deleteById(accountId);
    }
}
