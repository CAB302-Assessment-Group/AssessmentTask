package gui;


import maze.core.Maze;
import maze.core.image.ImageProcessing;
import maze.core.solver.Solver;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static gui.Frame.*;

public class Render {

    public static boolean autoSolveMaze = false;
    private static boolean shouldRenderSolution = true;

    //private static JFrame window = Frame.getInstance().window;
    private static JFrame window2 = Frame.getInstance().window2;




    //private static JFrame window2 = Frame.getInstance().window2;
    //tried to add maze generation panel, not sure how to take panel from frame class
    //private static JPanel MazeGenerationPanel = new JPanel();
    private static int[] mazeSize = Frame.getInstance().mazeSize;
    public static Maze currentMaze = Frame.getInstance().myMaze;
    /**
    
     * Draws the optimal solution
     * @author Hudson
     * @param solution the solution steps (found by calling solution.DFS(<Maze>))
     */
    public static void drawSolution(ArrayList<Integer[]> solution) {
        Util.resetSolution();

        // set states of maze object to solution
        for (int i = 0; i < solution.size(); i++) {
            Integer[] activateTileCords = solution.get(i);
            Frame.getInstance().myMaze.mazeTile(activateTileCords[0], activateTileCords[1]).setState(true);
        }

        window2.setLayout(null);

        window2.getContentPane().removeAll();
        window2.getContentPane().repaint();

        // render the solution
        renderMazeOBJ(Frame.getInstance().myMaze, !Frame.childMaze, false, shouldRenderSolution);
    }

    public static void toggleSolutionVisualisation(boolean state) {
        if (state) {
            Util.resetSolution();
            Frame.solveMyMaze();
        } else {
            window2.setLayout(null);

            window2.getContentPane().removeAll();
            window2.getContentPane().repaint();
            renderMazeOBJ(Frame.getInstance().myMaze, true);
        }

        shouldRenderSolution = state;
    }


    // overload the previous notation of the calling function as not to break it
    public static void setButtonPressed(String width, String height, String logoSize, boolean generated) {
        setButtonPressed(width, height, logoSize, generated, false);
    }

    /**
     * @author Jayden and Hudson
     * Actions that take place after the user has clicked the 'Generate Maze' button
     * @param width Width specified by the user
     * @param height Height specified by the user
     */
    public static void setButtonPressed(String width, String height, String logoSize, boolean generated, boolean autoSolve){


        String[] inputs = {width, height};
        //System.out.println(width);
        //System.out.println(height);

        
        if (!Util.validateInput(inputs)) {
            System.out.println("[ERROR] Invalid maze size...");
            PopUp errorMessage = new PopUp("[ERROR] Invalid maze size...");
            return;
        }

        //initialise(width.trim(), height.trim());

        //System.out.println(width.trim()+", "+height.trim());
        logoSize+="";

        int logoSizeInt = Util.getLogoSize(logoSize);



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

        if(generated){
            currentMaze.generateMaze(hasIm, Frame.childMaze);
        }else{
            int diffX = currentMaze.mazeSize()[0] - hasIm;
            int diffY = currentMaze.mazeSize()[1] - hasIm;
            currentMaze.setLogoTopCorner(new int[]{diffX/2,diffY/2});
        }


        renderMazeOBJ(currentMaze, generated, autoSolve);
        getInstance().myMaze = currentMaze;
    }

    public static void renderMazeOBJ(Maze myMaze, boolean generated) { renderMazeOBJ(myMaze, generated, false, false); }
    public static void renderMazeOBJ(Maze myMaze, boolean generated, boolean showSolution) { renderMazeOBJ(myMaze, generated, showSolution, false); }

