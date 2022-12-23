package ru.andrew.tbot.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.andrew.tbot.scheduler.entity.NotificationTaskEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationTaskRepository extends JpaRepository<NotificationTaskEntity, Long> {
    List<NotificationTaskEntity> findByNotifyDatetimeLessThanEqual(LocalDateTime dateTime);
}
