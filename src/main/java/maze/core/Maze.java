package src.main.java.maze.core;


import src.main.java.exceptions.MazeException;

public class Maze{
    private String Author;
    private String MazeName;
    private int id;
    private String DateEdited;
    private String DateCreated;
    private Tile[][] mazeTiles;
    private int[] startLoc;
    private int[] endLoc;
    private boolean childMaze;
    private boolean OneLocSet;



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


    /**
     * Constructor for Maze object that generates a default tile set for the maze
     * TODO: Maybe add a check to prevent larger then 2 height arrays
     * @author Hudson
     * @param size where size[0] is the width(x) and size[1] is the height(y)
     */

    public Maze(int[] size, boolean childMaze){
        mazeTiles = new Tile[size[0]][size[1]];
        this.childMaze = childMaze;
        for (int x = 0; x < size[0]; x++) {
            for (int y = 0; y < size[1]; y++) {
                boolean[] myTileWalls = {false, false, false, false};
                mazeTiles[x][y] = new Tile(myTileWalls);
            }
        }
    }

    /**
     * Returns the Tile at the coordinates specified
     * @author Hudson
     * @param x width of maze
     * @param y height of maze
     * @return Tile at that location
     */
    public Tile mazeTile(int x, int y) {
        return mazeTiles[x][y];
    }

    /**
     * Gets the Maze size in terms of height and height (x,y)
     * @author Hudson
     * @return myMazeSize int[] where myMazeSize[0] is the width and myMazeSize[1] is the height
     */
    public int[] mazeSize() {
        int[] myMazeSize = {mazeTiles.length, mazeTiles[1].length};
        return myMazeSize;
    }

     /**
     * Validates that the start location is on the outside of the maze for adult mazes
     * @param start 2D position representing the start coordinate
     * @author Jayden
     * @throws MazeException
     */
    public void setStart(int[] start) throws MazeException {
        this.startLoc = start;
        if(OneLocSet){
            if(startLoc == endLoc)
                throw new MazeException("Start and End Locations are the Same");
        }

        if(start[0] == 0 || start[1] == 0 || start[0] == mazeTiles.length-1 || start[1] == mazeTiles[1].length-1){
            if(start[0]==0) // needs condition for on a corner
                this.mazeTile(start[0],start[1]).setLeftWall(false);
            if(start[1] == 0)
                this.mazeTile(start[0],start[1]).setTopWall(false);
            if (start[0] == mazeTiles.length-1)
                this.mazeTile(start[0],start[1]).setRightWall(false);
            if (start[1] == mazeTiles[1].length-1)
                this.mazeTile(start[0],start[1]).setBottomWall(false);
            this.OneLocSet = true;
        }else{
            throw new MazeException("Maze start position lies not on edge for Adult Maze");
        }
    }

    /**
     * Sets the start location for the maze for child mazes
     * @param start 2D position representing the start coordinate
     * @param StartImage image for the start tile in byte[] format
     * @author Jayden
     * @throws MazeException
     */
    public void setStart(int[] start, byte[] StartImage) throws MazeException {
        this.startLoc = start;
        this.mazeTile(start[0],start[1]).setStartImage(StartImage);
        if(OneLocSet){
            if(startLoc == endLoc)
                throw new MazeException("Start and End Locations are the Same");
        }
        this.OneLocSet = true;

    }
    /**
     * Validates that the end location is on the outside of the maze for adult mazes
     * For child mazes does not require this validation
     * @param end 2D position representing the start coordinate
     * @author Jayden
     * @throws MazeException
     */
    public void setEnd(int[] end) throws MazeException {
        this.endLoc = end;
        if(OneLocSet){
            if(startLoc == endLoc)
                throw new MazeException("Start and End Locations are the Same");
        }
        if(end[0] == 0 || end[1] == 0 || end[0] == mazeTiles.length-1 || end[1] == mazeTiles[1].length-1){
            if(end[0]==0) // needs condition for on a corner
                this.mazeTile(end[0],end[1]).setLeftWall(false);
            if(end[1] == 0)
                this.mazeTile(end[0],end[1]).setTopWall(false);
            if (end[0] == mazeTiles.length-1)
                this.mazeTile(end[0],end[1]).setRightWall(false);
            if (end[1] == mazeTiles[1].length-1)
                this.mazeTile(end[0],end[1]).setBottomWall(false);
            this.OneLocSet = true;
        }else{
            throw new MazeException("Maze start position lies not on edge for Adult Maze");
        }
    }

    /**
     * Sets the end location for the maze for child mazes
     * @param end 2D position representing the end coordinate
     * @param EndImage image for the end tile in byte[] format
     * @author Jayden
     * @throws MazeException
     */
    public void setEnd(int[] end, byte[] EndImage) throws MazeException {
        this.endLoc = end;
        this.mazeTile(end[0],end[1]).setEndImage(EndImage);
        if(OneLocSet){
            if(startLoc == endLoc)
                throw new MazeException("Start and End Locations are the Same");
        }
            this.OneLocSet = true;
    }


    public int[] getStart() {
        return startLoc;
    }

    public int[] getEnd() {
        return endLoc;
    }

    public byte[] getEndImage() {
        return this.mazeTile(endLoc[0],endLoc[1]).getEndImage();
    }

    public byte[] getStartImage() {
        return this.mazeTile(startLoc[0],startLoc[1]).getStartImage();
    }
}
