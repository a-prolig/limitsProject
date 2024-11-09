package org.task.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.task.entity.UserAccount;
import org.task.repository.UserAccountRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserAccountServiceImpl implements UserAccountService {
    @Value("${baseLimit}")
    private double baseLimit;
    private final UserAccountRepository repository;

    public UserAccountServiceImpl(UserAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserAccount> findAll() {
        return repository.findAll();
    }

    @Override
    public UserAccount save(UserAccount userAccount) {
        UserAccount entity;
        try {
            entity = repository.saveAndFlush(userAccount);

        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw e;
        }
        return entity;
    }

    @Override
    public UserAccount getAccountInfoByUserId(Integer userId) {
        UserAccount userAccount = new UserAccount();
        if (userId > 100) {
            log.info(String.format("Сохранение нового пользователя userId=%s",  userId));
            userAccount.setUserId(userId);
            userAccount.setAccountNumber(String.format("100%s", userId));
            userAccount.setCustomLimit(BigDecimal.valueOf(baseLimit));
            userAccount.setBalance(BigDecimal.valueOf(0));
            save(userAccount);
        } else {
            userAccount = repository.getAccountInfoByUserId(userId);
        }
        return userAccount;
    }

    @Override
    public UserAccount updateUserLimit(Integer userId, BigDecimal amount) {
        UserAccount userAccount = getAccountInfoByUserId(userId);
        BigDecimal balance = userAccount.getBalance();
        String accountNumber = userAccount.getAccountNumber();
        BigDecimal limit = userAccount.getCustomLimit();
        if (Objects.isNull(balance) || balance.compareTo(amount) < 0) {
            throw new RuntimeException(String.format("Product accountNumber=%s has less balance then current amount",  accountNumber));
        }
        if (limit.intValue() == 0 || limit.compareTo(amount) < 0) {
            throw new RuntimeException(String.format("Product accountNumber=%s has less limit then current amount",  accountNumber));
        }
        BigDecimal newBalance = balance.subtract(amount);
        BigDecimal newLimit = limit.subtract(amount);
        userAccount.setCustomLimit(newLimit);
        userAccount.setBalance(newBalance);
        return save(userAccount);
    }

    public void executeServiceScheduler() {
        log.info("Scheduler started!");
        List<UserAccount> userAccountList = findAll();
        userAccountList.forEach(userAccount -> {
            userAccount.setCustomLimit(BigDecimal.valueOf(baseLimit));
            save(userAccount);
        });
    }
}
