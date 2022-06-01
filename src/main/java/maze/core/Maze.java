package maze.core;

import src.main.java.exceptions.MazeException;

import java.io.Serializable;
import java.util.ArrayList;

public class Maze implements MazeOutline, Serializable {
    private String Author;
    private String MazeName;
    private int id;
    private String DateEdited;
    private String DateCreated;
    private Tile[][] mazeTiles;
    private int[] startLoc;
    private int[] endLoc;
    private boolean OneLocSet;
    private String lastEditor;



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

    public void setId(int id) { this.id = id; }

    public String GetLastEditor() { return lastEditor; }

    public void SetLastEditor(String name) { lastEditor = name; }

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

    public Maze(int[] size){
        mazeTiles = new Tile[size[0]][size[1]];
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
        if(OneLocSet){
            if(startLoc !=null && startLoc == endLoc)
            {
                throw new MazeException("Start and End Locations are the Same");
            }
            else {
                this.startLoc=start;
            }


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
        if (OneLocSet) {
            if (startLoc != null && startLoc == endLoc) {
                throw new MazeException("Start and End Locations are the Same");
            } else {
                this.startLoc=start;
                this.mazeTile(startLoc[0], startLoc[1]).setStartImage(StartImage);
            }
            this.OneLocSet = true;

        }
    }
    /**
     * Validates that the end location is on the outside of the maze for adult mazes
     * For child mazes does not require this validation
     * @param end 2D position representing the start coordinate
     * @author Jayden
     * @throws MazeException
     */
    public void setEnd(int[] end) throws MazeException {
        if(OneLocSet){
            if(startLoc!=null && startLoc == endLoc){
                throw new MazeException("Start and End Locations are the Same");
            }
            else{
                this.endLoc=end;

            }

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
     * @author Jayden, Jack
     * @throws MazeException
     */
    public void setEnd(int[] end, byte[] EndImage) throws MazeException {
        if(OneLocSet){
            if(endLoc !=null && startLoc == endLoc)
                throw new MazeException("Start and End Locations are the Same");
            else {
                this.endLoc=end;
                this.mazeTile(endLoc[0], endLoc[1]).setStartImage(EndImage);
            }

        }
            this.OneLocSet = true;
    }


    public int[] getStart() {
        return startLoc;
    }

    public int[] getEnd() {
        return endLoc;
    }

    /**
     * End image does not exist for a standard maze
     * @return
     */
    public byte[] getEndImage() {
        return null;
    }

    public byte[] getStartImage() {
        return this.mazeTile(startLoc[0],startLoc[1]).getStartImage();
    }

    /**
     * Automatically generates the maze between the start and end tiles
     * Utilises Wilson's Algorithm
     * @author Jayden
     *
     */
    public boolean generateMaze(int hasIm) {
        int[] location = new int[]{0,0};


        for(int i = 0; i<mazeSize()[0]; i++){ //set all walls to be true
            for(int j = 0; j<mazeSize()[1]; j++){
                mazeTile(i,j).setLeftWall(true);
                mazeTile(i,j).setRightWall(true);
                mazeTile(i,j).setTopWall(true);
                mazeTile(i,j).setBottomWall(true);
            }
        }


        ArrayList<int[]> currentwalk = new ArrayList<>();
        currentwalk.add(new int[]{location[0],location[1]});
        int visited = 1;
        mazeTile(location[0],location[1]).setVisited(true);
        int step;
        int max = mazeSize()[0] * mazeSize()[1];

        int[] lastloc = new int[]{location[0],location[1]};

        if(hasIm > 0){
            int diffX = mazeSize()[0] - hasIm;
            int diffY = mazeSize()[1] - hasIm;
            System.out.println(diffX/2+","+diffY/2);
            for(int x = 0;x<hasIm;x++){
                for(int y = 0; y<hasIm;y++){
                    visited++;
                    mazeTile(diffX/2 + x,diffY/2 + y).setVisited(true);
                    mazeTile(diffX/2 + x,diffY/2 + y).setTopWall(false);
                    mazeTile(diffX/2 + x,diffY/2 + y).setLeftWall(false);
                    mazeTile(diffX/2 + x,diffY/2 + y).setRightWall(false);
                    mazeTile(diffX/2 + x,diffY/2 + y).setBottomWall(false);
                }
            }
            if(hasIm == 1){
                mazeTile(diffX/2,diffY/2).setTopWall(true);
                mazeTile(diffX/2,diffY/2).setLeftWall(true);
                mazeTile(diffX/2,diffY/2).setRightWall(true);
                mazeTile(diffX/2,diffY/2).setBottomWall(true);
            }else{
                for(int i = 0; i < hasIm;i++){ //top row
                    mazeTile(diffX/2 + i,diffY/2).setTopWall(true);
                    mazeTile(diffX/2 + i,diffY/2).setBottomWall(false);
                }
                for(int i = 0; i < hasIm;i++){ //right row
                    mazeTile(diffX/2 + hasIm - 1,diffY/2 + i).setRightWall(true);
                    mazeTile(diffX/2 + hasIm - 1,diffY/2 + i).setLeftWall(false);
                }
                for(int i = 0; i < hasIm;i++){ //bottom row
                    mazeTile(diffX/2 + i,diffY/2 + hasIm - 1).setBottomWall(true);
                    mazeTile(diffX/2 + i,diffY/2 + hasIm - 1).setTopWall(false);
                }
                for(int i = 0; i < hasIm;i++){ //left row
                    mazeTile(diffX/2,diffY/2 + i).setLeftWall(true);
                    mazeTile(diffX/2,diffY/2 + i).setRightWall(false);
                }
            }



        }

        while(visited < max){

            while(true){ //pick a valid step
                step = (int)Math.round(Math.random() * 4);

                if(step == 0){ //go up
                    if(location[1] != 0){
                        location[1]--;
                        break;
                    }
                }else if(step == 1){ //go down
                    if(location[1] != mazeSize()[1] - 1){
                        location[1]++;
                        break;
                    }
                }else if(step == 2){ //go left
                    if(location[0] != 0){
                        location[0]--;
                        break;
                    }
                }else{ //go right
                    if(location[0] != mazeSize()[0] - 1){
                        location[0]++;
                        break;
                    }
                }
            }

            if(mazeTile(location[0],location[1]).getVisited() == false){ //location has not been visited
                if(step == 0){ //gone up
                    mazeTile(location[0],location[1]).setBottomWall(false);
                    mazeTile(lastloc[0],lastloc[1]).setTopWall(false);
                }else if(step == 1){ //gone down
                    mazeTile(location[0],location[1]).setTopWall(false);
                    mazeTile(lastloc[0],lastloc[1]).setBottomWall(false);
                }else if(step == 2){ //gone left
                    mazeTile(location[0],location[1]).setRightWall(false);
                    mazeTile(lastloc[0],lastloc[1]).setLeftWall(false);
                }else{ //gone right
                    mazeTile(location[0],location[1]).setLeftWall(false);
                    mazeTile(lastloc[0],lastloc[1]).setRightWall(false);
                }
                mazeTile(location[0],location[1]).setVisited(true);
                currentwalk.add(new int[]{location[0],location[1]});
                visited++;
                //System.out.println(location[0]+", "+location[1]);
            }else { //cell has already been visited, try all other possibilities from

                int count = 0;
                boolean breaker = true;
                while (breaker == true) {
                    location = new int[] {lastloc[0],lastloc[1]}; //reset the location
                    step = Math.floorMod(step + 1, 4); //increment the step and try again
                    if (step == 0) { //go up
                        if (location[1] != 0) {
                            location[1]--;
                        }
                    } else if (step == 1) { //go down
                        if (location[1] != mazeSize()[1] - 1) {
                            location[1]++;
                        }
                    } else if (step == 2) { //go left
                        if (location[0] != 0) {
                            location[0]--;
                        }
                    } else { //go right
                        if (location[0] != mazeSize()[0] - 1) {
                            location[0]++;
                        }
                    }
                    breaker = mazeTile(location[0], location[1]).getVisited(); //check if new cell has been visited
                    count++;
                    if (count > 3) { //tried all options
                        location = currentwalk.get(0);
                        currentwalk.remove(0); //pop from front of queue
                        //System.out.println("reverted: "+location[0]+", "+location[1]);
                        break;

                    }
                }
                if(!breaker){
                    if(step == 0){ //gone up
                        mazeTile(location[0],location[1]).setBottomWall(false);
                        mazeTile(lastloc[0],lastloc[1]).setTopWall(false);
                    }else if(step == 1){ //gone down
                        mazeTile(location[0],location[1]).setTopWall(false);
                        mazeTile(lastloc[0],lastloc[1]).setBottomWall(false);
                    }else if(step == 2){ //gone left
                        mazeTile(location[0],location[1]).setRightWall(false);
                        mazeTile(lastloc[0],lastloc[1]).setLeftWall(false);
                    }else{ //gone right
                        mazeTile(location[0],location[1]).setLeftWall(false);
                        mazeTile(lastloc[0],lastloc[1]).setRightWall(false);
                    }
                    mazeTile(location[0],location[1]).setVisited(true);
                    currentwalk.add(new int[]{location[0],location[1]});
                    visited++;
                    //System.out.println(location[0]+", "+location[1]);
                }
            }
            int out = 0;
            //System.out.println("Last: "+lastloc[0]+", "+lastloc[1]);
            //System.out.println("Current: " + location[0]+", "+location[1]);
            //System.out.println(step);


            int x = location[0];
            int y = location[1];
            lastloc = new int[]{x,y};
        }

        return false;
    }

    /**
     * counts the number of dead end tiles, (potentially includes solution tile though)
     *
     * @author Jayden
     *
     */
    public int numDeadEnds(){
        int out = 0;
        for(int i=0; i<mazeSize()[0]; i++){
            for(int j=0; j<mazeSize()[1];j++){
                int countdeadends = 0;
                if(mazeTile(i,j).BottomWall()){
                    countdeadends++;
                }
                if(mazeTile(i,j).LeftWall()){
                    countdeadends++;
                }
                if(mazeTile(i,j).RightWall()){
                    countdeadends++;
                }
                if(mazeTile(i,j).TopWall()){
                    countdeadends++;
                }

                if(countdeadends==3){
                    out++;
                }
            }
        }
        return out;
    }
}
