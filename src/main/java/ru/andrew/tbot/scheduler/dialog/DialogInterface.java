package ru.andrew.tbot.scheduler.dialog;

import ru.andrew.tbot.scheduler.dto.DialogDto;

public interface DialogInterface {
    boolean isSupport(DialogDto dialogDto);
    boolean process(DialogDto dialogDto);
    String getMessage();
}
