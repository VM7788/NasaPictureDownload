import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;



import java.io.IOException;


public class TelegramBot extends TelegramLongPollingBot {



    public static final String BOT_TOKEN = "6224673589:AAEIEO52rcMTW2wDYuGKzLHMclPgYLr22gQ";
    public static final String BOT_USERNAME = "VM7788_Bot";

    //Наша ссылка, на которую будем отправлять запрос
    public static final String URI = "https://api.nasa.gov/planetary/apod?api_key=LtgP62uYi3iB0PBiQX2y5teYc9YgadPUDwQUiaTk";
    public static long chat_id;


    public TelegramBot() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);

    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            chat_id = update.getMessage().getChatId();
            switch (update.getMessage().getText()) {
                case "/help":
                    sendMessage("Привет, я бот NASA! Я высылаю ссылки на картинки по запросу. " +
                            "Напоминаю, что картинки на сайте NASA обновляются раз в сутки");
                    break;
                case "/give":
                    try {
                        sendMessage(Utils.getUrl(URI));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    sendMessage("Я не понимаю :(");
            }
        }
    }


    private void sendMessage(String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(chat_id);
        message.setText(messageText);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
    // Done! Congratulations on your new bot. You will find it at t.me/VM7788_Bot. You can now add a description, about section and profile picture for your bot, see /help for a list of commands. By the way, when you've finished creating your cool bot, ping our Bot Support if you want a better username for it. Just make sure the bot is fully operational before you do this.

    // Use this token to access the HTTP API:
    //        6224673589:AAEIEO52rcMTW2wDYuGKzLHMclPgYLr22gQ
    // Keep your token secure and store it safely, it can be used by anyone to control your bot.

    // For a description of the Bot API, see this page: https://core.telegram.org/bots/api

