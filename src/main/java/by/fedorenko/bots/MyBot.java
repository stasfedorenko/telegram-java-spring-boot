package by.fedorenko.bots;

import by.fedorenko.entity.Task;
import by.fedorenko.entity.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.util.Set;

public class MyBot extends TelegramLongPollingBot {
    private static final String CHAT_ID_MY = "798726464";
    private static final String CHAT_ID_ALEXEY = "811344357";
    //     private static final String CHAT_ID_YRA = "313204287"; // Yra telega chat_id
    //    private static final String CHAT_ID_OLEG_AKULOV = "496687309";
    private static final String CHAT_ID_KONSTANTIN = "670159425";

    private static MyBot instance;

    private MyBot() {
    }

    public static MyBot getInstance() {
        if (instance == null) {
            instance = new MyBot();
        }
        return instance;
    }

    @Override
    public String getBotUsername() {
        return "@yellow_command_reports_bot";
    }

    @Override
    public String getBotToken() {
        return "5126244951:AAGGgW4_aHBDJBHsjegm8ppMl8pZOFFC9Yg";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                try {
                    execute(SendMessage.builder().chatId(message.getChatId().toString()).
                            text("Hello, " + message.getFrom().getFirstName()).build());
                    execute(SendMessage.builder().chatId(message.getChatId().toString()).
                            text("Have a nice day, " + message.getFrom().getFirstName()).build());
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendReports(String path) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(this);
        String PATH = path + "reports.pdf";
//        this.execute(SendMessage.builder().chatId(CHAT_ID_OLEG_AKULOV).text("Hi, this is our reports list").build());
//        this.execute(SendDocument.builder().chatId(CHAT_ID_OLEG_AKULOV).document(new InputFile(new File(PATH))).build());
        this.execute(SendMessage.builder().chatId(CHAT_ID_MY).text("Hi, this is our reports list").build());
        this.execute(SendDocument.builder().chatId(CHAT_ID_MY).document(new InputFile(new File(PATH))).build());
//        this.execute(SendMessage.builder().chatId(CHAT_ID_KONSTANTIN).text("Hi, this is our reports list").build());
//        this.execute(SendDocument.builder().chatId(CHAT_ID_KONSTANTIN).document(new InputFile(new File(PATH))).build());
//        this.execute(SendMessage.builder().chatId(CHAT_ID_YRA).text("Hi, this is our reports list").build());
//        this.execute(SendDocument.builder().chatId(CHAT_ID_YRA).document(new InputFile(new File(PATH))).build());
//        this.execute(SendMessage.builder().chatId(CHAT_ID_ALEXEY).text("Hi, this is our reports list").build());
//        this.execute(SendDocument.builder().chatId(CHAT_ID_ALEXEY).document(new InputFile(new File(PATH))).build());
    }

    public void sendTask(Task task) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(this);
        String nameTask = task.getNameTask();
        String descriptionTask = task.getDescriptionTask();
        Set<User> users = task.getUsers();
        for (User user : users) {
            this.execute(SendMessage.builder().chatId(user.getChatId())
                    .text("Hi, " + user.getFirstName() + " " + user.getLastName() +
                            ". This is your task, have a nice day.")
                    .build());
            this.execute(SendMessage.builder().chatId(user.getChatId())
                    .text("Task : " + nameTask)
                    .build());
            this.execute(SendMessage.builder().chatId(user.getChatId())
                    .text("Description : " + descriptionTask)
                    .build());
        }
    }
}
