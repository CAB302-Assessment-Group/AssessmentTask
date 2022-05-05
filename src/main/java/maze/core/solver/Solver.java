package src.main.java.maze.core.solver;

import src.main.java.maze.core.*;

/**
 * The main solving class that uses DFS to calculate how to finish a maze
 * Litterally the worst code ever made, needs refactoring
 * @author Hudson
 */
public class Solver {
    public boolean[] neighbours(int tileLocationX, int tileLocationY, Maze myMaze) {
        Tile selectedTile = myMaze.mazeTile(tileLocationX, tileLocationY);

        boolean[] returnArray = {false, false, false, false};

        boolean[] walls = {
                selectedTile.RightWall(),
                selectedTile.TopWall(),
                selectedTile.LeftWall(),
                selectedTile.BottomWall()
        };

        returnArray[0] = (!walls[0] && tileLocationX != myMaze.mazeSize()[0]);
        returnArray[1] = (!walls[1] && tileLocationY != 0);
        returnArray[2] = (!walls[2] && tileLocationX != 0);
        returnArray[3] = !walls[3] && tileLocationY != myMaze.mazeSize()[1];

        return returnArray;
    }
}
