package maze.core.solver;

import maze.core.*;

import java.util.ArrayList;

/**
 * The main solving class that uses DFS to calculate how to finish a maze
 * Litterally the worst code ever made, needs refactoring
 * @author Hudson
 */
public class Solver {
    private ArrayList<Integer[]> myTree = new ArrayList<Integer[]>();
    private ArrayList<Integer[]> visited = new ArrayList<Integer[]>();

    private int totalTilesVisited = -1;
    private Maze myMaze;

    // getter for the solution
    public ArrayList<Integer[]> Solution() { return myTree; }

    /**
     * gets the neighbours of the current tile in a boolean array starting from 3 o'clock and moving anti-clockwise
     * @param tileLocationX Location of the tile row-wise
     * @param tileLocationY Location of the tile column-wise
     * @param myMaze Maze that the position is
     * @return boolean array of 4 values for determining if neighbours are valid
     * @author Hudson
     */
    public boolean[] neighbours(int tileLocationX, int tileLocationY, Maze myMaze) {
        Tile selectedTile = myMaze.mazeTile(tileLocationX, tileLocationY);

        boolean[] returnArray = {false, false, false, false};

        boolean[] walls = {
                selectedTile.RightWall(),
                selectedTile.BottomWall(),
                selectedTile.LeftWall(),
                selectedTile.TopWall()
        };

        returnArray[0] = (!walls[0] && tileLocationX != (myMaze.mazeSize()[0] - 1));
        returnArray[1] = (!walls[1] && tileLocationY != (myMaze.mazeSize()[1] - 1));
        returnArray[2] = (!walls[2] && tileLocationX != 0);
        returnArray[3] = (!walls[3] && tileLocationY != 0);

        return returnArray;
    }

    // checks to see if the current position is a winning position
    // this is done by checking if the algorithm is on the edge of the board

    /**
     * Checks to see if the current position is a winning position.
     * This is done by checking if the algorithm is on the edge of the board
     * @param position Current position determined by DFS
     * @param gb Maze to be solved
     * @return True if the position is a good value that is on the edge or is the end, else do not
     */
    private boolean isWin(Integer[] position, Maze gb) {
        //if (position[0] == gb.mazeSize()[0]) return true;
        //if (position[0] == 0) return true;
        //if (position[1] == gb.mazeSize()[1]) return true;
        //if (position[1] == 0) return true;

        if (position[0] == gb.getEnd()[0] && position[1] == gb.getEnd()[1]) return true;

        return false;
    }

    /**
     * Depth First Search Algorithim that recursively calls itself until it reaches the end or
     * everything is a dead end
     * @param gb Maze to solve
     * @param position The position going into before solving further
     * @return The new position the search has chosen to go to based off of position and current explored maze
     * @author Hudson
     */

    public Integer[] DFS(Maze gb, Integer[] position) {
        myMaze = gb;
        totalTilesVisited++;

        boolean[] tileN = neighbours(position[0], position[1], gb);

        // adds the current position to the solution tree
        myTree.add(position);

        // checks to see if the current position is winning

        if (isWin(position, gb)) {
            return position;
        }

        // goes through all possible options that a tile has
        if (tileN[0]) {
            Integer[] newTileLoc = {position[0] + 1, position[1]};

            // check that the new tile location hasn't been visited
            if (!(inList(visited, newTileLoc))) {

                // search through the next solution
                Integer[] possibleSolution = DFS(gb, newTileLoc);

                if (possibleSolution != null) {
                    return possibleSolution;
                }
            }
        }

        if (tileN[1]) {
            Integer[] newTileLoc = {position[0], position[1] + 1};

            if (!(inList(visited, newTileLoc))) {
                Integer[] possibleSolution = DFS(gb, newTileLoc);

                if (possibleSolution != null) {
                    return possibleSolution;
                }
            }
        }

        if (tileN[2]) {
            Integer[] newTileLoc = {position[0] - 1, position[1]};

            if (!(inList(visited, newTileLoc))) {
                Integer[] possibleSolution = DFS(gb, newTileLoc);

                if (possibleSolution != null) {
                    return possibleSolution;
                }
            }
        }

        if (tileN[3]) {
            Integer[] newTileLoc = {position[0], position[1] - 1};

            if (!(inList(visited, newTileLoc))) {
                Integer[] possibleSolution = DFS(gb, newTileLoc);

                if (possibleSolution != null) {
                    return possibleSolution;
                }
            }
        }

        // add the current tile to the "visited" position
        // this is so we don't revisit it
        visited.add(position);

        // since the tile has had all possible children computed and they are dead ends
        // remove it from the solution
        myTree.remove(myTree.size());

        // return to the last node with possible options
        totalTilesVisited--;
        return null;
    }

    private boolean inList(ArrayList<Integer[]> array, Integer[] element) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == element) {
                return true;
            }
        }

        return false;
    }

    public int tilesVisited() {
        return (totalTilesVisited == -1) ? (myMaze.mazeSize()[0] + myMaze.mazeSize()[1]) / totalTilesVisited : totalTilesVisited;
    }

    /**
     * Method to print out solution in a way that can be tested/debugged easier
     * @return String output in form x1,y1 -> x2,y2 -> ... -> xEnd, yEnd ->
     * @author Jack
     */
    public String outputSolution(){
        String output="";
        for (Integer[] i: this.Solution()) {
            output+=(i[0] +","+i[1] +" -> ");
        }
        return  output;
    }
    /**
     * Method to print out solution in a way that can be tested/debugged easier
     * @return String output in form x1,y1 -> x2,y2 -> ... -> xEnd, yEnd ->
     * @author Jack
     */
    public String outputSolution(ArrayList<Integer[]> Solution){
        String output="";
        for (Integer[] i: Solution) {
            output+=(i[0] +","+i[1] +" -> ");
        }
        return  output;
    }
}
