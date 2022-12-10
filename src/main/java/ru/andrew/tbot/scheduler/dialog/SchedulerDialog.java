package ru.andrew.tbot.scheduler.dialog;

import org.springframework.stereotype.Component;
import ru.andrew.tbot.scheduler.dto.DialogDto;
import ru.andrew.tbot.scheduler.entity.NotificationTaskEntity;
import ru.andrew.tbot.scheduler.exception.IntervalDateIncorrectException;
import ru.andrew.tbot.scheduler.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.andrew.tbot.configuration.BotConstants.*;

@Component
public class SchedulerDialog implements DialogInterface {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    private static final Pattern REGEX_PATTERN = Pattern.compile(REMINDER_TEXT_PATTERN);
    private static Matcher matcher;
    private final NotificationTaskRepository repository;

    public SchedulerDialog(NotificationTaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isSupport(DialogDto dialogDto) {
        matcherInitial(dialogDto);
        return matcher.matches();
    }

    private void matcherInitial(DialogDto dialogDto) {
        matcher = REGEX_PATTERN.matcher(dialogDto.message());
    }

    @Override
    public boolean process(DialogDto dialogDto) {
        if (Objects.isNull(matcher)) {
            matcherInitial(dialogDto);
        }

        NotificationTaskEntity entity = getEntity(dialogDto);
        repository.save(entity);

        return true;
    }

    private NotificationTaskEntity getEntity(DialogDto dialogDto) {
        LocalDateTime dateTime = LocalDateTime.parse(matcher.group(1), DATE_FORMATTER);
        checkDateTimeInterval(dateTime);
        return (new NotificationTaskEntity())
                .setChatId(dialogDto.chatId())
                .setNotifyDatetime(dateTime)
                .setNotifyMessage(matcher.group(2));
    }

    private void checkDateTimeInterval(LocalDateTime dateTime) {
        int compareResult = dateTime.compareTo(LocalDateTime.now());
        if (compareResult < 0) {
            throw new IntervalDateIncorrectException();
        }
    }

    @Override
    public String getMessage() {
        return ACCEPTED_TASK_MSG;
    }
}