    /**
     * Render the maze object in the JFrame window with correct scaling and solution if requested
     * @param myMaze
     * @param generated
     * @param showSolution
     * @param renderSolution
     * @author Hudson, Jayden, and Jack
     */
    public static void renderMazeOBJ(Maze myMaze, boolean generated, boolean showSolution, boolean renderSolution) {
        //maze generation starting
        // on frame

        window2.setLayout(null);

        window2.getContentPane().removeAll();
        window2.getContentPane().repaint();

        int xposition = 0;
        int yposition = 0;
        double scale_factor = Util.scaleFactor(myMaze.largestDimension(), screenHeight);

        int wallLength = 40;
        int wallWidth = 10;

        int between_walls = wallLength+wallWidth;
        //Removes bug where logo shows twice
        window2.getContentPane().removeAll();

        if (childMaze) myMaze.mazeTile(myMaze.mazeSize()[0] - 3, myMaze.mazeSize()[1] - 1).setRightWall(false);


        if(Frame.childMaze){
            myMaze.mazeTile(0,0).setTopWall(true);
            myMaze.mazeTile(myMaze.mazeSize()[0] - 1,myMaze.mazeSize()[1] - 1).setBottomWall(true);
        }
        for (int x = 0; x < myMaze.mazeSize()[0]; x++) {
            for (int y = 0; y < myMaze.mazeSize()[1]; y++) {
                // border styling
                JButton tempBTN = new JButton("");
                int [] bounds = Util.generateBounds(scale_factor,xposition,x,between_walls,yposition,y,wallLength,wallWidth);
                tempBTN.setBounds(bounds[0],bounds[1],bounds[2],bounds[3]);
                //tempBTN.setBounds((int) Math.floor(10 * scale_factor + xposition + x * (between_walls) * (scale_factor)), (int) Math.floor(yposition + y * between_walls * scale_factor), (int) Math.floor(wallLength * scale_factor), (int) Math.floor(wallWidth * scale_factor));
                int finalX = x;
                int finalY = y;
                tempBTN.addActionListener(action -> mazeButtonPressed(finalX,finalY, tempBTN, true));
                if(!generated && y==0){
                    myMaze.mazeTile(finalX,finalY).setTopWall(true);
                }
                JLabel label=new JLabel();
                if(Frame.Startlogo !=null){
                    ImageIcon icon=new ImageIcon(Frame.Startlogo);
                    label.setIcon(icon);

                    Dimension imageSize = new Dimension(icon.getIconWidth(),icon.getIconHeight());
                    label.setPreferredSize(imageSize);
                    label.revalidate();
                    label.repaint();
                    label.setLocation(0,0);
                    window2.add(label);
                }
                tempBTN.setBorderPainted(false);
                window2.add(tempBTN);

                JButton tempBTN2 = new JButton("");
                bounds = Util.generateBounds(xposition,x,between_walls,scale_factor,yposition,y,wallWidth,wallLength);
                tempBTN2.setBounds(bounds[0],bounds[1],bounds[2],bounds[3]);
                tempBTN2.addActionListener(action -> mazeButtonPressed(finalX,finalY, tempBTN2, false));

                if(!generated && x==0){
                    myMaze.mazeTile(finalX,finalY).setLeftWall(true);
                }



                // Render wall colours
                // left-right

                // up-down

                tempBTN.setBackground(myMaze.mazeTile(finalX,finalY).TopWall() ? Color.BLACK : Color.WHITE);

                //MazeGenerationPanel.add(tempBTN2);

                tempBTN2.setBackground(myMaze.mazeTile(finalX, finalY).LeftWall() ? Color.BLACK : Color.WHITE);

                boolean tileState = myMaze.mazeTile(finalX, finalY).GetState();
                if (tileState && renderSolution) {
                    JButton solveStep = new JButton("");
                    bounds = Util.generateBounds(x,wallLength,wallWidth,y,scale_factor);
                    solveStep.setBounds(bounds[0],bounds[1],bounds[2],bounds[3]);
                    solveStep.setBackground(Color.RED);

                    solveStep.setBorderPainted(false);
                    window2.add(solveStep);
                }


                //window.add(tempBTN2);
                tempBTN2.setBorderPainted(false);
                window2.add(tempBTN2);
            }
        }

        for(int i = 0; i < myMaze.mazeSize()[1]; i++){
            JButton tempBTN2 = new JButton("");
            int[] bounds = Util.generateBoundsLoop(xposition, myMaze.mazeSize()[0], between_walls, scale_factor, yposition,i,wallWidth,wallWidth);
            tempBTN2.setBounds(bounds[0],bounds[1],bounds[2],bounds[3]);
            tempBTN2.setBounds((int) Math.floor(xposition + myMaze.mazeSize()[0] * between_walls * scale_factor), (int) Math.floor(10 * scale_factor+yposition + i * between_walls * scale_factor), (int) Math.floor(wallWidth * scale_factor), (int) Math.floor(wallLength * scale_factor));
            int finalX = myMaze.mazeSize()[0];
            int finalY = i;
            if(!generated){
                myMaze.mazeTile(finalX - 1,finalY).setRightWall(true);
            }
            tempBTN2.addActionListener(action -> mazeButtonPressed(finalX,finalY, tempBTN2, false));
            tempBTN2.setBackground(myMaze.mazeTile(finalX - 1,finalY).RightWall() ? Color.BLACK : Color.WHITE);
            // change contianer from window to MazeGenerationPanel
            //MazeGenerationPanel.add(tempBTN2);

            //window.add(tempBTN2);
            tempBTN2.setBorderPainted(false);
            window2.add(tempBTN2);
        }

        for(int i = 0; i < myMaze.mazeSize()[0]; i++){
            JButton tempBTN = new JButton("");
            int[] bounds1 = Util.generateBounds(scale_factor,xposition,i,between_walls,yposition,myMaze.mazeSize()[1],wallLength,wallWidth);
            tempBTN.setBounds(bounds1[0],bounds1[1],bounds1[2],bounds1[3]);
            //tempBTN.setBounds((int) Math.floor(10 *scale_factor+xposition + i * between_walls * scale_factor), (int) Math.floor(yposition + myMaze.mazeSize()[1] * between_walls * scale_factor),(int) Math.floor( wallLength * scale_factor),(int) Math.floor( wallWidth * scale_factor));

            int finalX = i;
            int finalY = myMaze.mazeSize()[1];
            if(!generated){
                myMaze.mazeTile(finalX,finalY - 1).setBottomWall(true);
            }
            tempBTN.addActionListener(action -> mazeButtonPressed(finalX,finalY, tempBTN, true));
            tempBTN.setBackground(myMaze.mazeTile(finalX,finalY - 1).BottomWall() ? Color.BLACK : Color.WHITE);
            // change container from window to MazeGenerationPanel
            //MazeGenerationPanel.add(tempBTN);

            //window.add(tempBTN);
            tempBTN.setBorderPainted(false);
            window2.add(tempBTN);
        }

        int[] start = new int[]{0,0};
        int[] end = new int[]{mazeSize[0]-1,mazeSize[1]-1};

        try {
            
            myMaze.setStart(start);
            myMaze.setEnd(end);
        }catch (Exception e){

        }
        //window.add(MazeGenerationPanel);
        SwingUtilities.updateComponentTreeUI(window);
        //SwingUtilities.updateComponentTreeUI(MazeGenerationPanel);
        Frame.getInstance().myMaze = myMaze;

        if (showSolution) {
            Solver tmpSolver = new Solver();
            tmpSolver.DFS(myMaze, new Integer[] {0, 0});
            ArrayList<Integer[]> solution = tmpSolver.Solution();
            Render.drawSolution(solution);
        }

        window2.setVisible(false);

        
        if(childMaze){
            if(Startlogo != null){
                System.out.println("Found startim");
                try{
                    Frame.getInstance().myMaze.mazeTile(0,0).setImage(ImageProcessing.toByteArray(Startlogo));
                    BufferedImage bi = ImageProcessing.fromByteArray(myMaze.mazeTile(0,0).getImage());
                    int bounds[] = Util.generateBounds(xposition,between_walls,scale_factor,yposition);
                    window2.add(ImageProcessing.drawLogo(bounds,bi));
                    window2.repaint();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
	 	    if(Endlogo != null && childMaze){
                System.out.println("Found endim");
                try{
                    Frame.getInstance().myMaze.mazeTile(mazeSize[0]-1,mazeSize[1]-1 ).setImage(ImageProcessing.toByteArray(Endlogo));
                    int[] bounds = Util.generateBounds(xposition,between_walls,scale_factor,yposition,mazeSize[0],mazeSize[1]);
                    window2.add(ImageProcessing.drawLogo(bounds, getInstance().myMaze,mazeSize[0],mazeSize[1]));
                }catch(Exception e){
                    System.out.println(e.getMessage());

                }
            }
        }


        window2.pack();
        // set the window size
        int [] size = Util.windowScaledSize(getInstance().myMaze.largestDimension(), screenHeight);
        window2.setSize(size[0],size[1]);


        window2.setVisible(true);
        SwingUtilities.updateComponentTreeUI(window2);
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
                currentMaze.mazeTile(x-1,y).setRightWall(set);
            }
        }

        //Prints the coords of the button pressed
        System.out.println(x+", "+y);


        Frame.getInstance().myMaze = currentMaze;

        if (autoSolveMaze) Frame.solveMyMaze();

        Frame.SetMetrics(Frame.getInstance().MetricsWindow);
    }




}
