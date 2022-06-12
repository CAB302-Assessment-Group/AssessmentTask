

import maze.core.util;
import org.junit.jupiter.api.*;
import src.main.java.exceptions.MazeException;
import maze.core.Database;
import maze.core.Maze;
import maze.core.solver.Solver;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestDatabase {
    Maze testChildMaze;
    Solver testSolver;
    final String MAZENAME ="Test";
    final String AUTHOR ="Test";
    public final int[] STARTLOC = {0,0};
    public final int[] ENDLOC= {9,9};
    Database testDatabase = new Database();

    @BeforeEach
    public void Before() throws MazeException {

        final int[] SIZE = {10,10};

        testChildMaze = new Maze(SIZE);
        testChildMaze.setStart(STARTLOC);
        testChildMaze.setEnd(ENDLOC);
        testChildMaze.setMazeName(MAZENAME);
        testChildMaze.setAuthor(AUTHOR);

        Connection newConnection = testDatabase.getInstance();
    }


    /**
     * Test 1: Export a children's maze and have it be stored on the database successfully
     * #ID 9
     * @author Jack
     */
    @Test
    public void TestUpdateChildren() {
        //Test positive case
        assertEquals(testDatabase.exportMaze(testChildMaze), util.statusCodes.dbStatus.OK);


    }
    /**
     * Test 3: Export a maze that should not be stored and return an error saying it should
     * not be able to be stored
     * @author Jack
     */
    // I would argue that the DB export function should fail gracefully
    @Test
    @Disabled
    public void TestFailUpdateChildren() {
        //Test with everything null
        util.statusCodes.dbStatus result = testDatabase.exportMaze(null);
        assertEquals(testDatabase.exportMaze(null), util.statusCodes.dbStatus.INVALID_ARGUMENTS,
                "Should return " + util.statusCodes.dbStatus.INVALID_ARGUMENTS
                        + " but returned " + result);

        //Test with author null
        Maze temp = testChildMaze;
        testChildMaze.setAuthor(null);
        result = testDatabase.exportMaze(testChildMaze);
        assertEquals(testDatabase.exportMaze(testChildMaze), util.statusCodes.dbStatus.INVALID_ARGUMENTS,
                "Should return " + util.statusCodes.dbStatus.INVALID_ARGUMENTS
                        + " but returned " + result);
        testChildMaze=temp;

        //Test with name null
        testChildMaze.setMazeName(null);
        result = testDatabase.exportMaze(testChildMaze);
        assertEquals(testDatabase.exportMaze(testChildMaze), util.statusCodes.dbStatus.INVALID_ARGUMENTS,
                "Should return " + util.statusCodes.dbStatus.INVALID_ARGUMENTS
                        + " but returned " + result);
        testChildMaze=temp;

        //Test with invalid id
        testChildMaze.setId(-1);
        result = testDatabase.exportMaze(testChildMaze);
        assertEquals(testDatabase.exportMaze(testChildMaze), util.statusCodes.dbStatus.INVALID_ARGUMENTS,
                "Should return " + util.statusCodes.dbStatus.INVALID_ARGUMENTS
                        + " but returned " + result);
        testChildMaze=temp;

        //Test with invalid date
        testChildMaze.setDateEdited(null);
        result = testDatabase.exportMaze(testChildMaze);
        assertEquals(testDatabase.exportMaze(testChildMaze), util.statusCodes.dbStatus.INVALID_ARGUMENTS,
                "Should return " + util.statusCodes.dbStatus.INVALID_ARGUMENTS
                        + " but returned " + result);
        testChildMaze=temp;

        //Test with invalid date
        testChildMaze.setDateCreated(null);
        result = testDatabase.exportMaze(testChildMaze);
        assertEquals(testDatabase.exportMaze(testChildMaze), util.statusCodes.dbStatus.INVALID_ARGUMENTS,
                "Should return " + util.statusCodes.dbStatus.INVALID_ARGUMENTS
                        + " but returned " + result);
        testChildMaze=temp;

        //Test with invalid date
        testChildMaze.setDateCreated(null);
        result = testDatabase.exportMaze(testChildMaze);
        assertEquals(testDatabase.exportMaze(testChildMaze), util.statusCodes.dbStatus.INVALID_ARGUMENTS,
                "Should return " + util.statusCodes.dbStatus.INVALID_ARGUMENTS
                        + " but returned " + result);
        testChildMaze=temp;
    }

    /**
     * Test 4: Download maze by author or name and return a correct value
     * @author Jack
     * #ID 10
     */
    @Test
    public void TestDownload() {
        util.statusCodes.dbStatus result = testDatabase.exportMaze(testChildMaze);
        if(result == util.statusCodes.dbStatus.OK){
            //Search by Name
            assertFalse(testDatabase.loadMaze(MAZENAME).equals(new ArrayList<Maze>()));

            //Search by Author
            assertFalse(testDatabase.loadMaze(AUTHOR).equals(new ArrayList<Maze>()));
        }

    }
    /**
     * Test 5: Download maze by author or name that is not in the db and have it return a fail
     * @author Jack
     */
    @Test
    public void TestFailDownload() {
        //Search by Name
        assertEquals(testDatabase.loadMaze("cRaZy MAZE"), new ArrayList<Maze>());

        //Search by Author
        assertEquals(testDatabase.loadMaze("staff"), new ArrayList<Maze>());
    }
    /**
     * Test 6: Download maze by author or name that is not in the db and have it return a fail
     * @author Jack
     * #ID 12
     */
    @Test
    public void TestDownloadSolution() {
        util.statusCodes.dbStatus result = testDatabase.exportMaze(testChildMaze);
        if(result == util.statusCodes.dbStatus.OK){
            //Search by Name
            assertFalse(testDatabase.loadMaze(MAZENAME).equals(new ArrayList<Maze>()));
            //testChildMaze = databaseOutput
            //downloadedSolution = testChildMaze.Solution();
            //assertEquals(testSolver.Solution(),downloadedSolution);
        }
    }
}
