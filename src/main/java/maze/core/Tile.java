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

    public void setLeftWall(boolean wall){
        this.leftWall = wall;
    }

    public void setRightWall(boolean wall){
        this.rightWall = wall;
    }

    public void setTopWall(boolean wall){
        this.topWall = wall;
    }

    public void setBottomWall(boolean wall){
        this.bottomWall = wall;
    }

    public boolean LeftWall() {
        return leftWall;
    }
    public boolean RightWall() {
        return rightWall;
    }
    public boolean TopWall() {
        return topWall;
    }
    public boolean BottomWall() {
        return bottomWall;
    }
}
