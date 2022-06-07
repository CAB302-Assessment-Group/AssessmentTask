
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import src.main.java.exceptions.MazeException;
import gui.Frame;
import gui.Render;
import maze.core.Maze;
import maze.core.Tile;


public class TestMaze {
    Maze testMaze;


    @BeforeEach
    public void ConstructMaze(){ //needs a param for child maze
        int[] size = {5,5};
        testMaze = new Maze(size);
    }
    /**
     * Test 1 and 2: Testing for Maze class constructor and important attributes such as Maze sizing.
     * @author JackFFFFFF, Jayden
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
     */
    @Test
    public void TestAttributes() throws MazeException {
        final String AUTHOR = "Me";
        final String NAME ="My cool maze";
        final String CREATEDATE ="24/04/2022";
        final String EDITDATE ="25/04/2022";
        final int VISITCELLS = 0;
        final int DEADENDCELLS = 5;
        final int[] STARTLOC = {1, 0};
        final int[] ENDLOC = {4, 3};
        final int ID = 51;
        final byte[] startImage = new byte[1000];
        final byte[] endImage = new byte[1000];

        testMaze.setAuthor(AUTHOR);
        testMaze.setMazeName(NAME);
        testMaze.setDateCreated(CREATEDATE);
        testMaze.setDateEdited(EDITDATE);
        testMaze.setId(ID);

        //testMaze.setVistCells(VISITCELLS);
        //testMaze.setDeadEnds(DEADENDCELLS);
        testMaze.setStart(STARTLOC);
        testMaze.setEnd(ENDLOC);
        testMaze.setStart(STARTLOC,startImage);
        testMaze.setEnd(ENDLOC,endImage);

        assertEquals(ID,testMaze.getId());
        assertEquals(AUTHOR,testMaze.getAuthor());
        assertEquals(NAME,testMaze.getMazeName());
        assertEquals(CREATEDATE,testMaze.getDateCreated());
        assertEquals(EDITDATE,testMaze.getDateEdited());

        //assertEquals(VISITCELLS,testMaze.getVistCells());
        //assertEquals(DEADENDCELLS,testMaze.getDeadEnds());
        assertEquals(STARTLOC,testMaze.getStart());
        assertEquals(ENDLOC,testMaze.getEnd());
        assertEquals(startImage,testMaze.getStartImage());
        assertEquals(null,testMaze.getEndImage());

    }
    /**
     * Test 3 :Set a start position and have only one edge value be false
     * @author JackFFFFFF
     */
    @Test
    public void TestSetStart() throws MazeException {
        //Top left corner
        int[] STARTLOC = {0, 0};
        testMaze.setStart(STARTLOC);
        Tile[][] start = testMaze.getMazeTiles();
        Tile startTile = start[STARTLOC[0]][STARTLOC[1]];
        assertFalse(startTile.TopWall() || startTile.LeftWall());
        assertFalse(startTile.TopWall() && startTile.LeftWall());

        //Bottom left corner
        STARTLOC[0] = 0; STARTLOC[1] = 4;
        testMaze.setStart(STARTLOC);
        start = testMaze.getMazeTiles();
        startTile = start[STARTLOC[0]][STARTLOC[1]];
        assertFalse(startTile.BottomWall() || startTile.LeftWall());
        assertFalse(startTile.BottomWall() && startTile.LeftWall());

        //Bottom right corner
        STARTLOC[0] = 4; STARTLOC[1] = 4;
        testMaze.setStart(STARTLOC);
        start = testMaze.getMazeTiles();
        startTile = start[STARTLOC[0]][STARTLOC[1]];
        assertFalse(startTile.BottomWall() || startTile.RightWall());
        assertFalse(startTile.BottomWall() && startTile.RightWall());

        //Top right corner
        STARTLOC[0] = 4; STARTLOC[1] = 4;
        testMaze.setStart(STARTLOC);
        start = testMaze.getMazeTiles();
        startTile = start[STARTLOC[0]][STARTLOC[1]];
        assertFalse(startTile.TopWall() || startTile.RightWall());
        assertFalse(startTile.TopWall() && startTile.RightWall());
    }
    /**
     * Test 4: Set a start position and have only one edge value be false. Not testing corners as previous test did this
     * @author JackFFFFFF
     */
    @Test
    public void TestSetStartEdge() throws MazeException {
        int[] STARTLOC = {0, 1};
        //Downwards
        for(int i =1; i<testMaze.mazeSize()[1]-1;i++){
            STARTLOC[1] = i;
            testMaze.setStart(STARTLOC);
            Tile[][] start = testMaze.getMazeTiles();
            Tile startTile = start[STARTLOC[0]][STARTLOC[1]];
            assertFalse(startTile.LeftWall());
        }
        //Across bottom
        STARTLOC[0]=1;STARTLOC[1]=4;
        for(int i =1; i<testMaze.mazeSize()[1]-1;i++){
            STARTLOC[0] = i;
            testMaze.setStart(STARTLOC);
            Tile[][] start = testMaze.getMazeTiles();
            Tile startTile = start[STARTLOC[0]][STARTLOC[1]];
            assertFalse(startTile.LeftWall());
        }
        //Across bottom
        STARTLOC[0]=4;STARTLOC[1]=1;
        for(int i =1; i<testMaze.mazeSize()[1]-1;i++){
            STARTLOC[1] = i;
            testMaze.setStart(STARTLOC);
            Tile[][] start = testMaze.getMazeTiles();
            Tile startTile = start[STARTLOC[0]][STARTLOC[1]];
            assertFalse(startTile.LeftWall());
        }
        //Across top
        STARTLOC[0]=1;STARTLOC[1]=0;
        for(int i =0; i<testMaze.mazeSize()[1]-1;i++){
            STARTLOC[0] = i;
            testMaze.setStart(STARTLOC);
            Tile[][] start = testMaze.getMazeTiles();
            Tile startTile = start[STARTLOC[0]][STARTLOC[1]];
            assertFalse(startTile.LeftWall());
        }


    }
    /**
     * Test 5: Set a start position not on the boundary
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
        STARTLOC[0] = 2; STARTLOC[1] = 2;
        assertThrows(MazeException.class, () ->{
            testMaze.setStart(STARTLOC);
        });

        STARTLOC[0] = 3; STARTLOC[1] = 3;
        assertThrows(MazeException.class, () ->{
            testMaze.setStart(STARTLOC);
        });

    }
    /**
     * Test 6: Set an end position and have only one edge value be false
     * @author JackFFFFFF
     */
    @Test
    public void TestSetEnd() throws MazeException {
        //Top left corner
        int[] STARTLOC = {0, 0};
        testMaze.setEnd(STARTLOC);
        Tile[][] start = testMaze.getMazeTiles();
        Tile startTile = start[STARTLOC[0]] [STARTLOC[1]];
        assertFalse(startTile.TopWall() || startTile.LeftWall());
        assertFalse(startTile.TopWall() && startTile.LeftWall());

        //Bottom left corner
        STARTLOC[0] = 0; STARTLOC[1] = 4;
        testMaze.setEnd(STARTLOC);
        start = testMaze.getMazeTiles();
        startTile = start[STARTLOC[0]][STARTLOC[1]];
        assertFalse(startTile.BottomWall() || startTile.LeftWall());
        assertFalse(startTile.BottomWall() && startTile.LeftWall());

        //Bottom right corner
        STARTLOC[0] = 4; STARTLOC[1] = 4;
        testMaze.setEnd(STARTLOC);
        start = testMaze.getMazeTiles();
        startTile = start[STARTLOC[0]][STARTLOC[1]];
        assertFalse(startTile.BottomWall() || startTile.RightWall());
        assertFalse(startTile.BottomWall() && startTile.RightWall());

        //Top right corner
        STARTLOC[0] = 4; STARTLOC[1] = 4;
        testMaze.setEnd(STARTLOC);
        start = testMaze.getMazeTiles();
        startTile = start[STARTLOC[0]][STARTLOC[1]];
        assertFalse(startTile.TopWall() || startTile.RightWall());
        assertFalse(startTile.TopWall() && startTile.RightWall());
    }
    /**
     * Test 7: Set an end position and have only one edge value be false. Not testing corners as previous test did this
     * @author JackFFFFFF
     * #ID 14
     */
    @Test
    public void TestSetEndEdge() throws MazeException {
        int[] STARTLOC = {0, 1};
        //Downwards
        for(int i =1; i<testMaze.mazeSize()[1]-1;i++){
            STARTLOC[1] = i;
            testMaze.setEnd(STARTLOC);
            Tile[][] start = testMaze.getMazeTiles();
            Tile startTile = start[STARTLOC[0]][STARTLOC[1]];
            assertFalse(startTile.LeftWall());
        }
        //Across bottom
        STARTLOC[0]=1;STARTLOC[1]=4;
        for(int i =1; i<testMaze.mazeSize()[1]-1;i++){
            STARTLOC[0] = i;
            testMaze.setEnd(STARTLOC);
            Tile[][] start = testMaze.getMazeTiles();
            Tile startTile = start[STARTLOC[0]][STARTLOC[1]];
            assertFalse(startTile.LeftWall());
        }
        //Across bottom
        STARTLOC[0]=4;STARTLOC[1]=1;
        for(int i =1; i<testMaze.mazeSize()[1]-1;i++){
            STARTLOC[1] = i;
            testMaze.setEnd(STARTLOC);
            Tile[][] start = testMaze.getMazeTiles();
            Tile startTile = start[STARTLOC[0]][STARTLOC[1]];
            assertFalse(startTile.LeftWall());
        }
        //Across top
        STARTLOC[0]=1;STARTLOC[1]=0;
        for(int i =1; i<testMaze.mazeSize()[1]-1;i++){
            STARTLOC[0] = i;
            testMaze.setEnd(STARTLOC);
            Tile[][] start = testMaze.getMazeTiles();
            Tile startTile = start[STARTLOC[0]][STARTLOC[1]];
            assertFalse(startTile.LeftWall());
        }


    }
    /**
     * Test 8: Set an end position not on the boundary
     * @author JackFFFFFF
     */
    @Test
    public void TestInvalidEnd() throws MazeException {
        //Middle of maze
        int[] STARTLOC = {2, 2};
        assertThrows(MazeException.class, () ->{
            testMaze.setEnd(STARTLOC);
        });

        //Almost edge
        STARTLOC[0] = 2; STARTLOC[1] = 2;
        assertThrows(MazeException.class, () ->{
            testMaze.setEnd(STARTLOC);
        });

        STARTLOC[0] = 3; STARTLOC[1] = 3;
        assertThrows(MazeException.class, () ->{
            testMaze.setEnd(STARTLOC);
        });

    }
    /**
     * Test 9: Set a start and end and have them not be equal to each other
     * @author JackFFFFFF & Jayden
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
        //Bottom left corner
        STARTLOC[0]=0;
        STARTLOC[1]=4;
        ENDLOC[0] = 0;
        ENDLOC[1] = 4;
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
        ENDLOC[0] = 4;
        ENDLOC[1] = 4;
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
        ENDLOC[0] = 4;
        ENDLOC[1] = 1;
        assertThrows(MazeException.class, () ->{
            testMaze.setStart(STARTLOC);
            testMaze.setEnd(ENDLOC);
        });
        assertThrows(MazeException.class, () ->{
            testMaze.setEnd(ENDLOC);
            testMaze.setStart(STARTLOC);
        });
    }
    /**
     * Test 10: Create a maze of 100x100 length
     * @author JackFFFFFF
     * #ID 26
     */
    @Disabled
    @Test
    public void TestMazeReq(){
        for(int x=2;x<=100;x++){
            for(int y=2;y<=100;y++){
                int[] size = {x,y};
                assertDoesNotThrow(() ->{
                    Maze testSizeMaze = new Maze(size);
                });
            }
        }

        //Negative case
        int[] size = {101,100};
        assertThrows(MazeException.class, () ->{
            Maze testSizeMaze = new Maze(size);
        },"Invalid size " + size[0] + ", " + size[1]);

        //Negative case
        size[0] = 100; size[0] = 101;
        assertThrows(MazeException.class, () ->{
            Maze testSizeMaze = new Maze(size);
        },"Invalid size " + size[0] + ", " + size[1]);

        //Negative case
        size[0] = 101; size[0] = 101;
        assertThrows(MazeException.class, () ->{
            Maze testSizeMaze = new Maze(size);
        },"Invalid size " + size[0] + ", " + size[1]);

        //Negative case
        size[0] = -1; size[0] = 100;
        assertThrows(MazeException.class, () ->{
            Maze testSizeMaze = new Maze(size);
        },"Invalid size " + size[0] + ", " + size[1]);

        //Negative case
        size[0] = 100; size[0] = -1;
        assertThrows(MazeException.class, () ->{
            Maze testSizeMaze = new Maze(size);
        },"Invalid size " + size[0] + ", " + size[1]);

        //Negative case
        size[0] = 100; size[0] = -1;
        assertThrows(MazeException.class, () ->{
            Maze testSizeMaze = new Maze(size);
        },"Invalid size " + size[0] + ", " + size[1]);

    }
    /**
     * Test 11:
     * Validates the validateInput is working for frame class
     * @author Jack
     */
    @Test
    public void TestValidateInput(){
        //Valid Case 1
        String[] inputs = {"1", "1"};
        assertTrue(Render.validateInput(inputs));

        //Valid Case 2
        inputs[0] ="1";inputs[1]="100";
        assertTrue(Render.validateInput(inputs));

        //Valid Case 3
        inputs[0] ="100";inputs[1]="1";
        assertTrue(Render.validateInput(inputs));

        //Valid Case 4
        inputs[0] ="100";inputs[1]="100";
        assertTrue(Render.validateInput(inputs));

        //Invalid Case 1
        inputs[0] ="1 2";inputs[1]="1 0 0";
        assertFalse(Render.validateInput(inputs), "Not valid inputs " + inputs);

        //Invalid Case 2
        inputs[0] ="0";inputs[1]="1";
        assertFalse(Render.validateInput(inputs), "Under limit for " + inputs);

        //Invalid Case 4
        inputs[0] ="-1";inputs[1]="0";
        assertFalse(Render.validateInput(inputs), "Under limit for " + inputs);

        //Invalid Case 5
        inputs[0] ="1F";inputs[1]="one";
        assertFalse(Render.validateInput(inputs),"Not valid inputs " + inputs);

    }


}
