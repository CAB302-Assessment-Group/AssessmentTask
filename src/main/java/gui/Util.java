package gui;

import maze.core.Database;
import maze.core.solver.Solver;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static gui.Frame.logoCellSize;

public class Util {
    /**
     * Generates scale factor for sizing components within JFrame
     * @param largerDim Largest dimension of the maze (width or height)
     * @return Scale factor as a double that can be used for making adjustments to the components
     * @author Jayden and Jack
     */
    public static double scaleFactor(int largerDim, int windowHeight){
        double scale_factor = 25.0/largerDim;
        double resolution_scale = 1.0;
        if(largerDim<=10){
            scale_factor = 4.0/largerDim/resolution_scale;
        } else if (largerDim>10 && largerDim<15) {
            scale_factor = 7.5/largerDim/resolution_scale;
        } else if (largerDim>=15 && largerDim<30){
            scale_factor = 10.0/largerDim/resolution_scale;
        } else if (largerDim>=30 && largerDim<50){
            scale_factor = 15.0/largerDim/resolution_scale;
        } else if (largerDim>=50 && largerDim<60) {
            scale_factor = 20.0 / largerDim/resolution_scale;
        } else if (largerDim>=60 && largerDim<75) {
            scale_factor = 25.0 / largerDim/resolution_scale;
        } else if (largerDim>=75 && largerDim<85) {
            scale_factor = 26.0 / largerDim/resolution_scale;
        } else if (largerDim>=85){
            scale_factor = 27.0/largerDim/resolution_scale;
        }
        return scale_factor;

    }

    /**
     *
     * @param largerDim
     * @param windowHeight
     * @return
     */
    public static int[] windowScaledSize(int largerDim, int windowHeight){
        int[] size = new int[2];
        double scaling =1;
        if(windowHeight<1050){
            scaling=1.1;
        }
        if(largerDim<=10){
            int dimension = (int) (300/scaling);
            size = new int[]{dimension, dimension};
        } else if (largerDim>10 && largerDim<15) {
            int dimension = (int) (500/scaling);
            size = new int[]{dimension, dimension};
        } else if (largerDim>=15 && largerDim<30){
            int dimension = (int) (575/scaling);
            size = new int[]{dimension, dimension};
        } else if (largerDim>=30 && largerDim<50){
            int dimension = (int) (800/scaling);
            size = new int[]{dimension, dimension};
        } else if (largerDim>=50 && largerDim<60) {
            int dimension = (int) (1050/scaling);
            size = new int[]{dimension, dimension};
        } else if (largerDim>=60 && largerDim<75) {
            int dimension = (int) (1300/scaling);
            size = new int[]{dimension, dimension};
        } else if (largerDim>=75 && largerDim<85) {
            int dimension = (int) (1500/scaling);
            size = new int[]{dimension, dimension};
        } else if (largerDim>=85){
            int dimension = (int) (1600/scaling);
            size = new int[]{dimension, dimension};
        }
        return size;
    }

    /**
     * Updates current maze object based on what is currently within the text boxes
     * @return True if maze object was succefully updated with no errors,
     * false if crucial things are missing or errors occur
     * @author Jack
     */
    public static boolean UpdateMazeFromInput(String author, String name){
        boolean updating = true;
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        if(author=="" || name == ""){
            updating = false;
        }
        if(Frame.getInstance().myMaze.getAuthor()!=null || Frame.getInstance().myMaze.getAuthor()!=author){
            Frame.getInstance().myMaze.setAuthor(author);
        }

        Frame.getInstance().myMaze.setMazeName(name);
        if(Frame.getInstance().myMaze.getDateCreated()!=null){
            Frame.getInstance().myMaze.setDateCreated(timeStamp);
        }
        Frame.getInstance().myMaze.setDateEdited(timeStamp);
        Frame.getInstance().myMaze.SetLastEditor(author);
        Database.exportMaze(Frame.getInstance().myMaze);
        return updating;
    }

    /**
     * Sets up fileChooser to receive a location to store a file locally
     * @param exportFile The JFileChooser the user is interacting with
     * @return Location the
     */
    public static String getExportLocation(JFileChooser exportFile){
        // From https://stackoverflow.com/questions/10621687/how-to-get-full-path-directory-from-file-chooser
        exportFile.setCurrentDirectory(new java.io.File("."));
        exportFile.setDialogTitle("Export Maze");
        exportFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        exportFile.setAcceptAllFileFilterUsed(false);


        String fileLocation ="";
        if (exportFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            fileLocation = exportFile.getSelectedFile().getAbsolutePath();
        } else {
            System.out.println("No Selection ");
            PopUp popUp = new PopUp("Please select a location");
        }
        return fileLocation;
    }
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
     * Resets solution to no maze walls except on the outside
     * @author Hudson
     */
    public static void resetSolution() {
        // reset states of maze object
        for (int x = 0; x < Frame.getInstance().myMaze.mazeSize()[0]; x++) {
            for (int y = 0; y < Frame.getInstance().myMaze.mazeSize()[1]; y++) {
                Frame.getInstance().myMaze.mazeTile(x, y).setState(false);
            }
        }
    }

