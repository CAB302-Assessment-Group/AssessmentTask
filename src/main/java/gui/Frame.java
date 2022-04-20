package src.main.java.gui;

import src.main.java.maze.core.Maze;

import javax.swing.*;
import java.awt.*;

public class Frame {
    int[] mazeSize = new int[2];
    Maze myMaze;
    JFrame window;

    public Frame() {
        super();
        // initialize swing window
        window = new JFrame();
        window.setSize(1200, 800);

        // remove menu bar
        window.setLayout(null);

        window.setVisible(false);

        // make the program process close when the main window is closed
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initialise("2","2");


        // this is where "user customization" will come in

        // needs to be put into seperate "render" class


        window.setVisible(true);
    }

    public void initialise(String xvar, String yvar){
        window.getContentPane().removeAll();
        JLabel labelx = new JLabel("Width:");
        labelx.setBounds(0,0,50,20);

        JLabel labely = new JLabel("Height:");
        labely.setBounds(110,0,50,20);

        JTextArea inputx = new JTextArea(xvar);
        inputx.setBounds(50, 0, 50, 20);

        JTextArea inputy = new JTextArea(yvar);
        inputy.setBounds(160, 0, 50, 20);

        JButton setSize = new JButton("Set Size");
        setSize.setBounds(160, 30, 100, 20);

        window.add(inputx);
        window.add(labelx);
        window.add(inputy);
        window.add(labely);

        window.add(setSize);

        setSize.addActionListener(action -> setButtonPressed(inputx.getText(), inputy.getText()));
    }

    public void setButtonPressed(String width, String height){
        initialise(width.trim(), height.trim());

        //System.out.println(width.trim()+", "+height.trim());

        mazeSize = new int[]{Integer.parseInt(width.trim()),Integer.parseInt(height.trim())};
        myMaze = new Maze(mazeSize);
        for (int x = 0; x < myMaze.mazeSize()[0]; x++) {
            for (int y = 0; y < myMaze.mazeSize()[1]; y++) {
                // border styling
                JButton tempBTN = new JButton("");
                tempBTN.setBounds(10 + x * 50, 75 + y * 50, 40, 10);
                int finalX = x;
                int finalY = y;
                tempBTN.addActionListener(action -> mazeButtonPressed(finalX,finalY, tempBTN));
                tempBTN.setBackground(Color.WHITE);
                window.add(tempBTN);

                JButton tempBTN2 = new JButton("");
                tempBTN2.setBounds(x * 50, 10+75 + y * 50, 10, 40);
                tempBTN2.addActionListener(action -> mazeButtonPressed(finalX,finalY, tempBTN2));
                tempBTN2.setBackground(Color.WHITE);
                window.add(tempBTN2);
            }
        }

        for(int i = 0; i < myMaze.mazeSize()[1]; i++){
            JButton tempBTN2 = new JButton("");
            tempBTN2.setBounds(myMaze.mazeSize()[0] * 50, 10+75 + i * 50, 10, 40);
            int finalX = myMaze.mazeSize()[0];
            int finalY = i;
            tempBTN2.addActionListener(action -> mazeButtonPressed(finalX,finalY, tempBTN2));
            tempBTN2.setBackground(Color.WHITE);
            window.add(tempBTN2);
        }

        for(int i = 0; i < myMaze.mazeSize()[0]; i++){
            JButton tempBTN = new JButton("");
            tempBTN.setBounds(10 + i * 50, 75 + myMaze.mazeSize()[1] * 50, 40, 10);
            int finalX = i;
            int finalY = myMaze.mazeSize()[1];
            tempBTN.addActionListener(action -> mazeButtonPressed(finalX,finalY, tempBTN));
            tempBTN.setBackground(Color.WHITE);
            window.add(tempBTN);
        }


        SwingUtilities.updateComponentTreeUI(window);
    }

    public void mazeButtonPressed(int x, int y, JButton button){
        if(button.getBackground() == Color.BLACK){
            button.setBackground(Color.WHITE);
        }else{
            button.setBackground(Color.BLACK);
        }


        //Prints the coords of the button pressed
        System.out.println(x + ", " + y);

    }
}
