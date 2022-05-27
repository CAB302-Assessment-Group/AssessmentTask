package maze.core;

import maze.core.util.statusCodes;

// file reader
import java.sql.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
            "CREATE TABLE IF NOT EXISTS mazes ("
                    + "idx INTEGER PRIMARY KEY /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE,"
                    + "name VARCHAR(30),"
                    + "creator VARCHAR(30),"
                    + "last_editor VARCHAR(20),"
                    + "create_timestamp VARCHAR(10),"
                    + "maze_obj BLOB" + ");";

    protected Database() {
        connect();
        System.out.println("Initiated database instance");
        try {
            statusCodes.dbStatus _ = CreateSchema();
        } catch(Exception e) {}
    }

    private statusCodes.dbStatus CreateSchema() throws SQLException {
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

    /**
     * Validates user input for sizing maze
     * @author Hudson
     * @param myMaze An instance of a maze object which to export to the database
     * @return The status/success code of the function
     */
    public statusCodes.dbStatus exportMaze(Maze myMaze) {
        try {
            Connection myDBInstance = getInstance();

            String sql = "INSERT INTO mazes(name,creator,last_editor,create_timestamp,maze_obj) VALUES(?,?,?,?,?)";

            try {
                PreparedStatement pstmt = myDBInstance.prepareStatement(sql);
                pstmt.setString(1, myMaze.getMazeName());
                pstmt.setString(2, myMaze.getAuthor());
                pstmt.setString(3, myMaze.GetLastEditor());
                pstmt.setString(4, myMaze.getDateCreated());
                pstmt.setBlob(5, util.serialize(myMaze));
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return statusCodes.dbStatus.INVALID_ARGUMENTS;
            }

            return statusCodes.dbStatus.OK;
        } catch(Exception e) {
            return statusCodes.dbStatus.FAILED;
        }
    }

    /**
     * Loads a maze object from a DB record
     * @author Hudson
     * @param mazeName The maze name/unique identifier of the maze
     */
    public Maze loadMaze(String mazeName) {
        Connection myDBInstance = getInstance();
        ResultSet rs = null;

        String SQL = "SELECT * FROM mazes WHERE mazeName = '" + mazeName + "'";

        try {
            PreparedStatement SQLselection = myDBInstance.prepareStatement(SQL);
            rs = SQLselection.executeQuery();
            rs.next();
            Blob blob = rs.getBlob("maze_obj");

            int blobLength = (int) blob.length();
            byte[] blobAsBytes = blob.getBytes(1, blobLength);

            //release the blob and free up memory. (since JDBC 4.0)
            blob.free();

            return util.deserialize(blobAsBytes);
        } catch (Exception e) {
            return null;
        }
    }
}
