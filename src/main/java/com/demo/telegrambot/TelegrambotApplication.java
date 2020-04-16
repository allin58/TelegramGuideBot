package com.demo.telegrambot;

import com.demo.telegrambot.bot.GuideBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TelegrambotApplication {

	private static GuideBot guideleBotStatic;

	@Autowired
	public GuideBot guideleBot;

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(TelegrambotApplication.class, args);
		TelegramBotsApi botsApi = new TelegramBotsApi();
		try {
			botsApi.registerBot(guideleBotStatic);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}

	}

	@PostConstruct
	private void init() {

		guideleBotStatic = this.guideleBot;

	}

}
