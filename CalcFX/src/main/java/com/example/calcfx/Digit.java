package com.example.calcfx;

import javafx.scene.control.Button;

public class Digit extends Button {

    Digit(String text, int x, int y){

        //Formatting the button
        super(text);
        this.setPrefWidth(50);
        this.setPrefHeight(50);
        this.setTranslateX(x);
        this.setTranslateY(y);

        this.setOnMouseClicked(mouseEvent -> {
                try { //! Exceptions EVERYWHERE jesus christ
                    if (Main.digits.getText().equals("0")) {
                        Main.digits.setText("");
                    }

                    try { //! There was an exception when typing too many digits, and I was too lazy to figure it out, so I just made this
                        Main.digits.setText(Main.digits.getText() + text);
                    } catch (Exception ignored) {}

                    //* Choosing the number to sum/sub/mul/div
                    if (Main.choosingIntProcessing) {
                        Main.intProcessing = Integer.parseInt(Main.digits.getText());
                    } else { //* This means the user has already chosen an operator, so choose the number to sum/sub/mul/div by
                        Main.intBeingProcessed = Integer.parseInt(Main.digits.getText());
                        Main.intProcessing = 0; //If the user edits the current number, this will reset the chosen operator
                        Main.operator = 1; //Anything added to 0 remains the same, and 1 is the operator index for addition
                    }
                } catch (Exception ignored){}
            }
        );
    }
}