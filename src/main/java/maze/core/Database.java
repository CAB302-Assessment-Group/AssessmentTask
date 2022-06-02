package maze.core;

import gui.Frame;
import maze.core.util.statusCodes;

// file reader
import java.io.ByteArrayInputStream;
import java.sql.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
                    + "dateCreated VARCHAR(20),"
                    + "dateModified VARCHAR(20),"
                    + "maze_obj BLOB" + ");";

    public Database() {
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

    public static ArrayList<Maze> loadByQuery(String query) {
        ArrayList<Maze> myMazes = new ArrayList<Maze>();

        return new ArrayList<Maze>();
    }

    /**
     * Validates user input for sizing maze
     * @author Hudson
     * @param myMaze An instance of a maze object which to export to the database
     * @return The status/success code of the function
     */
    public static statusCodes.dbStatus exportMaze(Maze myMaze) {
        try {
            Connection myDBInstance = getInstance();

            // get the maze instance
            String sql = "INSERT INTO mazes(name,creator,dateCreated,dateModified,maze_obj) VALUES(?,?,?,?,?)";

            try {
                PreparedStatement pstmt = myDBInstance.prepareStatement(sql);
                pstmt.setString(1, myMaze.getMazeName());
                pstmt.setString(2, myMaze.getAuthor());
                pstmt.setString(3, myMaze.getDateCreated());
                pstmt.setString(4, myMaze.getDateEdited());

                byte[] myByteArray = util.serialize(myMaze);
                pstmt.setBinaryStream(5, new ByteArrayInputStream(myByteArray), myByteArray.length);
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
//    public Maze loadMaze(String mazeName) {
//        Connection myDBInstance = getInstance();
//        ResultSet rs = null;
//
//        String SQL = "SELECT * FROM mazes WHERE mazeName = '" + mazeName + "'";
//
//        try {
//            PreparedStatement SQLselection = myDBInstance.prepareStatement(SQL);
//            rs = SQLselection.executeQuery();
//            rs.next();
//            Blob blob = rs.getBlob("maze_obj");
//
//            int blobLength = (int) blob.length();
//            byte[] blobAsBytes = blob.getBytes(1, blobLength);
//
//            //release the blob and free up memory. (since JDBC 4.0)
//            blob.free();
//
//            return util.deserialize(blobAsBytes);
//        } catch (Exception e) {
//            return null;
//        }
//    }

    private String constructQuery(String mazeName, String author, String dateCreated, String dateModified) {
        String baseQuery = "SELECT * from mazes where ";

        String[] queryMatch = {"name", "creator", "dateCreated", "dateModified"};
        String[] queryElements = { mazeName, author, dateCreated, dateModified };

        int appendedNum = 0;
        for (int i = 0; i < queryElements.length; i++) {
            if (queryElements[i].isEmpty()) continue;

            if (appendedNum != 0) {
                baseQuery += " AND ";
            }

            baseQuery += queryMatch[i] + "= '" + queryElements[i] + "'";

            appendedNum++;
        }

        return baseQuery;
    }

    public ArrayList<Maze> loadMaze(String mazeName) { return loadMaze(mazeName, "", "", ""); }

    public ArrayList<Maze> loadMaze(String mazeName, String author, String dateCreated, String dateModified)
    {
        Connection connection = getInstance();

        ArrayList<Maze> mazeList = new ArrayList<Maze>();
        try
        {
            String query = constructQuery(mazeName, author, dateCreated, dateModified);
            PreparedStatement SQLselection = connection.prepareStatement(query);
//            ps.setString(1, mazeName);
            ResultSet rs = SQLselection.executeQuery();
            while(rs.next())
            {
                byte[] blobAsBytes = rs.getBytes(6);

                Maze myMaze = new Maze(new int[] { 100, 100 });
                myMaze = util.deserialize(blobAsBytes);
                mazeList.add(myMaze);

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return mazeList;
    }
}
