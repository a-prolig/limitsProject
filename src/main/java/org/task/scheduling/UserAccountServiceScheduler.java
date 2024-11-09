package org.task.scheduling;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.task.service.UserAccountService;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class UserAccountServiceScheduler implements SchedulingConfigurer {
    private static final String CRON = "0 5 1 * * *";
    private final UserAccountService userAccountService;

    public Executor taskExecutor() {
        return Executors.newSingleThreadScheduledExecutor();
    }

    public UserAccountServiceScheduler(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
        taskRegistrar.addCronTask(userAccountService::executeServiceScheduler, CRON);
    }
}
