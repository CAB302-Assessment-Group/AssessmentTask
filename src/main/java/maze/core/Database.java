package src.main.java.maze.core;

import src.main.java.util.statusCodes;

// using a singleton class to define the database since there won't be more than once
// instance of the database
/**
 * An instance of a database class which can upload and download generated mazes
 * @author Hudson
 */
public class Database {
    private static Database dbInstance = null;

    protected Database() {
        // System.out.println("Initiated database instance");
    }

    public Database getInstance() {
        if (dbInstance == null) dbInstance = new Database();

        return dbInstance;
    }

    /**
     * Loads a maze object from a DB record
     * @author Hudson
     * @param mazeName The maze name/unique indentifyer of the maze
     */
    public statusCodes.dbStatus loadMaze(String mazeName) {
        return statusCodes.dbStatus.FAILED;
    }

    /**
     * Validates user input for sizing maze
     * @author Hudson
     * @param myMaze An instance of a maze object which to export to the database
     * @return The status/success code of the function
     */
    public statusCodes.dbStatus exportMaze(Maze myMaze) {
        return statusCodes.dbStatus.FAILED;
    }
}
