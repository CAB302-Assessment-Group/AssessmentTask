package src.main.java.maze.core;

public class Maze{
    private String Author;
    private String Name;
    private Tile[][] Maze;


    public Maze(String Author, String Name, int[] size){
        this.Author = Author;
        this.Name = Name;

        if(size.length != 2){
            //Error Throwing
        }else{
            this.Maze = new Tile[size[1]][size[2]];
        }
    }

    public Tile tile(int x, int y) {
        if (x < Maze.length || y < Maze[0].length) {
            return new Tile();
        }

        return Maze[x][y];
    }

    public void setStart(int x, int y){
    }

    public void getStart() {
    }

    public void setEnd(int x, int y) {
    }

    public void getEnd() {
    }

    public int[] getSize() {
        int[] size = {Maze.length, Maze[0].length};
        return size;
    }
}
