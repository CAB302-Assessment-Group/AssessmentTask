package MazeClasses;

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

    public void setStart(int x, int y){

    }

    public void setEnd(int x, int y){

    }
}
