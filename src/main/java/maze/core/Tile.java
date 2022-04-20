package src.main.java.maze.core;

public class Tile {
    private boolean leftWall;
    private boolean rightWall;
    private boolean topWall;
    private boolean bottomWall;

    public Tile(boolean[] walls){
        rightWall = walls[0];
        topWall = walls[1];
        leftWall = walls[2];
        bottomWall = walls[3];
    }

    public boolean LeftWall() {
        return leftWall;
    }
    public boolean rightWall() {
        return rightWall;
    }
    private boolean topWall() {
        return topWall;
    }

    public boolean bottomWall() {
        return bottomWall;
    }
}
