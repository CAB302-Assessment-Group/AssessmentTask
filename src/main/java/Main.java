

import src.main.java.exceptions.MazeException;
import gui.Frame;
import maze.core.Database;
import maze.core.Maze;
import maze.core.solver.Solver;

import javax.xml.crypto.Data;

public class Main {
    // program entry point
    public static Maze currentMaze;

    public static void main(String[] args) throws MazeException {



        // maze solver debugging
//        final int[] STARTLOC = {0,0};
//        final int[] ENDLOC= {9,9};
//        final Integer[] START = {0,0};
//        final Integer[] END= {9,9};
//
//        Maze testMaze;
//        Solver mySolver;
//
//        int[] size = {10,10};
//        testMaze = new Maze(size, false);
//        mySolver = new Solver();
//
//        testMaze.setStart(STARTLOC);
//        testMaze.setEnd(ENDLOC);
//
//        mySolver.DFS(testMaze,START);

        //Database.getInstance();


        Frame mainWindow = Frame.getInstance();
    }
}
