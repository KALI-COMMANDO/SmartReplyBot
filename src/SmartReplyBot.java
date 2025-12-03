// File: SmartReplyBot.java
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SmartReplyBot extends TelegramLongPollingBot {

    private final String BOT_USERNAME = "surajbhaie_bot";  // apna bot username
    private final String BOT_TOKEN = "7820854876:AAHI9clQ7QNhlkGIeY8vSiRG8EDI8cJC_x8"; // naya token, purana revoke kar do

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
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {

                String userMsg = update.getMessage().getText().toLowerCase();
                long chatId = update.getMessage().getChatId();

                // ✔️ /start command handling
                if (userMsg.equals("/start")) {
                    SendMessage reply = SendMessage.builder()
                            .chatId(String.valueOf(chatId))
                            .text("Hello bhai! Ab tum mujhse chat kar sakte ho 🙂")
                            .build();
                    execute(reply);
                }

                // ✔️ HELLO
                else if (userMsg.contains("hello")) {
                    SendMessage reply = SendMessage.builder()
                            .chatId(String.valueOf(chatId))
                            .text("Hello bhai! Kaise ho? 🙂")
                            .build();
                    execute(reply);
                }

                // ✔️ HI
                else if (userMsg.equals("hi") || userMsg.startsWith("hi ")) {
                    SendMessage reply = SendMessage.builder()
                            .chatId(String.valueOf(chatId))
                            .text("Hi bhai! Batao kya haal hai? 👋")
                            .build();
                    execute(reply);
                }

                // ✔️ HEY (capital/small sab chalega)
                else if (userMsg.equals("hey") || userMsg.startsWith("hey ")) {
                    SendMessage reply = SendMessage.builder()
                            .chatId(String.valueOf(chatId))
                            .text("Hey bhai! Kya scene hai? 😎")
                            .build();
                    execute(reply);
                }

                // ❌ Baaki sab par bot reply nahi karega
            }

        } catch (TelegramApiException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new SmartReplyBot());
            System.out.println("SmartReplyBot started...");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
