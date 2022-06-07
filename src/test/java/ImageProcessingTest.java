import gui.Frame;
import maze.core.image.ImageProcessing;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ImageProcessingTest {
    public String location;
    @BeforeEach
    void setUp() {
         location = "D:\\Documents\\2022\\CAB302\\Assignment\\AssessmentTask\\src\\test\\java\\ExampleImages\\StandardLogo.PNG";
    }

    @Test
    void exportImage() {

    }

    @Test
    void getLogo() throws IOException {
        JLabel label = new JLabel();
        File file = new File(location);
        BufferedImage img = ImageIO.read(file);
        JFrame frame = new JFrame();
        frame.setSize(1000,1000);
        ImageIcon icon=new ImageIcon(img);
        label.setIcon(icon);

        Dimension size = label.getPreferredSize();
        label.setBounds(50, 30, size.width, size.height);
        Container c = frame.getContentPane(); //Gets the content layer
        c.add(label);
        frame.setVisible(true);
    }
}