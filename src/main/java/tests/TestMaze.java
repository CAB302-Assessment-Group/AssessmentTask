package src.main.java.tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import src.main.java.exceptions.MazeException;
import src.main.java.maze.core.Maze;
import src.main.java.maze.core.Tile;


public class TestMaze {
    Maze testMaze;


    @BeforeEach
    public void ConstructMaze(){
        int[] size = {5,5};
        testMaze = new Maze(size);
    }
    /**
     * Testing for Maze class constructor and important attributes such as Maze sizing
     * Test 1 and 2
     * @author JackFFFFFF
     */
    @Test
    public void TestTileDimensions(){
        assertEquals(5,testMaze.mazeSize()[0]);
        assertEquals(5,testMaze.mazeSize()[1]);
        System.out.println("Assigned height 5 matches Maze height");
        assertNotEquals(6,testMaze.mazeSize()[0]);
        assertNotEquals(6,testMaze.mazeSize()[1]);
        System.out.println("Wrong height 6 does not match Maze height");
    }

    /**
     * TODO: Test for following new fields (uncomment once implemented)
     * - deadEndCells
     * - visitedCells
     * - startloc
     * - endloc
     */
    @Test
    public void TestAttributes(){
        final String AUTHOR = "Me";
        final String NAME ="My cool maze";
        final String CREATEDATE ="24/04/2022";
        final String EDITDATE ="25/04/2022";
        final int VISITCELLS = 0;
        final int DEADENDCELLS = 5;
        final int[] STARTLOC = {2, 1};
        final int[] ENDLOC = {5, 3};
        final int ID = 51;

        testMaze.setAuthor(AUTHOR);
        testMaze.setMazeName(NAME);
        testMaze.setDateCreated(CREATEDATE);
        testMaze.setDateEdited(EDITDATE);
        testMaze.setId(ID);

        //testMaze.setVistCells(VISITCELLS);
        //testMaze.setDeadEnds(DEADENDCELLS);
        //testMaze.setStart(STARTLOC);
        //testMaze.setEnd(ENDLOC);

        assertEquals(ID,testMaze.getId());
        assertEquals(AUTHOR,testMaze.getAuthor());
        assertEquals(NAME,testMaze.getMazeName());
        assertEquals(CREATEDATE,testMaze.getDateCreated());
        assertEquals(EDITDATE,testMaze.getDateEdited());

        //assertEquals(VISITCELLS,testMaze.getVistCells());
        //assertEquals(DEADENDCELLS,testMaze.getDeadEnds());
        //assertEquals(STARTLOC,testMaze.getStart());
        //assertEquals(ENDLOC,testMaze.getEnd());

    }
    /**
     * Set a start position and have only one edge value be false
     * Test 3
     * @author JackFFFFFF
     */
    @Test
    public void TestSetStart() throws MazeException {
        //Top left corner
        int[] STARTLOC = {0, 0};
        //testMaze.setStart(STARTLOC)
        Tile[][] start = testMaze.getMazeTiles();
        Tile startTile = start[STARTLOC[1]][STARTLOC[1]];
        assertFalse(startTile.TopWall() || startTile.LeftWall());
        assertFalse(startTile.TopWall() && startTile.LeftWall());

        //Bottom left corner
        STARTLOC[0] = 0; STARTLOC[1] = 4;
        testMaze.setStart(STARTLOC);
        start = testMaze.getMazeTiles();
        startTile = start[STARTLOC[1]][STARTLOC[2]];
        assertFalse(startTile.BottomWall() || startTile.LeftWall());
        assertFalse(startTile.BottomWall() && startTile.LeftWall());

        //Bottom right corner
        STARTLOC[0] = 4; STARTLOC[1] = 4;
        testMaze.setStart(STARTLOC);
        start = testMaze.getMazeTiles();
        startTile = start[STARTLOC[1]][STARTLOC[2]];
        assertFalse(startTile.BottomWall() || startTile.RightWall());
        assertFalse(startTile.BottomWall() && startTile.RightWall());

        //Top right corner
        STARTLOC[0] = 4; STARTLOC[1] = 4;
        testMaze.setStart(STARTLOC);
        start = testMaze.getMazeTiles();
        startTile = start[STARTLOC[1]][STARTLOC[2]];
        assertFalse(startTile.TopWall() || startTile.RightWall());
        assertFalse(startTile.TopWall() && startTile.RightWall());
    }
    /**
     * Set a start position and have only one edge value be false. Not testing corners as previous test did this
     * Test 4
     * @author JackFFFFFF
     */
    @Test
    public void TestSetStartEdge() throws MazeException {
        int[] STARTLOC = {0, 1};
        //Downwards
        for(int i =1; i<testMaze.mazeSize()[1];i++){
            STARTLOC[1] = i;
            testMaze.setStart(STARTLOC);
            Tile[][] start = testMaze.getMazeTiles();
            Tile startTile = start[STARTLOC[1]][STARTLOC[2]];
            assertFalse(startTile.LeftWall());
        }
        //Across bottom
        STARTLOC[0]=1;STARTLOC[1]=4;
        for(int i =1; i<testMaze.mazeSize()[1];i++){
            STARTLOC[0] = i;
            testMaze.setStart(STARTLOC);
            Tile[][] start = testMaze.getMazeTiles();
            Tile startTile = start[STARTLOC[1]][STARTLOC[2]];
            assertFalse(startTile.LeftWall());
        }
        //Across bottom
        STARTLOC[0]=4;STARTLOC[1]=1;
        for(int i =1; i<testMaze.mazeSize()[1];i++){
            STARTLOC[1] = i;
            testMaze.setStart(STARTLOC);
            Tile[][] start = testMaze.getMazeTiles();
            Tile startTile = start[STARTLOC[1]][STARTLOC[2]];
            assertFalse(startTile.LeftWall());
        }
        //Across top
        STARTLOC[0]=0;STARTLOC[1]=1;
        for(int i =1; i<testMaze.mazeSize()[1];i++){
            STARTLOC[0] = i;
            testMaze.setStart(STARTLOC);
            Tile[][] start = testMaze.getMazeTiles();
            Tile startTile = start[STARTLOC[1]][STARTLOC[2]];
            assertFalse(startTile.LeftWall());
        }


    }
    /**
     * Set a start position not on the boundary
     * Test 5
     * @author JackFFFFFF
     */
    @Test
    public void TestInvalidStart() throws MazeException {
        //Middle of maze
        int[] STARTLOC = {3, 3};
        assertThrows(MazeException.class, () ->{
            testMaze.setStart(STARTLOC);
        });

        //Almost edge
        STARTLOC[1] = 2; STARTLOC[2] = 2;
        assertThrows(MazeException.class, () ->{
            testMaze.setStart(STARTLOC);
        });

        STARTLOC[1] = 4; STARTLOC[2] = 4;
        assertThrows(MazeException.class, () ->{
            testMaze.setStart(STARTLOC);
        });

    }
    /**
     * Set an end position and have only one edge value be false
     * Test 6
     * @author JackFFFFFF
     */
    @Test
    public void TestSetEnd() throws MazeException {
        //Top left corner
        int[] STARTLOC = {0, 0};
        testMaze.setEnd(STARTLOC);
        Tile[][] start = testMaze.getMazeTiles();
        Tile startTile = start[STARTLOC[1]][STARTLOC[1]];
        assertFalse(startTile.TopWall() || startTile.LeftWall());
        assertFalse(startTile.TopWall() && startTile.LeftWall());

        //Bottom left corner
        STARTLOC[0] = 0; STARTLOC[1] = 4;
        testMaze.setEnd(STARTLOC);
        start = testMaze.getMazeTiles();
        startTile = start[STARTLOC[1]][STARTLOC[2]];
        assertFalse(startTile.BottomWall() || startTile.LeftWall());
        assertFalse(startTile.BottomWall() && startTile.LeftWall());

        //Bottom right corner
        STARTLOC[0] = 4; STARTLOC[1] = 4;
        testMaze.setEnd(STARTLOC);
        start = testMaze.getMazeTiles();
        startTile = start[STARTLOC[1]][STARTLOC[2]];
        assertFalse(startTile.BottomWall() || startTile.RightWall());
        assertFalse(startTile.BottomWall() && startTile.RightWall());

        //Top right corner
        STARTLOC[0] = 4; STARTLOC[1] = 4;
        testMaze.setEnd(STARTLOC);
        start = testMaze.getMazeTiles();
        startTile = start[STARTLOC[1]][STARTLOC[2]];
        assertFalse(startTile.TopWall() || startTile.RightWall());
        assertFalse(startTile.TopWall() && startTile.RightWall());
    }
    /**
     * Set an end position and have only one edge value be false. Not testing corners as previous test did this
     * Test 7
     * @author JackFFFFFF
     */
    @Test
    public void TestSetEndEdge() throws MazeException {
        int[] STARTLOC = {0, 1};
        //Downwards
        for(int i =1; i<testMaze.mazeSize()[1];i++){
            STARTLOC[1] = i;
            testMaze.setEnd(STARTLOC);
            Tile[][] start = testMaze.getMazeTiles();
            Tile startTile = start[STARTLOC[1]][STARTLOC[2]];
            assertFalse(startTile.LeftWall());
        }
        //Across bottom
        STARTLOC[0]=1;STARTLOC[1]=4;
        for(int i =1; i<testMaze.mazeSize()[1];i++){
            STARTLOC[0] = i;
            testMaze.setEnd(STARTLOC);
            Tile[][] start = testMaze.getMazeTiles();
            Tile startTile = start[STARTLOC[1]][STARTLOC[2]];
            assertFalse(startTile.LeftWall());
        }
        //Across bottom
        STARTLOC[0]=4;STARTLOC[1]=1;
        for(int i =1; i<testMaze.mazeSize()[1];i++){
            STARTLOC[1] = i;
            testMaze.setEnd(STARTLOC);
            Tile[][] start = testMaze.getMazeTiles();
            Tile startTile = start[STARTLOC[1]][STARTLOC[2]];
            assertFalse(startTile.LeftWall());
        }
        //Across top
        STARTLOC[0]=0;STARTLOC[1]=1;
        for(int i =1; i<testMaze.mazeSize()[1];i++){
            STARTLOC[0] = i;
            testMaze.setEnd(STARTLOC);
            Tile[][] start = testMaze.getMazeTiles();
            Tile startTile = start[STARTLOC[1]][STARTLOC[2]];
            assertFalse(startTile.LeftWall());
        }


    }
    /**
     * Set an end position not on the boundary
     * Test 8
     * @author JackFFFFFF
     */
    @Test
    public void TestInvalidEnd() throws MazeException {
        //Middle of maze
        int[] STARTLOC = {3, 3};
        assertThrows(MazeException.class, () ->{
            testMaze.setEnd(STARTLOC);
        });

        //Almost edge
        STARTLOC[1] = 2; STARTLOC[2] = 2;
        assertThrows(MazeException.class, () ->{
            testMaze.setEnd(STARTLOC);
        });

        STARTLOC[1] = 4; STARTLOC[2] = 4;
        assertThrows(MazeException.class, () ->{
            testMaze.setEnd(STARTLOC);
        });

    }
    /**
     * Set a start and end and have them not be equal to each other
     * Test 9
     * @author JackFFFFFF
     */
    @Test
    public void TestDuplicateStartEnd() throws MazeException {
        //Top left corner
        int[] STARTLOC = {0, 0};
        int[] ENDLOC = {0, 0};
        assertThrows(MazeException.class, () ->{
            testMaze.setStart(STARTLOC);
            testMaze.setEnd(ENDLOC);
        });
        assertThrows(MazeException.class, () ->{
            testMaze.setEnd(ENDLOC);
            testMaze.setStart(STARTLOC);
        });

        //Bottom left corner
        STARTLOC[0]=0;
        STARTLOC[1]=4;
        assertThrows(MazeException.class, () ->{
            testMaze.setStart(STARTLOC);
            testMaze.setEnd(ENDLOC);
        });
        assertThrows(MazeException.class, () ->{
            testMaze.setEnd(ENDLOC);
            testMaze.setStart(STARTLOC);
        });

        //Bottom right corner
        STARTLOC[0]=4;
        STARTLOC[1]=4;
        assertThrows(MazeException.class, () ->{
            testMaze.setStart(STARTLOC);
            testMaze.setEnd(ENDLOC);
        });
        assertThrows(MazeException.class, () ->{
            testMaze.setEnd(ENDLOC);
            testMaze.setStart(STARTLOC);
        });

        //Top right corner
        STARTLOC[0]=4;
        STARTLOC[1]=1;
        assertThrows(MazeException.class, () ->{
            testMaze.setStart(STARTLOC);
            testMaze.setEnd(ENDLOC);
        });
        assertThrows(MazeException.class, () ->{
            testMaze.setEnd(ENDLOC);
            testMaze.setStart(STARTLOC);
        });
    }


}
