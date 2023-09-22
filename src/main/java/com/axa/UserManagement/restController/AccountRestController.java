package com.axa.UserManagement.restController;

import com.axa.UserManagement.dto.account.AccountGridDTO;
import com.axa.UserManagement.dto.account.InsertAccountDTO;
import com.axa.UserManagement.dto.account.UpdateAccountDTO;
import com.axa.UserManagement.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    private static final Logger logger = LoggerFactory.getLogger(AccountRestController.class);

    @GetMapping
    public ResponseEntity<Object> get(String username){
        username = (username == null) ? "" : username;
        try{
            List<AccountGridDTO> accounts = accountService.getGridAccount(username);
            logger.info("Endpoint: getAllAccount/count/" + accounts.stream().count());
            return ResponseEntity.status(HttpStatus.OK).body(accounts);
        } catch (Exception e){
            logger.error("Endpoint: failGetAllAccount/error/" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody InsertAccountDTO dto){
        try{
            String respondId = accountService.saveAccount(dto);
            logger.info("Endpoint: postAccount/save/" + respondId);
            return ResponseEntity.status(HttpStatus.CREATED).body(respondId);
        } catch (Exception e){
            logger.error("Endpoint: failPostAccount/error/" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }
    }

    @GetMapping("/id={username}")
    public ResponseEntity<Object> getUpdate(@PathVariable(required = true) String username){
        try{
            UpdateAccountDTO dto = accountService.getUpdateAccount(username);
            logger.info("Endpoint: getAccountByUsername/get/" + dto.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } catch (Exception exception){
            logger.error("Endpoint: failGetAccount/error/" + exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody UpdateAccountDTO dto){
        try{
            Long respondId = accountService.updateAccount(dto);
            logger.info("Endpoint: updateAccount/update/" + respondId.longValue());
            return ResponseEntity.status(HttpStatus.CREATED).body(respondId);
        } catch (Exception e){
            logger.error("Endpoint: failUpdateAccount/error/" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }
    }

    @DeleteMapping("/id={id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) Long id){
        try{
            accountService.deleteAccount(id);
            logger.info("Endpoint: deleteAccount/delete/" + id);
            return ResponseEntity.status(HttpStatus.OK).body(id);
        }catch (Exception e){
            logger.error("Endpoint: failDeleteAccount/error/" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }
    }
}
