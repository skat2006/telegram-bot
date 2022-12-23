package ru.andrew.tbot.configuration;

public class BotConstants {
    public static final String INITIAL_MSG =            "/start";
    public static final String PROBLEM_OCCURS_MSG =     "Пожалуйста пишите в формате 'dd.mm.yyyy hh:mm Текст напоминания' для установки напоминания.";
    public static final String GREETING_MSG =           "Приветствую!\n" +
                                                        "Я смогу напомнить Вам о чем угодно, только попросите.\n" + PROBLEM_OCCURS_MSG;
    public static final String ACCEPTED_TASK_MSG =      "Хорошо, я обязательно вам напомню об этом!";
    public static final String USE_DATE_IN_FUTURE =     "Вы пытаетесь установить напоминание в прошлое. \n" +
                                                        "Измените пожалуйста дату/время на будущее!";
    public static final String DATE_TIME_FORMAT =       "dd.MM.yyyy HH:mm";
    public static final String CRON_TASK_PERIOD =       "*/1 * * * * *";
    public static final String REMINDER_TEXT_PATTERN =  "([0-9\\.\\:\\s]{16})\\s([\\w|\\W+]+)";
}
