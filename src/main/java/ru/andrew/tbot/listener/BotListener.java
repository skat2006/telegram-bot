package ru.andrew.tbot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.andrew.tbot.service.BotService;

import java.util.List;

@Service
public class BotListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(BotListener.class);
    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    private BotService botService;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Обновление: {}", update);
            botService.process(update);
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