    /**
     * Takes in a string input of what  the user has inputted as a logoSize and outputs an integer value
     * @param logoSize A string representation of the desired logo size
     * @return An integer representation of the string which has been parsed
     * @author Jayden, Hudson and Jack
     */
    public static int getLogoSize(String logoSize){
        int logoSizeInt = -1;
        try{
            if(logoSize.trim()==""){
                logoSize = "0";
            }
            logoSizeInt = Integer.parseInt(logoSize);
        }catch(Exception e){
            System.out.println("[ERROR] Invalid logo size...");
            PopUp errorMessage = new PopUp("[ERROR] Invalid logo size...");
        }
        return logoSizeInt;
    }

    /**
     * Generate a bounding area for a JButton to enable appropriate scaling based on a variety of inputs.
     * Overloaded to compensate
     * @author Jayden and Jack
     */
    public static int[] generateBounds(int xposition, int x, int between_walls, double scale_factor, int yposition, int y, int wallWidth, int wallLength){
        int[] bounds = {(int) Math.floor(xposition + x * between_walls * scale_factor), (int) Math.floor(10 * scale_factor+yposition + y * between_walls * scale_factor), (int) Math.floor(wallWidth * scale_factor), (int) Math.floor(wallLength * scale_factor)};
        return bounds;
    }

    public static int[] generateBounds(int x, int wallLength, int wallWidth, int y, double scale_factor){
        int[] bounds={(int) Math.floor(((x * (wallLength + wallWidth)) + (wallLength/4))*scale_factor), (int) Math.floor(((y * (wallLength + wallWidth)) + (wallLength/4))*scale_factor), (int) Math.floor(40 * scale_factor), (int) Math.floor(40 * scale_factor)};
        return bounds;
    }

    public static int[] generateBounds(double scale_factor, int xposition, int i, int between_walls, int yposition, int y, int wallLength, int wallWidth){
        int[] bounds = {(int) Math.floor(10 *scale_factor+xposition + i * between_walls * scale_factor), (int) Math.floor(yposition + y * between_walls * scale_factor),(int) Math.floor( wallLength * scale_factor),(int) Math.floor( wallWidth * scale_factor)};
        return bounds;
    }

    public static int[] generateBoundsLoop(int xposition,int x, int between_walls, double scale_factor, int yposition, int i, int wallWidth, int wallLength){
        int[] bounds= { (int) Math.floor(xposition + x * between_walls * scale_factor), (int) Math.floor(10 * scale_factor+yposition + i * between_walls * scale_factor), (int) Math.floor(wallWidth * scale_factor), (int) Math.floor(wallLength * scale_factor)};
        return bounds;
    }
    public static int[] generateBounds(int xposition, int between_walls, double scale_factor, int yposition){
        int[] bounds= { (int) Math.floor(xposition + 0 * between_walls * scale_factor), (int) Math.floor(10 * scale_factor+yposition + 0 * between_walls * scale_factor), (int) Math.floor(2*between_walls*scale_factor), (int) Math.floor(2*between_walls*scale_factor)};
        return bounds;
    }
    public static int[] generateBounds(int xposition,int topCorner, int between_walls, double scale_factor, int yposition){
        int[] bounds= { (int) Math.floor(xposition + topCorner * between_walls * scale_factor), (int) Math.floor(10 * scale_factor+yposition + (Frame.getInstance().myMaze.getLogoTopCorner()[1]) * between_walls * scale_factor), (int) Math.floor(Integer.parseInt(logoCellSize)*between_walls*scale_factor), (int) Math.floor(Integer.parseInt(logoCellSize)*between_walls*scale_factor)};
        return bounds;
    }
    public static int[] generateBounds(int xposition, int between_walls, double scale_factor, int yposition, int mazeSize1, int mazeSize2){
        int[] bounds= { (int) Math.floor(xposition + (mazeSize1-2) * between_walls * scale_factor), (int) Math.floor(10 * scale_factor+yposition + (mazeSize2-2) * between_walls * scale_factor), (int) Math.floor(2*between_walls*scale_factor), (int) Math.floor(2*between_walls*scale_factor)};
        return bounds;
    }




}
