package src.main.java.maze.core;

public class Maze{
    private String Author;
    private String Name;
    private Tile[][] myMaze;


    public Maze(int[] size){
        for (int x = 0; x < size[0]; x++) {
            for (int y = 0; y < size[1]; y++) {
                myMaze[x][y] = new Tile();
            }
        }
    }

    public Tile mazeTile(int[] cords) {
        return myMaze[cords[0]][cords[1]];
    }
}
