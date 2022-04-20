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

        int[] mazeSize = {5, 4};
        Maze myMaze = new Maze(mazeSize);
    }
}
