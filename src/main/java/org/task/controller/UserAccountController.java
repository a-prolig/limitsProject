package org.task.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.task.entity.UserAccount;
import org.task.model.RequestDto;
import org.task.service.UserAccountService;

import java.util.List;

@RestController
@RequestMapping("/limits")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/")
    public List<UserAccount> getAllProducts() {
        return userAccountService.findAll();
    }

    @GetMapping(value = "/userId:{userId}")
    public UserAccount getAccountInfoByUserId(@PathVariable Integer userId) {
        return userAccountService.getAccountInfoByUserId(userId);
    }

    @PostMapping("/executePayment")
    public UserAccount executePayment(@RequestBody RequestDto dto) {
        return userAccountService.updateUserLimit(dto.userId, dto.amount);
    }

}
