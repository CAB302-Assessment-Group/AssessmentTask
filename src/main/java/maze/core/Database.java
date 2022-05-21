package src.main.java.maze.core;

import src.main.java.util.statusCodes;

// file reader
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// for javadocs and report
// using code from https://www.javatpoint.com/java-sqlite

// using a singleton class to define the database since there won't be more than once
// instance of the database
/**
 * An instance of a database class which can upload and download generated mazes
 * @author Hudson
 */
public class Database {
    private static Connection dbInstance = null;

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS address ("
                    + "idx INTEGER PRIMARY KEY /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE,"
                    + "name VARCHAR(30),"
                    + "creator VARCHAR(30),"
                    + "last_editor VARCHAR(20),"
                    + "create_timestamp VARCHAR(10),"
                    + "logoOne VARCHAR(30)" + ");";

    protected Database() {
        connect();
        System.out.println("Initiated database instance");
    }

    private statusCodes.dbStatus CreateSchema() {
        dbInstance = this.getInstance();

        Statement st = dbInstance.createStatement();
        st.execute(CREATE_TABLE);

        return statusCodes.dbStatus.OK;
    }

    public static void connect() {
        FileInputStream in = null;

        try {
            Class.forName("org.sqlite.JDBC");

            System.out.println("got here");
            Properties props = new Properties();

            in = new FileInputStream("./db.props");
            props.load(in);
            in.close();

            // specify the data source, username and password
            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");
            String schema = props.getProperty("jdbc.schema");

            // get a connection
            dbInstance = DriverManager.getConnection(url + "/" + schema, username, password);
        } catch (SQLException sqle) {
            System.err.println(sqle);
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Connection getInstance() {
        if (dbInstance == null) new Database();

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
        return statusCodes.dbStatus.FAILED;
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
