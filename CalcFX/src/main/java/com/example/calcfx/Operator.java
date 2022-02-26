package com.example.calcfx;

import javafx.scene.control.Button;

public class Operator extends Button {

    Operator(String operatorIndex, int x, int y){

        //Formatting the button
        super(operatorIndex);
        this.setPrefWidth(50);
        this.setPrefHeight(50);
        this.setTranslateX(x);
        this.setTranslateY(y);

        this.setOnMouseClicked(mouseEvent -> {
            Main.operator = switch (operatorIndex){ //Choosing the operator index
                case "+" -> 1;
                case "-" -> 2;
                case "*" -> 3;
                case "/" -> 4;
                default -> throw new IllegalStateException("Unexpected value: " + operatorIndex);
            };
            Main.intBeingProcessed = Integer.parseInt(Main.digits.getText());
            Main.digits.setText("0"); //Resetting Main.digits now to avoid having to make useless booleans in the Digit class
            Main.choosingIntProcessing = true;
        });
    }
}