package com.demo.telegrambot.bot;


import com.demo.telegrambot.entity.City;
import com.demo.telegrambot.repository.CityRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;


@Component
@PropertySource("classpath:bot.properties")
public class GuideBot extends TelegramLongPollingBot {

    @Value("${token}")
    private String token;

    @Value("${username}")
    private String username;

    @Autowired
    CityRepositiry cityRepositiry;


    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
    }

    @Override
    public String getBotUsername() {
        return username;
    }


    @Override
    public void onUpdatesReceived(List<Update> updates) {

        City requiredCity = null;
        Update update = updates.get(0);
        if (update.hasMessage() && update.getMessage().hasText()) {
            String userText = update.getMessage().getText();


            SendMessage message;
            if (!"/start".equalsIgnoreCase(userText)) {
                for (City city : cityRepositiry.findAll()) {
                    if (userText.equalsIgnoreCase(city.getName())) {
                        requiredCity = city;
                    }
                }
                if (requiredCity != null) {
                    message = new SendMessage()
                            .setChatId(update.getMessage().getChatId())
                            .setText(requiredCity.getDescription().getText());
                } else {
                    message = new SendMessage()
                            .setChatId(update.getMessage().getChatId())
                            .setText("К сожелению по требуемому городу данных не найденно");
                }
            } else {
                message = new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("Введите город, и я подскажу какую достопремечательность стоит посетить");
            }

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}