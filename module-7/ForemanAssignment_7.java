/*
Name: Natasha Foreman
Course: CSD 420 – Advanced Java 
Date: May 1st, 2026
Assignment: Module 7
Purpose: Display four circles using CSS styles.
*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ForemanAssignment_7 extends Application {

    @Override
    public void start(Stage stage) {

        HBox pane = new HBox(10);

        // Create circles
        Circle c1 = new Circle(40);
        Circle c2 = new Circle(40);
        Circle c3 = new Circle(40);
        Circle c4 = new Circle(40);

        // Apply style class (white with black stroke)
        c1.getStyleClass().add("whitecircle");
        c2.getStyleClass().add("whitecircle");

        // Apply IDs (red and green)
        c3.setId("redcircle");
        c4.setId("greencircle");

        pane.getChildren().addAll(c1, c2, c3, c4);

        Scene scene = new Scene(pane, 400, 150);

        // Load CSS
        scene.getStylesheets().add("mystyle.css");

        stage.setTitle("Four Circles");
        stage.setScene(scene);
        stage.show();

        // Test Code
        if (c3.getId().equals("redcircle") && c4.getId().equals("greencircle")) {
            System.out.println("Test Passed: Styles applied correctly.");
        } else {
            System.out.println("Test Failed.");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}