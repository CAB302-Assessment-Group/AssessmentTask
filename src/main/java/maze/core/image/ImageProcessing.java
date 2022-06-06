package maze.core.image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessing {
    /**
     * Obtained from https://stackoverflow.com/questions/30335787/take-snapshot-of-full-jframe-and-jframe-only
     * Takes a picture output of the JFrame and converts to png format
     * @param mazeBox the JFrame to screenshot
     * @author Jack
     * @throws IOException
     *
     */
    public static void ExportImage(JFrame mazeBox, String location, String name) throws IOException {
        //TODO
        //Save the maze to database and make sure all fields are matching object
        BufferedImage img = new BufferedImage(mazeBox.getWidth(), mazeBox.getHeight(), BufferedImage.TYPE_INT_RGB);
        mazeBox.paint(img.getGraphics());
        String path = location+"/"+name+".png";
        File outputfile = new File(path);
        ImageIO.write(img, "png", outputfile);
    }

    public static JLabel GetLogo(String location){
        JLabel logo = new JLabel(new ImageIcon(location));
        return logo;
    }
}
