package ru.andrew.tbot.scheduler.exception;

import static ru.andrew.tbot.configuration.BotConstants.USE_DATE_IN_FUTURE;

public class IntervalDateIncorrectException extends RuntimeException {
    public IntervalDateIncorrectException() {
        super(USE_DATE_IN_FUTURE);
    }
}
