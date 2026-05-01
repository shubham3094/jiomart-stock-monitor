package com.stockmonitor.service;

import com.stockmonitor.config.ConfigLoader;
import java.net.URL;
import java.net.URLEncoder;

public class TelegramService {
    public static void send(String message) {
        try {
            String token = ConfigLoader.get("bot.token");
            String chatId = ConfigLoader.get("chat.id");

            String url = "https://api.telegram.org/bot" + token +
                    "/sendMessage?chat_id=" + chatId +
                    "&text=" + URLEncoder.encode(message, "UTF-8");

            new URL(url).openStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}