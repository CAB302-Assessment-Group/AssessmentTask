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

    /**
     * Test 4
     * Validate that generateBounds works with original code from Jayden
     * @author Jack
     */
    @Test
    void TestGenerateBounds(){
        int xposition =4,x=2,yposition=3,y=2,wallWidth=10,wallLength=40,between_walls=wallLength+wallWidth;
        double scale_factor = Util.scaleFactor(testMaze.largestDimension(),1440);

        //Refactored method 1
        int[] bounds1 = Util.generateBounds(xposition,x,between_walls,scale_factor,yposition,y,wallWidth,wallLength);
        //Original code 1
        int[] expected1 = {(int) Math.floor(xposition + x * between_walls * scale_factor), (int) Math.floor(10 * scale_factor+yposition + y * between_walls * scale_factor), (int) Math.floor(wallWidth * scale_factor), (int) Math.floor(wallLength * scale_factor)};
        assertEquals(expected1[0],bounds1[0]);
        assertEquals(expected1[1],bounds1[1]);

        int[] bounds2 = Util.generateBounds(x,wallLength,wallWidth,y,scale_factor);
        int[] expected2 = {(int) Math.floor(((x * (wallLength + wallWidth)) + (wallLength/4))*scale_factor), (int) Math.floor(((y * (wallLength + wallWidth)) + (wallLength/4))*scale_factor), (int) Math.floor(40 * scale_factor), (int) Math.floor(40 * scale_factor)};
        assertEquals(expected2[0],bounds2[0]);
        assertEquals(expected2[1],bounds2[1]);

        for(int i =0;i<testMaze.mazeSize()[1];i++){
            int[] bounds3 = Util.generateBoundsLoop(xposition, testMaze.mazeSize()[0], between_walls, scale_factor, yposition,i,wallLength,wallLength);
            int[] expected3 = {(int) Math.floor(xposition + testMaze.mazeSize()[0] * between_walls * scale_factor), (int) Math.floor(10 * scale_factor+yposition + i * between_walls * scale_factor), (int) Math.floor(wallWidth * scale_factor), (int) Math.floor(wallLength * scale_factor)};
            assertEquals(expected3[0],bounds3[0]);
            assertEquals(expected3[1],bounds3[1]);
        }

        for(int i =0;i<testMaze.mazeSize()[0];i++){
            int[] bounds4 = Util.generateBounds(scale_factor,xposition,i,between_walls,yposition,testMaze.mazeSize()[1],wallWidth,wallLength);
            int[] expected4 = {(int) Math.floor(10 *scale_factor+xposition + i * between_walls * scale_factor), (int) Math.floor(yposition + testMaze.mazeSize()[1] * between_walls * scale_factor),(int) Math.floor( wallLength * scale_factor),(int) Math.floor( wallWidth * scale_factor)};
            assertEquals(expected4[0],bounds4[0]);
            assertEquals(expected4[1],bounds4[1]);
        }
    }
}