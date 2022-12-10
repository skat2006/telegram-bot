package ru.andrew.tbot.scheduler.dialog;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.andrew.tbot.scheduler.dto.DialogDto;

import static ru.andrew.tbot.configuration.BotConstants.PROBLEM_OCCURS_MSG;

@Component
@Order(-1)
public class UnknownDialog implements DialogInterface {
    @Override
    public boolean isSupport(DialogDto dialogDto) {
        return true;
    }

    @Override
    public boolean process(DialogDto dialogDto) {
        return true;
    }

    @Override
    public String getMessage() {
        return PROBLEM_OCCURS_MSG;
    }
}
