package src.main.java.gui;

import src.main.java.maze.core.Maze;

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

    public void Render(Maze selectedMaze, Frame window) {
        // x rows
        for (int x = 0; x < selectedMaze.getSize()[0]; x++) {
            // nested for loop for y rows
            for (int y = 0; y < selectedMaze.getSize()[1]; y++) {
                
            }
        }
    }
}
