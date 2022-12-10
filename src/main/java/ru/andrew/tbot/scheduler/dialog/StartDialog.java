package ru.andrew.tbot.scheduler.dialog;

import org.springframework.stereotype.Component;
import ru.andrew.tbot.scheduler.dto.DialogDto;

import static ru.andrew.tbot.configuration.BotConstants.GREETING_MSG;
import static ru.andrew.tbot.configuration.BotConstants.INITIAL_MSG;

@Component
public class StartDialog implements DialogInterface {
    @Override
    public boolean isSupport(DialogDto dialogDto) {
        return dialogDto.message().equals(INITIAL_MSG);
    }

    @Override
    public boolean process(DialogDto dialogDto) {
        return true;
    }

    @Override
    public String getMessage() {
        return GREETING_MSG;
    }
}
