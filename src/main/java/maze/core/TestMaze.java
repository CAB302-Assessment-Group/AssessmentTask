package src.main.java.maze.core;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


public class TestMaze {
    Maze testMaze;

    @BeforeEach
    public void ConstructMaze(){
        int[] size = {5,5};
        testMaze = new Maze(size);
    }
    @Test
    public void TestLength(){
        assertEquals(5,testMaze.getMazeTiles().length);
        System.out.println("Assigned length 5 matches Maze length");
        assertNotEquals(6,testMaze.getMazeTiles().length);
        System.out.println("Wrong length 6 does not match Maze length");
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
