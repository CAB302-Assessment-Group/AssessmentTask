package maze.core.image;

import gui.Frame;
import gui.Util;
import maze.core.Maze;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static gui.Frame.Centerlogo;
import static gui.Frame.logoCellSize;

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
        BufferedImage img = new BufferedImage(mazeBox.getWidth(), mazeBox.getHeight(), BufferedImage.TYPE_INT_RGB);
        mazeBox.paint(img.getGraphics());
        String path = location+"/"+name+".png";
        File outputfile = new File(path);
        ImageIO.write(img, "png", outputfile);
    }

    public static BufferedImage GetLogo(File file) throws IOException {
        System.out.println(file.toString());
        BufferedImage image = ImageIO.read(file);
        return image;
    }

    /**
     * Obtained from https://mkyong.com/java/how-to-convert-bufferedimage-to-byte-in-java/
     * @param bi
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(BufferedImage bi) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "png", baos);
        byte[] bytes = baos.toByteArray();
        return bytes;

    }

    public static BufferedImage fromByteArray(byte[] data) throws Exception {

        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        BufferedImage bImage2 = ImageIO.read(bis);

        return bImage2;
    }

    /**
     * Resizes image to fit box
     * @param originalImage Original image before scaling
     * @param targetWidth new width
     * @param targetHeight new height
     * @return new image rescaled to targetWidth and targetHeight dimensions
     * @throws IOException
     * @author Jack
     * Based off code from https://www.baeldung.com/java-resize-image
     */
    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    /**
     * Draws logo image onto a JLabel
     * @param bounds Bounds of the box to draw
     * @param myMaze Maze to be drawn upon
     * @return
     * @throws Exception
     * @author Jayden and Jack
     */
    public static JLabel drawCenterLogo(int[] bounds, Maze myMaze) throws Exception {
        gui.Frame.getInstance().myMaze.mazeTile(gui.Frame.getInstance().myMaze.getLogoTopCorner()[0], gui.Frame.getInstance().myMaze.getLogoTopCorner()[1]).setImage(ImageProcessing.toByteArray(Centerlogo));
        BufferedImage bi = ImageProcessing.fromByteArray(myMaze.mazeTile(gui.Frame.getInstance().myMaze.getLogoTopCorner()[0], gui.Frame.getInstance().myMaze.getLogoTopCorner()[1]).getImage());

        BufferedImage image = ImageProcessing.resizeImage(bi,bounds[2],bounds[3]);
        ImageIcon imageIcon = new ImageIcon(image);

        JLabel centreim = new JLabel(imageIcon);

        centreim.setBounds(bounds[0],bounds[1],bounds[2],bounds[3]);
        centreim.setVisible(true);
        return centreim;
    }

}
