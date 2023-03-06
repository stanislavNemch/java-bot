package com.example.javabot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class AcademyBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        System.out.println("Message received " + message.getText());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Hello user! I received your message: " + message.getText());
        sendMessage.setChatId(String.valueOf(message.getChatId()));

        if (message.getText().equals("/start")) {
            String text = "Welcome to Recipe bot! Please pass the meal of the day!\n";
//            text += "Possible option are:\n";
//            text += "breakfast\n";
//            text += "dinner\n";
//            text += "lunch\n";
//            text += "supper\n";

            sendMessage.enableMarkdown(true);
            ReplyKeyboardMarkup keyboardMarkup = getMenuKeyboard();
            sendMessage.setReplyMarkup(keyboardMarkup);
            sendMessage.setText(text);
        }

        if (message.getText().equals("breakfast")) {
            String menu = "Breakfast menu!\n";

            menu += "1. Blueberry-Banana-Nut Smoothie\n";
            menu += "2. Classic Omelet ad Greens\n";
            menu += "3. Curry-Avocado Crispy Egg Toast\n";

            sendMessage.setText(menu);
        }

        if (message.getText().equals("dinner")) {
            String menu = "Dinner menu!\n";

            menu += "1. Creamy Lemon Chicken Pasta\n";
            menu += "2. Turkey Tacos\n";
            menu += "3. Vegetarian Lasagna\n";

            sendMessage.setText(menu);
        }

        if (message.getText().equals("lunch")) {
            String text = "Lunch menu is in progress...";
            sendMessage.setText(text);
        }

        if (message.getText().equals("supper")) {
            String text = "Supper menu is in progress...";
            sendMessage.setText(text);
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private ReplyKeyboardMarkup getMenuKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("breakfast");
        keyboardRow.add("dinner");
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add("lunch");
        keyboardSecondRow.add("supper");
        keyboardRows.add(keyboardRow);
        keyboardRows.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }

    @Override
    public String getBotToken() {
        return "6173954074:AAFWFuHPHEd38d1OQApuAmSMFOu68SgPvDc";
    }

    @Override
    public String getBotUsername() {
        return "recipe_by_bcstas_bot";
    }
}
