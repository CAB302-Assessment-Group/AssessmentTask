import maze.core.image.ImageProcessing;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.*;

import javax.swing.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ImageProcessingTest {
    public String location;
    @BeforeEach
    void setUp() {
         location = "ExampleImages/StandardLogo.PNG";
    }

    @Test
    void exportImage() {

    }

    @Test
    void getLogo() {
        ImageIcon icon = new ImageIcon(location);
        JLabel label = new JLabel(icon);
        label.setSize(50,50);
        Path path = Paths.get(location);
        label.setLocation(0,0);
        System.out.println(path);
        JFrame frame = new JFrame();
        frame.setSize(200,200);
        frame.add(label);
        frame.setLocation(0,0);
        frame.setVisible(true);
        frame.setVisible(false);
    }
}