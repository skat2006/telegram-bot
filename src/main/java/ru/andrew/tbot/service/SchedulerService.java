package ru.andrew.tbot.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.andrew.tbot.scheduler.entity.NotificationTaskEntity;
import ru.andrew.tbot.scheduler.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.util.List;

import static ru.andrew.tbot.configuration.BotConstants.CRON_TASK_PERIOD;

@Service
public class SchedulerService {
    private final NotificationTaskRepository repository;
    private final BotService botService;

    public SchedulerService(
            NotificationTaskRepository repository,
            BotService botService
    ) {
        this.repository = repository;
        this.botService = botService;
    }

    @Scheduled(cron = CRON_TASK_PERIOD)
    public void run() {
        List<NotificationTaskEntity> tasks = repository
                .findByNotifyDatetimeLessThanEqual(LocalDateTime.now());

        tasks.forEach(task -> {
            botService.sendResponse(
                    task.getChatId(),
                    String.format("Вы просили напомнить о '%s'", task.getNotifyMessage())
            );
            repository.delete(task);
        });
    }
}