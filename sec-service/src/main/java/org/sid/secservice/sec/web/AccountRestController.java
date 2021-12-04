package org.sid.secservice.sec.web;

import lombok.Data;
import org.sid.secservice.sec.entities.AppRole;
import org.sid.secservice.sec.entities.AppUser;
import org.sid.secservice.sec.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountRestController {

    private AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/users")
    public List<AppUser> appUsers(){
        return accountService.USERS();
    }

    @PostMapping(path = "/users/")
    public AppUser saveUser(@RequestParam AppUser appUser){
        return accountService.addNewUser(appUser);
    }

    @PostMapping(path = "/roles/")
    public AppRole saveRole(@RequestParam AppRole appRole){
        return accountService.addNewRole(appRole);
    }

    public void addRoleToUser(RoleUserForm roleUserForm){
        accountService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRolename());
    }
}

@Data
class RoleUserForm{
    private String username;
    private String rolename;
}