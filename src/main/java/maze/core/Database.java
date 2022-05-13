package src.main.java.maze.core;

import src.main.java.util.statusCodes;

// SQLITE3 database import
import javax.sql.rowset.serial.SerialBlob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// for javadocs and report
// using code from https://www.javatpoint.com/java-sqlite

// using a singleton class to define the database since there won't be more than once
// instance of the database
/**
 * An instance of a database class which can upload and download generated mazes
 * @author Hudson
 */
public class Database {
    private static Database dbInstance = null;

    protected Database() {
        System.out.println("Initiated database instance");
    }

    public static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/SSSIT.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static Database getInstance() {
        if (dbInstance == null) dbInstance = new Database();

        return dbInstance;
    }

//    private SerialBlob generateThumbnail(Maze myMaze) {
//    }

    /**
     * Validates user input for sizing maze
     * @author Hudson
     * @param myMaze An instance of a maze object which to export to the database
     * @return The status/success code of the function
     */
    public statusCodes.dbStatus exportMaze(Maze myMaze) {
        String sql = "INSERT INTO mazes(Maze_Name, Creator, Last_Editor, Create_time, Edit_time, Logo_one, Logo_two, Thumbnail, Maze_Object) VALUES(?,?,?,?,?,?,?,?,?)";

        if (myMaze == null) return statusCodes.dbStatus.INVALID_ARGUMENTS;

        try{
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, myMaze.getMazeName());
            pstmt.setString(2, myMaze.getAuthor());
            pstmt.setString(3, "todo");
            pstmt.setString(4, myMaze.getDateCreated());
            pstmt.setString(5, myMaze.getDateEdited());
            pstmt.setBlob(6, new SerialBlob(myMaze.getStartImage()));
            pstmt.setBlob(7, new SerialBlob(myMaze.getEndImage()));
//            pstmt.setBlob(8, new SerialBlob()); todo
//            pstmt.setBlob(9, new SerialBlob( myMaze ));
            pstmt.executeUpdate();

            return statusCodes.dbStatus.OK;
        } catch (SQLException e) {
            return statusCodes.dbStatus.FAILED;
        }
    }

    /**
     * Loads a maze object from a DB record
     * @author Hudson
     * @param mazeName The maze name/unique indentifyer of the maze
     */
    public statusCodes.dbStatus loadMaze(String mazeName) {
        return statusCodes.dbStatus.FAILED;
    }
}
