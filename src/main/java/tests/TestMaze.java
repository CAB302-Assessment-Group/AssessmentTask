package src.main.java.tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import src.main.java.maze.core.Maze;


public class TestMaze {
    Maze testMaze;

    /**
     * Testing for Maze class constructor and important attributes such as Maze sizing
     * @author JackFFFFFF
     */
    @BeforeEach
    public void ConstructMaze(){
        int[] size = {5,5};
        testMaze = new Maze(size);
    }
    @Test
    public void TestTileDimensions(){
        assertEquals(5,testMaze.mazeSize()[0]);
        assertEquals(5,testMaze.mazeSize()[1]);
        System.out.println("Assigned height 5 matches Maze height");
        assertNotEquals(6,testMaze.mazeSize()[0]);
        assertNotEquals(6,testMaze.mazeSize()[1]);
        System.out.println("Wrong height 6 does not match Maze height");
    }
    @Test
    public void TestAttributes(){
        final String AUTHOR = "Me";
        final String NAME ="My cool maze";
        final String CREATEDATE ="24/04/2022";
        final String EDITDATE ="25/04/2022";
        final int ID = 51;

        testMaze.setAuthor(AUTHOR);
        testMaze.setMazeName(NAME);
        testMaze.setDateCreated(CREATEDATE);
        testMaze.setDateEdited(EDITDATE);
        testMaze.setId(ID);

        assertEquals(ID,testMaze.getId());
        assertEquals(AUTHOR,testMaze.getAuthor());
        assertEquals(NAME,testMaze.getMazeName());
        assertEquals(CREATEDATE,testMaze.getDateCreated());
        assertEquals(EDITDATE,testMaze.getDateEdited());
    }

}
