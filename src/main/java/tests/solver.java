package src.main.java.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import src.main.java.maze.core.Maze;
import src.main.java.maze.core.solver.Solver;

public class solver {
    Maze testMaze;
    Solver mySolver;

    /**
     * @author Hudson
     */
    @BeforeEach
    public void ConstructMaze(){
        int[] size = {5,5};
        testMaze = new Maze(size);
        mySolver = new Solver();
    }

    @Test
    public void TestNeighbours() {
        // make sure that the function returns a value
        assertNotNull(mySolver.neighbours(0, 0, testMaze));

        boolean[] tile_0_0N = {true, false, false, true};
        assertEquals(tile_0_0N, mySolver.neighbours(0, 0, testMaze));

        testMaze.mazeTile(3, 4).setRightWall(true);
        boolean[] tile_4_4N = {true, true, false, true};
        assertEquals(tile_4_4N, mySolver.neighbours(4, 4, testMaze));
    }
}
