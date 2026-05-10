/*
Name: Natasha Foreman
Course: CSD 420 – Advanced Java 
Date: May 10th, 2026
Assignment: Module 8
Purpose: Use three threads to display random letters, numbers, and special characters in a JavaFX text area.
*/

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class NatashaThreeThreads extends Application {

    private static final int COUNT = 10000;

    private TextArea textArea = new TextArea();

    private AtomicInteger letterCount = new AtomicInteger(0);
    private AtomicInteger numberCount = new AtomicInteger(0);
    private AtomicInteger specialCount = new AtomicInteger(0);

    @Override
    public void start(Stage stage) {

        textArea.setWrapText(true);

        Scene scene = new Scene(textArea, 700, 400);
        stage.setTitle("Natasha Three Threads");
        stage.setScene(scene);
        stage.show();

        Thread letters = new Thread(() -> generateLetters());
        Thread numbers = new Thread(() -> generateNumbers());
        Thread specials = new Thread(() -> generateSpecialCharacters());

        letters.start();
        numbers.start();
        specials.start();

        Thread testThread = new Thread(() -> {
            try {
                letters.join();
                numbers.join();
                specials.join();

                Platform.runLater(() -> {
                    textArea.appendText("\n\nTest Results:");
                    textArea.appendText("\nLetters generated: " + letterCount.get());
                    textArea.appendText("\nNumbers generated: " + numberCount.get());
                    textArea.appendText("\nSpecial characters generated: " + specialCount.get());

                    if (letterCount.get() == COUNT &&
                        numberCount.get() == COUNT &&
                        specialCount.get() == COUNT) {
                        textArea.appendText("\nTest Passed: All threads generated 10,000 characters.");
                    } else {
                        textArea.appendText("\nTest Failed.");
                    }
                });

            } catch (InterruptedException e) {
                Platform.runLater(() -> textArea.appendText("\nThread interrupted."));
            }
        });

        testThread.start();
    }

    public void generateLetters() {
        Random random = new Random();

        for (int i = 0; i < COUNT; i++) {
            char letter = (char) ('a' + random.nextInt(26));
            Platform.runLater(() -> textArea.appendText(String.valueOf(letter)));
            letterCount.incrementAndGet();
        }
    }

    public void generateNumbers() {
        Random random = new Random();

        for (int i = 0; i < COUNT; i++) {
            char number = (char) ('0' + random.nextInt(10));
            Platform.runLater(() -> textArea.appendText(String.valueOf(number)));
            numberCount.incrementAndGet();
        }
    }

    public void generateSpecialCharacters() {
        Random random = new Random();
        char[] characters = {'!', '@', '#', '$', '%', '&', '*'};

        for (int i = 0; i < COUNT; i++) {
            char symbol = characters[random.nextInt(characters.length)];
            Platform.runLater(() -> textArea.appendText(String.valueOf(symbol)));
            specialCount.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}