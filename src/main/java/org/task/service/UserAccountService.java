package org.task.service;

import org.task.entity.UserAccount;

import java.math.BigDecimal;
import java.util.List;

public interface UserAccountService {
    List<UserAccount> findAll();
    UserAccount save(UserAccount limit);
    UserAccount getAccountInfoByUserId(Integer userId);
    UserAccount updateUserLimit(Integer userId, BigDecimal amount);
    void executeServiceScheduler();
}
