package src.tests.java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import src.main.java.maze.core.Maze;
import src.main.java.maze.core.solver.Solver;

public class TestSolver {
    Maze testMaze;
    Solver mySolver;

    /**
     * @author Hudson
     */
    @BeforeEach
    public void ConstructMaze(){
        //TODO Add in call to automatically generate or manually make one so we can test solvability
        int[] size = {10,10};
        testMaze = new Maze(size, false);
        mySolver = new Solver();
    }
    /**
     * Test 1: Test to see if the neighbours functions returns values adjacent to the cell
     * @author Hudson
     */
    @Test
    public void TestNeighbours() {
        // make sure that the function returns a value
        assertNotNull(mySolver.neighbours(0, 0, testMaze));

        // test that the top left tile only has neighbours to the bottom and right
        boolean[] tile_0_0N = {true, false, false, true};
        assertArrayEquals(tile_0_0N, mySolver.neighbours(0, 0, testMaze));

        // set tile 3, 4's right wall to active
        testMaze.mazeTile(4, 4).setLeftWall(true);

        boolean[] tile_4_4N = {true, true, false, true};
        assertArrayEquals(tile_4_4N, mySolver.neighbours(4, 4, testMaze));
    }
    /**
     * Test 2: Test to see if maze solver can solve the maze faster than a human (i.e. 60 seconds)
     * #ID 4
     * Stole timing stuff from https://stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java
     * @author Jack
     */
    @Test
    public void TestSolverTime(){
        long startTime = System.nanoTime();
        //mySolver.solveMaze(testMaze);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
        assertTrue(duration<60000,"Maze solver took longer then 60 seconds");
    }
}
