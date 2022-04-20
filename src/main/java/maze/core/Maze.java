package src.main.java.maze.core;

import java.util.ArrayList;

public class Maze{
    private String Author;
    private String Name;
    private Tile[][] myMaze = new Tile[60][60];


    public Maze(int[] size){
        for (int x = 0; x < size[0]; x++) {
            for (int y = 0; y < size[1]; y++) {
                boolean[] myTileWalls = {false, false, false, false};
                myMaze[x][y] = new Tile(myTileWalls);
            }
        }
    }

    public Tile mazeTile(int x, int y) {
        return myMaze[x][y];
    }

    public int[] mazeSize() {
        int[] myMazeSize = {60, 60};
        return myMazeSize;
    }
}
