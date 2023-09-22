package com.axa.UserManagement.repository;

import com.axa.UserManagement.dto.account.AccountGridDTO;
import com.axa.UserManagement.dto.account.UpdateAccountDTO;
import com.axa.UserManagement.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("""
            SELECT new com.axa.UserManagement.dto.account.AccountGridDTO(a.id, a.username)
            FROM Account AS a
            WHERE a.username LIKE %:username%
            """)
    List<AccountGridDTO> findAll(@Param("username") String username);

    @Query("""
            SELECT new com.axa.UserManagement.entity.Account(a.id, a.username, a.password)
            FROM Account AS a
            WHERE a.username = :username
            """)
    Optional<Account> findByUsername(@Param("username") String username);
}
