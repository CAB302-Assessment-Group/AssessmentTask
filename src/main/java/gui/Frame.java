package src.main.java.gui;

import src.main.java.maze.core.Maze;

import javax.swing.*;

public class Frame {
    public Frame() {
        super();
        // initialize swing window
        JFrame window = new JFrame();
        window.setSize(1200, 800);
        window.setVisible(true);

        // remove menu bar
        window.setLayout(null);

        window.setVisible(false);

        // make the program process close when the main window is closed
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // this is where "user customization" will come in
        int[] mazeSize = {5, 4};
        Maze myMaze = new Maze(mazeSize);

        // needs to be put into seperate "render" class
        for (int x = 0; x < myMaze.mazeSize()[0]; x++) {
            for (int y = 0; y < myMaze.mazeSize()[1]; y++) {
                // border styling
                JButton tempBTN = new JButton("");

                tempBTN.setBounds(x * 50, y * 50, 50, 50);
                window.add(tempBTN);
            }
        }

        window.setVisible(true);
    }
}
