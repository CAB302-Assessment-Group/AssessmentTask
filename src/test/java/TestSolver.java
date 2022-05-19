package src.test.java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import src.main.java.exceptions.MazeException;
import src.main.java.maze.core.Maze;
import src.main.java.maze.core.solver.Solver;

import java.util.ArrayList;

public class TestSolver {
    Maze testMaze;
    Solver mySolver;

    /**
     * @author Hudson
     */
    public final int[] STARTLOC = {0,0};
    public final int[] ENDLOC= {9,9};
    public final Integer[] START = {0,0};
    public final Integer[] END= {9,9};
    @BeforeEach
    public void ConstructMaze() throws MazeException {
        //TODO Add in call to automatically generate or manually make one so we can test solvability
        int[] size = {10,10};
        testMaze = new Maze(size, false);
        mySolver = new Solver();

        testMaze.setStart(STARTLOC);
        testMaze.setEnd(ENDLOC);
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
        boolean[] tile_0_0N = {true, true, false, false};
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
        mySolver.DFS(testMaze,START);
        long endTime = System.nanoTime();
        //mySolver.outputSolution();
        long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
        assertTrue(duration<60000,"Maze solver took longer then 60 seconds");
    }
    /**
     * Test 3: Test to see if the solver returns a valid search where the search starts at the starting location
     * and finishes at the end location
     * #ID 27
     * @author Jack
     */
    @Test
    public void TestValidSolver(){
        mySolver.DFS(testMaze,START);
        int l = mySolver.Solution().size();
        assertEquals(START,mySolver.Solution().get(0),"First position in solver is " +
                mySolver.Solution().get(l-1)[0] + ", " + mySolver.Solution().get(l-1)[1] + " instead of "+
                END[0] + ", " + END[1]);
        assertEquals(END[0] + ", " + END[1],mySolver.Solution().get(l-1)[0] + ", " +mySolver.Solution().get(l-1)[1]
                ,"Last position in solver is " + mySolver.Solution().get(l-1)[0] + ", "
                        + mySolver.Solution().get(l-1)[1] + " instead of "+ END[0] + ", " + END[1]);
    }
    /**
     * Test 4: Tests to see if it follows the fastest path for a blank maze from top left to bottom right.
     * This should be a diagonal path
     * #ID 28
     * @author Jack
     */
    @Test
    public void TestBestSolutionNoWalls(){
        mySolver.DFS(testMaze,START);
        ArrayList<Integer[]> Solution1 = new ArrayList<Integer[]>();
        //Across top
        for(int i=0;i<testMaze.mazeSize()[0]-1;i++){
            Integer[] pos = {i,0};
            Solution1.add(pos);

        }
        for(int i=0;i<testMaze.mazeSize()[0]-1;i++){
            Integer[] pos = {9,i};
            Solution1.add(pos);

        }
        Integer[] pos = {9,9};
        Solution1.add(pos);
        assertEquals(mySolver.outputSolution(Solution1),mySolver.outputSolution(),"Maze solver is not optimal");








    }

}
