package gui;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class Util {
    /**
     * Generates scale factor for sizing components within JFrame
     * @param largerDim Largest dimension of the maze (width or height)
     * @return Scale factor as a double that can be used for making adjustments to the components
     * @author Jayden and Jack
     */
    public static double scaleFactor(int largerDim, int resolution_scale){
        double scale_factor = 25.0/largerDim;
        if(largerDim<=10){
            scale_factor = 5.0/largerDim/resolution_scale;
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
    public static int[] windowScaledSize(int largerDim, int resolution_scale){

    }

    /**
     * Updates current maze object based on what is currently within the text boxes
     * @return True if maze object was succefully updated with no errors,
     * false if crucial things are missing or errors occur
     * @author Jack
     */
    public static boolean updateCurrentMaze(){
        return false;
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

}
