package maze.core;

import java.io.*;

public class Tile implements Serializable {
    private boolean leftWall;
    private boolean rightWall;
    private boolean topWall;
    private boolean bottomWall;
    private byte[] image;
    private boolean visited;
    private boolean active = false;

    public Tile(boolean[] walls){
        rightWall = walls[0];
        topWall = walls[1];
        leftWall = walls[2];
        bottomWall = walls[3];
        visited = false;
    }

    public void setImage(byte[] Image){
        this.image = Image;
    }

    public byte[] getImage(){
        return this.image;
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

    public void setState(boolean state) { active = state; }

    public boolean GetState() { return active; }

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

    public boolean getVisited(){ return visited;}
    public void setVisited(boolean visited){this.visited = visited;}
}
