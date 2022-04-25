package src.main.java.maze.core;


public class Maze{
    private String Author;
    private String MazeName;
    private int id;
    private String DateEdited;
    private String DateCreated;
    private Tile[][] mazeTiles;

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getMazeName() {
        return MazeName;
    }

    public void setMazeName(String mazeName) {
        MazeName = mazeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateEdited() {
        return DateEdited;
    }

    public void setDateEdited(String dateEdited) {
        DateEdited = dateEdited;
    }

    public String getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(String dateCreated) {
        DateCreated = dateCreated;
    }

    public Tile[][] getMazeTiles() {
        return mazeTiles;
    }

    public void setMazeTiles(Tile[][] mazeTiles) {
        this.mazeTiles = mazeTiles;
    }




    public Maze(int[] size){
        mazeTiles = new Tile[size[0]][size[1]];

        for (int x = 0; x < size[0]; x++) {
            for (int y = 0; y < size[1]; y++) {
                boolean[] myTileWalls = {false, false, false, false};
                mazeTiles[x][y] = new Tile(myTileWalls);
            }
        }
    }

    public Tile mazeTile(int x, int y) {
        return mazeTiles[x][y];
    }

    public int[] mazeSize() {
        int[] myMazeSize = {mazeTiles.length, mazeTiles[1].length};
        return myMazeSize;
    }
}
