package gui;


import maze.core.Maze;

import javax.swing.*;
import java.awt.*;

import static gui.Frame.window;

public class Render {
    //private static JFrame window = Frame.getInstance().window;
    private static JFrame window2 = Frame.getInstance().window2;



    //private static JFrame window2 = Frame.getInstance().window2;
    //tried to add maze generation panel, not sure how to take panel from frame class
    //private static JPanel MazeGenerationPanel = new JPanel();
    private static int[] mazeSize = Frame.getInstance().mazeSize;
    public static Maze currentMaze = Frame.getInstance().myMaze;
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
    public static void setButtonPressed(String width, String height, String logoSize, boolean generated){


        String[] inputs = {width, height};
        //System.out.println(width);
        //System.out.println(height);

        if (!validateInput(inputs)) {
            System.out.println("[ERROR] Invalid maze size...");
            return;
        }

        //initialise(width.trim(), height.trim());

        //System.out.println(width.trim()+", "+height.trim());
        logoSize+="";
        int logoSizeInt = -1;
        try{
            System.out.println(logoSize.trim()=="");
            if(logoSize.trim()==""){
                logoSize = "0";
            }
            logoSizeInt = Integer.parseInt(logoSize);
        }catch(Exception e){
            System.out.println("[ERROR] Invalid logo size...");
            return;
        }

        mazeSize = new int[]{Integer.parseInt(width.trim()),Integer.parseInt(height.trim())};
        currentMaze = new Maze(mazeSize); //need to pass child maze param

        if(logoSizeInt >= Integer.parseInt(width.trim())-1 || logoSizeInt >= Integer.parseInt(height.trim())-1){
            System.out.println("[ERROR] Logo size too large...");
            return;
        }
        int hasIm = 0;
        if(logoSizeInt > 0){
            hasIm = logoSizeInt;
        }

        if(generated)
            currentMaze.generateMaze(hasIm);

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

        for (int x = 0; x < currentMaze.mazeSize()[0]; x++) {
            for (int y = 0; y < currentMaze.mazeSize()[1]; y++) {
                // border styling
                JButton tempBTN = new JButton("");
                //tempBTN.setBounds(10 + x * 50, 75 + y * 50, 40, 10);
                tempBTN.setBounds(10+xposition + x * (xdistance_between_horizontal_walls) * (scale_factor), yposition + y * ydistance_between_horizontal_walls * scale_factor, horizontal_wall_length * scale_factor, horizontal_wall_width);
                int finalX = x;
                int finalY = y;
                tempBTN.addActionListener(action -> mazeButtonPressed(finalX,finalY, tempBTN, true));
                tempBTN.setBackground(y == 0 ? Color.BLACK : Color.WHITE);
                if(y==0){
                    currentMaze.mazeTile(finalX,finalY).setTopWall(true);
                }
                // change container from window to MazeGenerationPanel
                //MazeGenerationPanel.add(tempBTN);

                //Jayden's update
                tempBTN.setBackground(currentMaze.mazeTile(finalX,finalY).TopWall() ? Color.BLACK : Color.WHITE);
                //System.out.println(finalX + ","+finalY + myMaze.mazeTile(finalX,finalY).TopWall());


                //MazeGenerationPanel.add(tempBTN);

                //window.add(tempBTN);
                window2.add(tempBTN);

                JButton tempBTN2 = new JButton("");
                //tempBTN2.setBounds(x * 50, 10+75 + y * 50, 10, 40);
                tempBTN2.setBounds(xposition + x * xdistance_between_vertical_walls * scale_factor, 10+yposition + y * ydistance_between_vertical_walls * scale_factor, vertical_wall_width, vertical_wall_length * scale_factor);
                tempBTN2.addActionListener(action -> mazeButtonPressed(finalX,finalY, tempBTN2, false));
                tempBTN2.setBackground(x == 0 ? Color.BLACK : Color.WHITE);
                if(x==0){
                    currentMaze.mazeTile(finalX,finalY).setLeftWall(true);
                }
                // change container from window to MazeGenerationPanel
                //MazeGenerationPanel.add(tempBTN2);

                //Jayden's update
                tempBTN2.setBackground(currentMaze.mazeTile(finalX,finalY).LeftWall() ? Color.BLACK : Color.WHITE);


                //MazeGenerationPanel.add(tempBTN2);



                //window.add(tempBTN2);
                window2.add(tempBTN2);
            }
        }

        for(int i = 0; i < currentMaze.mazeSize()[1]; i++){
            JButton tempBTN2 = new JButton("");
            //tempBTN2.setBounds(myMaze.mazeSize()[0] * 50, 10+75 + i * 50, 10, 40);
            tempBTN2.setBounds(xposition + currentMaze.mazeSize()[0] * xdistance_between_vertical_walls * scale_factor, 10+yposition + i * ydistance_between_vertical_walls * scale_factor, vertical_wall_width, vertical_wall_length * scale_factor);
            int finalX = currentMaze.mazeSize()[0];
            int finalY = i;
            tempBTN2.addActionListener(action -> mazeButtonPressed(finalX,finalY, tempBTN2, false));
            tempBTN2.setBackground(Color.BLACK);
            // change contianer from window to MazeGenerationPanel
            //MazeGenerationPanel.add(tempBTN2);

            //window.add(tempBTN2);
            window2.add(tempBTN2);
        }

        for(int i = 0; i < currentMaze.mazeSize()[0]; i++){
            JButton tempBTN = new JButton("");
            //tempBTN.setBounds(10 + i * 50, 75 + myMaze.mazeSize()[1] * 50, 40, 10);
            tempBTN.setBounds(10+xposition + i * xdistance_between_horizontal_walls * scale_factor, yposition + currentMaze.mazeSize()[1] * ydistance_between_horizontal_walls * scale_factor, horizontal_wall_length * scale_factor, horizontal_wall_width);
            int finalX = i;
            int finalY = currentMaze.mazeSize()[1];
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
                currentMaze.mazeTile(x,y).setTopWall(set);
            }else if(y==mazeSize[1]){
                currentMaze.mazeTile(x,y-1).setBottomWall(set);
            }else{
                currentMaze.mazeTile(x,y).setTopWall(set);
                currentMaze.mazeTile(x,y-1).setBottomWall(set);
            }
        }else{
            if(x == 0){
                currentMaze.mazeTile(x,y).setLeftWall(set);
            }else if(x==mazeSize[0]){
                currentMaze.mazeTile(x-1,y).setRightWall(set);
            }else{
                currentMaze.mazeTile(x,y).setLeftWall(set);
                currentMaze.mazeTile(x,y-1).setRightWall(set);
            }
        }

        //Prints the coords of the button pressed
        System.out.println(x+", "+y);



    }




}
