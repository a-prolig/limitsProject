package org.task.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "user_accounts")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;
    @Column(name = "account_number")
    public String accountNumber;
    @Column(name = "balance")
    public BigDecimal balance;
    @Column(name = "custom_limit")
    public BigDecimal customLimit;
    @Column(name = "user_id")
    public Integer userId;
}
