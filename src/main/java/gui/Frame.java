package src.main.java.gui;

import src.main.java.maze.core.Maze;

import javax.swing.*;

public class Frame {
    int[] mazeSize = new int[2];
    Maze myMaze;
    JFrame window;

    public Frame() {
        super();
        // initialize swing window
        window = new JFrame();
        window.setSize(1200, 800);
        window.setVisible(true);

        // remove menu bar
        window.setLayout(null);

        window.setVisible(false);

        // make the program process close when the main window is closed
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel labelx = new JLabel("Width:");
        labelx.setBounds(0,0,50,20);

        JLabel labely = new JLabel("Height:");
        labely.setBounds(110,0,50,20);

        JTextArea inputx = new JTextArea("5");
        inputx.setBounds(50, 0, 50, 20);

        JTextArea inputy = new JTextArea("4");
        inputy.setBounds(160, 0, 50, 20);

        JButton setSize = new JButton("Set Size");
        setSize.setBounds(160, 30, 100, 20);

        window.add(inputx);
        window.add(labelx);
        window.add(inputy);
        window.add(labely);

        window.add(setSize);

        setSize.addActionListener(action -> setButtonPressed(Integer.parseInt(inputx.getText()), Integer.parseInt(inputy.getText())));

        // this is where "user customization" will come in

        // needs to be put into seperate "render" class


        window.setVisible(true);
    }
    public void setButtonPressed(int width, int height){
        mazeSize = new int[]{width,height};
        myMaze = new Maze(mazeSize);
        for (int x = 0; x < myMaze.mazeSize()[0]; x++) {
            for (int y = 0; y < myMaze.mazeSize()[1]; y++) {
                // border styling
                JButton tempBTN = new JButton("");

                tempBTN.setBounds(x * 50, 75 + y * 50, 50, 50);
                window.add(tempBTN);
            }
        }
        SwingUtilities.updateComponentTreeUI(window);
    }
}
