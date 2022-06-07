

import static org.junit.jupiter.api.Assertions.*;

import gui.Frame;
import gui.Render;
import org.junit.jupiter.api.*;
import src.main.java.exceptions.MazeException;
import maze.core.Maze;
import maze.core.solver.Solver;

import java.util.ArrayList;

public class TestSolver {
    Maze testMaze;
    Maze largeMaze;
    Maze massiveMaze;
    Solver mySolver;

    /**
     * @author Hudson
     */
    public final int[] STARTLOC = {0,0};
    public final int[] ENDLOC= {9,9};
    public final Integer[] START = {0,0};
    public final Integer[] END= {9,9};
    public int[] size2 = {45,62};
    public int[] size3 = {100,100};
    public int[] size1 = {10,10};
    @BeforeEach
    public void ConstructMaze() throws MazeException {


        testMaze = new Maze(size1);
        largeMaze = new Maze(size2);
        massiveMaze = new Maze(size3);
        //testMaze.generateMaze();
        mySolver = new Solver();

        testMaze.setStart(STARTLOC);
        testMaze.setEnd(ENDLOC);

        largeMaze.setStart(STARTLOC);
        largeMaze.setEnd(new int[]{44, 61});

        massiveMaze.setStart(STARTLOC);
        massiveMaze.setEnd(new int[]{99, 99});

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
        mySolver = new Solver();
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
    /**
     * Test 5: Test to see if the solver returns a valid search where the search starts at the starting location
     * and finishes at the end location for randomly generated mazes
     * @author Jack
     */
    @Test
    public void TestValidSolverRandom(){
        //Small test maze
        testMaze.generateMaze(1,false);
        mySolver.DFS(testMaze,START);
        int l = mySolver.Solution().size();
        assertEquals(START,mySolver.Solution().get(0),"First position in solver is " +
                mySolver.Solution().get(l-1)[0] + ", " + mySolver.Solution().get(l-1)[1] + " instead of "+
                END[0] + ", " + END[1]);
        assertEquals(END[0] + ", " + END[1],mySolver.Solution().get(l-1)[0] + ", " +mySolver.Solution().get(l-1)[1]
                ,"Last position in solver is " + mySolver.Solution().get(l-1)[0] + ", "
                        + mySolver.Solution().get(l-1)[1] + " instead of "+ END[0] + ", " + END[1]);

        //Large Maze
        largeMaze.generateMaze(5,false);
        Solver solver = new Solver();
        solver.DFS(largeMaze,START);
        l = solver.Solution().size();
        assertEquals(START,solver.Solution().get(0),"First position in solver is " +
                solver.Solution().get(l-1)[0] + ", " + solver.Solution().get(l-1)[1] + " instead of "+
                44 + ", " + 61);
        assertEquals(44 + ", " + 61,solver.Solution().get(l-1)[0] + ", " +solver.Solution().get(l-1)[1]
                ,"Last position in solver is " + solver.Solution().get(l-1)[0] + ", "
                        + solver.Solution().get(l-1)[1] + " instead of "+ 44 + ", " + 61);

        //Max size maze
        massiveMaze.generateMaze(10,false);
        mySolver = new Solver();
        mySolver.DFS(massiveMaze,START);
        l = mySolver.Solution().size();
        assertEquals(START,mySolver.Solution().get(0),"First position in solver is " +
                mySolver.Solution().get(l-1)[0] + ", " + mySolver.Solution().get(l-1)[1] + " instead of "+
                99 + ", " + 99);
        assertEquals(99 + ", " + 99,mySolver.Solution().get(l-1)[0] + ", " +mySolver.Solution().get(l-1)[1]
                ,"Last position in solver is " + mySolver.Solution().get(l-1)[0] + ", "
                        + mySolver.Solution().get(l-1)[1] + " instead of "+ 99 + ", " + 99);


    }
    /**
     * Test 6: Test to see if tiles visited is returning a valid number of tiles visited (i.e. greater than
     * 0 and less than x*y)
     * @author Jack
     */
    @Test
    public void TestTilesVisited(){
        //Small test maze
        testMaze.generateMaze(1,false);
        mySolver.DFS(testMaze,START);
        int l = mySolver.Solution().size();
        assertTrue(mySolver.tilesVisited()>0);
        assertTrue(mySolver.tilesVisited()<=(l*l));
        assertTrue(mySolver.tilesVisited()<=100);
        //Large Maze
        largeMaze.generateMaze(5,false);
        Solver solver = new Solver();
        solver.DFS(largeMaze,START);
        l = solver.Solution().size();
        assertTrue(solver.tilesVisited()>0);
        assertTrue(solver.tilesVisited()<=(l*l));
        assertTrue(solver.tilesVisited()<=100);

        //Max size maze
        massiveMaze.generateMaze(10,false);
        mySolver = new Solver();
        mySolver.DFS(massiveMaze,START);
        assertTrue(mySolver.tilesVisited()>0);
        assertTrue(mySolver.tilesVisited()<=(l*l));
        assertTrue(mySolver.tilesVisited()<=100);
    }
    /**
     * Test 7: Same as test 5 but on repeat to see what happens when regenerating maze occurs + solver solving again
     * @author Jack
     */
    @Test
    public void TestValidSolverRandomRepeat() throws MazeException {
        //Small test maze
        int l =0;
        for (int i=0;i<10;i++){
            testMaze = new Maze(size1);
            testMaze.setStart(STARTLOC);
            testMaze.setEnd(ENDLOC);
            testMaze.generateMaze(1,false);
            mySolver = new Solver();
            mySolver.DFS(testMaze,START);
            l = mySolver.Solution().size();
            assertEquals(START,mySolver.Solution().get(0),"First position in solver is " +
                    mySolver.Solution().get(l-1)[0] + ", " + mySolver.Solution().get(l-1)[1] + " instead of "+
                    END[0] + ", " + END[1]);
            assertEquals(END[0] + ", " + END[1],mySolver.Solution().get(l-1)[0] + ", " +mySolver.Solution().get(l-1)[1]
                    ,"Last position in solver is " + mySolver.Solution().get(l-1)[0] + ", "
                            + mySolver.Solution().get(l-1)[1] + " instead of "+ END[0] + ", " + END[1]);
        }


        //Large Maze
        for (int i=0;i<10;i++){
            mySolver = new Solver();
            largeMaze = new Maze(size2);
            largeMaze.generateMaze(5,false);
            largeMaze.setStart(STARTLOC);
            largeMaze.setEnd(new int[]{44, 61});
            mySolver.DFS(largeMaze,START);
            l = mySolver.Solution().size();
            assertEquals(START,mySolver.Solution().get(0),"First position in solver is " +
                    mySolver.Solution().get(l-1)[0] + ", " + mySolver.Solution().get(l-1)[1] + " instead of "+
                    44 + ", " + 61);
            assertEquals(44 + ", " + 61,mySolver.Solution().get(l-1)[0] + ", " +mySolver.Solution().get(l-1)[1]
                    ,"Last position in solver is " + mySolver.Solution().get(l-1)[0] + ", "
                            + mySolver.Solution().get(l-1)[1] + " instead of "+ 44 + ", " + 61);
        }


        //Max size maze
        for (int i=0;i<10;i++){
            mySolver = new Solver();
            massiveMaze = new Maze(size3);
            massiveMaze.setStart(STARTLOC);
            massiveMaze.setEnd(new int[]{99, 99});
            massiveMaze.generateMaze(10,false);
            mySolver = new Solver();
            mySolver.DFS(massiveMaze,START);
            l = mySolver.Solution().size();
            assertEquals(START,mySolver.Solution().get(0),"First position in solver is " +
                    mySolver.Solution().get(l-1)[0] + ", " + mySolver.Solution().get(l-1)[1] + " instead of "+
                    99 + ", " + 99);
            assertEquals(99 + ", " + 99,mySolver.Solution().get(l-1)[0] + ", " +mySolver.Solution().get(l-1)[1]
                    ,"Last position in solver is " + mySolver.Solution().get(l-1)[0] + ", "
                            + mySolver.Solution().get(l-1)[1] + " instead of "+ 99 + ", " + 99);
        }



    }

}
