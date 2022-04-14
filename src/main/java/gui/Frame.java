package src.main.java.gui;

import javax.swing.*;

public class Frame {
    public Frame() {
        super();

        // initialize swing window
        JFrame window = new JFrame();
        window.setSize(500, 400);
        window.setVisible(true);

        // remove menu bar
        window.setLayout(null);
    }
}
