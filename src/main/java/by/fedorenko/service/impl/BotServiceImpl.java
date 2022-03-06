package by.fedorenko.service.impl;

import by.fedorenko.bots.MyBot;
import by.fedorenko.exception.ServiceException;
import by.fedorenko.service.BotService;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotServiceImpl implements BotService {

    @Override
    public void runBot() throws ServiceException {
        MyBot bot = MyBot.getInstance();

        if (bot == null) {
            throw new ServiceException("Bot is not running");
        }


        try {
            bot.sendReports();
        } catch (TelegramApiException e) {
            throw new ServiceException("Bot didn't send message", e);
        }
    }
}
