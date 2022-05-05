package src.main.java.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import src.main.java.maze.core.Maze;
import src.main.java.maze.core.Tile;
import src.main.java.maze.core.solver.Solver;

import java.util.ArrayList;
import java.util.Arrays;

public class solver {
    Maze testMaze;
    Solver mySolver;

    /**
     * @author Hudson
     */
    @BeforeEach
    public void ConstructMaze(){
        int[] size = {10,10};
        testMaze = new Maze(size);
        mySolver = new Solver();
    }

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
}
