package src.main.java.maze.core.solver;

import src.main.java.maze.core.*;

import java.util.ArrayList;

/**
 * The main solving class that uses DFS to calculate how to finish a maze
 * Litterally the worst code ever made, needs refactoring
 * @author Hudson
 */
public class Solver {
    private ArrayList<Integer[]> myTree = new ArrayList<Integer[]>();
    private ArrayList<Integer[]> visited = new ArrayList<Integer[]>();

    // getter for the solution
    public ArrayList<Integer[]> Solution() { return myTree; }

    // gets the neighbours of the current tile in a boolean array starting from 3 o'clock and moving anti-clockwise
    public boolean[] neighbours(int tileLocationX, int tileLocationY, Maze myMaze) {
        Tile selectedTile = myMaze.mazeTile(tileLocationX, tileLocationY);

        boolean[] returnArray = {false, false, false, false};

        boolean[] walls = {
                selectedTile.RightWall(),
                selectedTile.TopWall(),
                selectedTile.LeftWall(),
                selectedTile.BottomWall()
        };

        returnArray[0] = (!walls[0] && tileLocationX != myMaze.mazeSize()[0]);
        returnArray[1] = (!walls[1] && tileLocationY != 0);
        returnArray[2] = (!walls[2] && tileLocationX != 0);
        returnArray[3] = !walls[3] && tileLocationY != myMaze.mazeSize()[1];

        return returnArray;
    }

    // checks to see if the current position is a winning position
    // this is done by checking if the algorithm is on the edge of the board
    private boolean isWin(Integer[] position, Maze gb) {
        if (position[0] == gb.mazeSize()[0]) return true;
        if (position[0] == 0) return true;
        if (position[1] == gb.mazeSize()[1]) return true;
        if (position[1] == 0) return true;

        return false;
    }

    public Integer[] DFS(Maze gb, Integer[] position) {
        boolean[] tileN = neighbours(position[0], position[1], gb);

        // make sure that we haven't visited the tile yet
        boolean haveVisited = false;

        // check if the tile has already been visited
        // TODO: NEED TO REFACTOR THIS CODE TO HAVE BETTER PERFORMANCE
        for (int i = 0; i < visited.size(); i++) {
            if (visited.get(i) == position) {
                haveVisited = true;
            }
        }
        if (haveVisited) return null;

        // adds the current position to the solution tree
        myTree.add(position);

        // checks to see if the current position is winning
        if (isWin(position, gb)) {
            return position;
        }

        // goes through all possible options that a tile has
        if (tileN[0]) {
            Integer[] newTileLoc = {position[0] + 1, position[1]};
            Integer[] possibleSolution = DFS(gb, newTileLoc);
            if (possibleSolution != null) return possibleSolution;
        }

        if (tileN[1]) {
            Integer[] newTileLoc = {position[0], position[1] - 1};
            Integer[] possibleSolution = DFS(gb, newTileLoc);
            if (possibleSolution != null) return possibleSolution;
        }

        if (tileN[2]) {
            Integer[] newTileLoc = {position[0] -1, position[1]};
            Integer[] possibleSolution = DFS(gb, newTileLoc);
            if (possibleSolution != null) return possibleSolution;
        }

        if (tileN[3]) {
            Integer[] newTileLoc = {position[0], position[1] + 1};
            Integer[] possibleSolution = DFS(gb, newTileLoc);
            if (possibleSolution != null) return possibleSolution;
        }

        // add the current tile to the "visited" position
        // this is so we don't revisit it
        visited.add(position);

        // since the tile has had all possible children computed and they are dead ends
        // remove it from the solution
        myTree.remove(myTree.size());

        // return to the last node with possible options
        return null;
    }
}
