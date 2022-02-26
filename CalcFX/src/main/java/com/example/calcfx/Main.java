package com.example.calcfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    static Label digits = new Label("0"); //* Main user interface
    static int operator = 1; //Operator index

    static int intBeingProcessed; //? THIS [+-*/] X
    static int intProcessing; //? X [+-*/] THIS

    static boolean choosingIntProcessing = false;

    /**
    Calculates and outputs the result to the label based on the current operator index, using the 2 ints
     */
    static void calculate(){
        try {
            digits.setText(switch (operator) {
                case 1 -> String.valueOf(intBeingProcessed + intProcessing);
                case 2 -> String.valueOf(intBeingProcessed - intProcessing);
                case 3 -> String.valueOf(intBeingProcessed * intProcessing);
                case 4 -> String.valueOf(intBeingProcessed / intProcessing);
                default -> throw new IllegalStateException("Unexpected value: " + operator);
            });
        } catch (Exception e){digits.setText("0");} //! In case anything happens, just reset it to 0, to be safe
    }

    @Override
    public void start(Stage stage) {

        Group root = new Group();
        Scene scene = new Scene(root, 200, 300, Color.BLACK);

        //Formatting digits
        digits.setFont(Font.font(50));
        digits.setTranslateX(0);
        digits.setTranslateY(10);
        digits.setTextFill(Color.LIGHTGREEN);

        //Declaring, formatting, and assigning events to the Equals and Del buttons
        Button eq = new Button("=");
        eq.setTranslateX(50);
        eq.setTranslateY(5 * 50);
        eq.setPrefWidth(50);
        eq.setPrefHeight(50);
        eq.setOnMouseClicked(mouseEvent -> {
            calculate();
            intBeingProcessed = Integer.parseInt(digits.getText());
            choosingIntProcessing = false;
        });
        Button del = new Button("<");
        del.setTranslateX(100);
        del.setTranslateY(5 * 50);
        del.setPrefWidth(50);
        del.setPrefHeight(50);
        del.setOnMouseClicked(mouseEvent -> {
            try {
                digits.setText(digits.getText().substring(0, digits.getText().length() - 1));
            } catch (Exception ignored){}
            if (digits.getText().length() == 0) {
                digits.setText("0");
            }
            intBeingProcessed = Integer.parseInt(digits.getText());
        });

        //Generates a matrix-like arrangement of digit buttons
        int digitsText = 1;
        for (int i = 2; i < 5; i++){
            for (int j = 0; j < 3; j++){ //Lower the height o the digits
                root.getChildren().add(new Digit(String.valueOf(digitsText), j * 50, i * 50));
                digitsText++;
            }
        }
        root.getChildren().addAll(
          new Operator("+", 3 * 50, 2 * 50),
          new Operator("-", 3 * 50, 3 * 50),
          new Operator("*", 3 * 50, 4 * 50),
          new Operator("/", 3 * 50, 5 * 50),
          new Digit("0", 0, 5 * 50),
                digits, eq, del
        );

        stage.setTitle("CalcFX");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}