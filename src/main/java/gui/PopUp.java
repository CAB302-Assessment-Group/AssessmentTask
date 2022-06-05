package gui;

import javax.swing.*;

public class PopUp {
    private String message;
    public static JFrame popUp;

    private final int WIDTH = 200;
    private final int HEIGHT = 100;

    public PopUp(String message) {
        this.message = message;
        popUp = new JFrame("Error Message Popup");

        popUp.setLayout(null);

        // make the program process close when the main window is closed
        popUp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        popUp.setVisible(true);

    }
}

