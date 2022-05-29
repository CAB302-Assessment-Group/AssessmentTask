package gui;


import maze.core.Maze;
import gui.Frame;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gui.Frame.initialise;
import static gui.Frame.window;

public class Render {
    //private static JFrame window = Frame.getInstance().window;
    private static JFrame window2 = Frame.getInstance().window2;



    //private static JFrame window2 = Frame.getInstance().window2;
    //tried to add maze generation panel, not sure how to take panel from frame class
    //private static JPanel MazeGenerationPanel = new JPanel();
    private static int[] mazeSize = Frame.getInstance().mazeSize;
    private static Maze myMaze = Frame.getInstance().myMaze;
    /**
     * Validates user input for sizing maze
     * @author Hudson
     * @param inputs The width and height specified by the user
     * @return True if input is a number and not less than or equal to 0. False if not
     */
    public static boolean validateInput(String[] inputs) {
        for (int i = 0; i < inputs.length; i++) {
            try {
                if (Integer.parseInt(inputs[i]) <= 0) return false;
            } catch(Exception e) {
                return false;
            }
        }

        return true;
    }


    /**
     * @author Jayden and Hudson
     * Actions that take place after the user has clicked the 'Generate Maze' button
     * @param width Width specified by the user
     * @param height Height specified by the user
     */
    public static void setButtonPressed(String width, String height){


        String[] inputs = {width, height};
        //System.out.println(width);
        //System.out.println(height);

        if (!validateInput(inputs)) {
            System.out.println("[ERROR] Invalid maze size...");
            return;
        }

        //initialise(width.trim(), height.trim());

        //System.out.println(width.trim()+", "+height.trim());

        mazeSize = new int[]{Integer.parseInt(width.trim()),Integer.parseInt(height.trim())};
        myMaze = new Maze(mazeSize); //need to pass child maze param

        myMaze.generateMaze();

        int scale_factor = 1;
        //maze generation starting position on frame
        int xposition = 0;
        int yposition = 0;

        int horizontal_wall_length = 40;
        int horizontal_wall_width = 10;

        int vertical_wall_length = 40;
        int vertical_wall_width = 10;

        int xdistance_between_horizontal_walls = 50;
        int ydistance_between_horizontal_walls = 50;

        int xdistance_between_vertical_walls = 50;
        int ydistance_between_vertical_walls = 50;

        for (int x = 0; x < myMaze.mazeSize()[0]; x++) {
            for (int y = 0; y < myMaze.mazeSize()[1]; y++) {
                // border styling
                JButton tempBTN = new JButton("");
                //tempBTN.setBounds(10 + x * 50, 75 + y * 50, 40, 10);
                tempBTN.setBounds(10+xposition + x * (xdistance_between_horizontal_walls) * (scale_factor), yposition + y * ydistance_between_horizontal_walls * scale_factor, horizontal_wall_length * scale_factor, horizontal_wall_width);
                int finalX = x;
                int finalY = y;
                tempBTN.addActionListener(action -> mazeButtonPressed(finalX,finalY, tempBTN, true));
                tempBTN.setBackground(y == 0 ? Color.BLACK : Color.WHITE);

                // change container from window to MazeGenerationPanel
                //MazeGenerationPanel.add(tempBTN);

                //Jayden's update
                //tempBTN.setBackground(myMaze.mazeTile(finalX,finalY).TopWall() ? Color.BLACK : Color.WHITE);



                //MazeGenerationPanel.add(tempBTN);

                //window.add(tempBTN);
                window2.add(tempBTN);

                JButton tempBTN2 = new JButton("");
                //tempBTN2.setBounds(x * 50, 10+75 + y * 50, 10, 40);
                tempBTN2.setBounds(xposition + x * xdistance_between_vertical_walls * scale_factor, 10+yposition + y * ydistance_between_vertical_walls * scale_factor, vertical_wall_width, vertical_wall_length * scale_factor);
                tempBTN2.addActionListener(action -> mazeButtonPressed(finalX,finalY, tempBTN2, false));
                tempBTN2.setBackground(x == 0 ? Color.BLACK : Color.WHITE);

                // change container from window to MazeGenerationPanel
                //MazeGenerationPanel.add(tempBTN2);

                //Jayden's update
                //tempBTN.setBackground(myMaze.mazeTile(finalX,finalY).LeftWall() ? Color.BLACK : Color.WHITE);


                //MazeGenerationPanel.add(tempBTN2);



                //window.add(tempBTN2);
                window2.add(tempBTN2);
            }
        }

        for(int i = 0; i < myMaze.mazeSize()[1]; i++){
            JButton tempBTN2 = new JButton("");
            //tempBTN2.setBounds(myMaze.mazeSize()[0] * 50, 10+75 + i * 50, 10, 40);
            tempBTN2.setBounds(xposition + myMaze.mazeSize()[0] * xdistance_between_vertical_walls * scale_factor, 10+yposition + i * ydistance_between_vertical_walls * scale_factor, vertical_wall_width, vertical_wall_length * scale_factor);
            int finalX = myMaze.mazeSize()[0];
            int finalY = i;
            tempBTN2.addActionListener(action -> mazeButtonPressed(finalX,finalY, tempBTN2, false));
            tempBTN2.setBackground(Color.BLACK);
            // change contianer from window to MazeGenerationPanel
            //MazeGenerationPanel.add(tempBTN2);

            //window.add(tempBTN2);
            window2.add(tempBTN2);
        }

        for(int i = 0; i < myMaze.mazeSize()[0]; i++){
            JButton tempBTN = new JButton("");
            //tempBTN.setBounds(10 + i * 50, 75 + myMaze.mazeSize()[1] * 50, 40, 10);
            tempBTN.setBounds(10+xposition + i * xdistance_between_horizontal_walls * scale_factor, yposition + myMaze.mazeSize()[1] * ydistance_between_horizontal_walls * scale_factor, horizontal_wall_length * scale_factor, horizontal_wall_width);
            int finalX = i;
            int finalY = myMaze.mazeSize()[1];
            tempBTN.addActionListener(action -> mazeButtonPressed(finalX,finalY, tempBTN, true));
            tempBTN.setBackground(Color.BLACK);
            // change container from window to MazeGenerationPanel
            //MazeGenerationPanel.add(tempBTN);

            //window.add(tempBTN);
            window2.add(tempBTN);
        }

        //window.add(MazeGenerationPanel);
        SwingUtilities.updateComponentTreeUI(window);
        //SwingUtilities.updateComponentTreeUI(MazeGenerationPanel);
    }

    /**
     * Actions that occur after the user selects a tile in the maze
     * @author Jayden
     * @param x X location of that tile
     * @param y Y location of that tile
     * @param button The button representing that tile
     * @param Top Boolean to determine whether it is at the top of the maze
     */
    public static void mazeButtonPressed(int x, int y, JButton button, boolean Top){
        boolean set = false;
        if(button.getBackground() == Color.BLACK){
            button.setBackground(Color.WHITE);
        }else{
            button.setBackground(Color.BLACK);
            set = true;
        }

        if(Top){
            if(y == 0){
                myMaze.mazeTile(x,y).setTopWall(set);
            }else if(y==mazeSize[1]){
                myMaze.mazeTile(x,y).setBottomWall(set);
            }else{
                myMaze.mazeTile(x,y).setTopWall(set);
                myMaze.mazeTile(x,y-1).setBottomWall(set);
            }
        }

        //Prints the coords of the button pressed
        System.out.println(x+", "+y);



    }




}
