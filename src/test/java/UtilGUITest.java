import gui.Render;
import gui.Util;
import maze.core.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class UtilGUITest {

    Maze testMaze;
    Maze testMaze1;
    Maze testMaze2;
    @BeforeEach
    public void ConstructMaze(){ //needs a param for child maze
        int[] size = {10,10};
        int[] size1= {50,50};
        int[] size2 = {100,100};
        testMaze = new Maze(size);
        testMaze1 = new Maze(size1);
        testMaze2 = new Maze(size2);
    }
    /**
     * Test 1: Validate that scaleFactor returns different scale factors for different inputs
     * that are within reason
     * @author Jack
     */
    @Test
    void TestScaleFactor() {
        int windowHeight1 = 1440, windowHeight2=1024;

        double output1 = Util.scaleFactor(testMaze.largestDimension(),windowHeight1);
        double output2 = Util.scaleFactor(testMaze2.largestDimension(),windowHeight1);
        assertTrue(output1>output2,"Larger maze has bigger scaling");

        output1 = Util.scaleFactor(testMaze2.largestDimension(),windowHeight2);
        output2 = Util.scaleFactor(testMaze1.largestDimension(),windowHeight2);
        assertTrue(output1<output2,"Larger maze has bigger scaling");
    }

    /**
     * Test 2: Validate that scaling is done properly depending on different maze sizes and resolutions
     * @author Jack
     */
    @Test
    void TestWindowScaleSize(){
        int windowHeight1 = 1440, windowHeight2=1024;
        //Same window height different mazes
        int[] output1 = Util.windowScaledSize(testMaze.largestDimension(),windowHeight1);
        int[] output2 = Util.windowScaledSize(testMaze2.largestDimension(),windowHeight1);
        assertTrue(output2[0]>output1[0],"Larger maze has bigger scaling");

        //Same maze different window heights
        output1 = Util.windowScaledSize(testMaze.largestDimension(),windowHeight1);
        output2 = Util.windowScaledSize(testMaze.largestDimension(),windowHeight2);
        assertTrue(output1[0]>output2[0],"Larger screensize has bigger scaling");
    }


    @Test
    void updateCurrentMaze() {

    }

    /**
     * Test 3:
     * Validates the validateInput is working for frame class
     * @author Jack
     */
    @Test
    void TestValidateInput(){
        //Valid Case 1
        String[] inputs = {"1", "1"};
        assertTrue(Util.validateInput(inputs));

        //Valid Case 2
        inputs[0] ="1";inputs[1]="100";
        assertTrue(Util.validateInput(inputs));

        //Valid Case 3
        inputs[0] ="100";inputs[1]="1";
        assertTrue(Util.validateInput(inputs));

        //Valid Case 4
        inputs[0] ="100";inputs[1]="100";
        assertTrue(Util.validateInput(inputs));

        //Invalid Case 1
        inputs[0] ="1 2";inputs[1]="1 0 0";
        assertFalse(Util.validateInput(inputs), "Not valid inputs " + inputs);

        //Invalid Case 2
        inputs[0] ="0";inputs[1]="1";
        assertFalse(Util.validateInput(inputs), "Under limit for " + inputs);

        //Invalid Case 4
        inputs[0] ="-1";inputs[1]="0";
        assertFalse(Util.validateInput(inputs), "Under limit for " + inputs);

        //Invalid Case 5
        inputs[0] ="1F";inputs[1]="one";
        assertFalse(Util.validateInput(inputs),"Not valid inputs " + inputs);

    }
}