package org.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.task.entity.UserAccount;

import java.util.List;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    @Query("select usacc from UserAccount usacc where usacc.userId=:userId")
    UserAccount getAccountInfoByUserId(@Param("userId") Integer userId);
}
