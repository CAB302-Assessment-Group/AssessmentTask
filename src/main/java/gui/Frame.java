package src.main.java.gui;

import org.junit.jupiter.api.Test;
import src.main.java.maze.core.Maze;
import src.main.java.maze.core.Tile;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static org.junit.jupiter.api.Assertions.*;
import src.main.java.gui.Render;
public class Frame {
    public int[] mazeSize = new int[2];
    public Maze myMaze = new Maze(new int[]{100, 100}, false); //init as adult maze
    public JFrame window;

    public int WELCOMEFRAME = 1;
    public int MAZETYPEFRAME = 2;
    public int SIZEFRAME = 3;
    public int ERRORFRAME = 4;
    public int EDITFRAME = 5;
    public int MAZECREATEFRAME = 6;


    private static class FrameHolder{
        private final static Frame INSTANCE = new Frame();
    }

    /**
     * getInstance enables the use of a Singleton class
     * @author JackFFFFFF
     * @return the current and ONLY instance of this class
     */
    public static Frame getInstance(){
        return FrameHolder.INSTANCE;
    }



    protected Frame() {
        super();
        // initialize swing window
        window = new JFrame("MazeCo Maze Gen Tool");
        window.setSize(850, 750);

        // set frame location to center of screen
        window.setLocationRelativeTo(null);

        /*// menu bar
        JMenuBar menubar = new JMenuBar();
        JMenu fileMenuItem = new JMenu("File");

        // sub menus
        JMenuItem quitMenuItem = new JMenuItem("Quit");

        fileMenuItem.add(quitMenuItem);

        JMenu editMenuItem = new JMenu("Edit");

        // sub menus
        JMenuItem undoMenuItem = new JMenuItem("Undo");
        JMenuItem redoMenuItem = new JMenuItem("Redo");

        editMenuItem.add(undoMenuItem);
        editMenuItem.add(redoMenuItem);

        JMenu mazeMenuItem = new JMenu("My Maze");

        // sub menus
        JMenuItem exportMenuItem = new JMenuItem("Export Maze");
        JMenuItem importMenuItem = new JMenuItem("Export Maze");

        mazeMenuItem.add(exportMenuItem);
        mazeMenuItem.add(importMenuItem);

        menubar.add(fileMenuItem);
        menubar.add(editMenuItem);
        menubar.add(mazeMenuItem);

        window.setJMenuBar(menubar);*/

        window.setLayout(null);


        // make the program process close when the main window is closed
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MainMenu();
        // initialise("","");

        // this is where "user customization" will come in

        // needs to be put into seperate "render" class


        window.setVisible(true);
    }

    public void MainMenu(){
        window.getContentPane().removeAll();
        window.getContentPane().repaint();

        JLabel Title = new JLabel("MazeCo Maze Gen Tool");
        Title.setBounds(10,10,200,20);

        JButton CreateNewMaze = new JButton("Create New Maze");
        CreateNewMaze.setBounds(50, 50, 150, 20);

        JButton OpenExistingMaze = new JButton("Open Existing Maze");
        OpenExistingMaze.setBounds(50, 100, 150, 20);

        JButton Exit = new JButton(("Exit"));
        Exit.setBounds(50,150,150,20);

        window.add(Title);
        window.add(CreateNewMaze);
        window.add(OpenExistingMaze);
        window.add(Exit);

        // on CreateNewMaze button press move to SelectOption Screen
        CreateNewMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { Render.initialise("","");}
        });

        // on OpenExistingMaze button press open file chooser
        OpenExistingMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenExistingMaze();
            }
        });

        // on Exist button press close window
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


    public void OpenExistingMaze(){
        window.getContentPane().removeAll();
        window.getContentPane().repaint();

        JLabel OpenExistingMaze = new JLabel("Search for a Maze to edit");
        OpenExistingMaze.setBounds(10,10,200,20);

        JLabel SearchByName = new JLabel("Search By: Name");
        SearchByName.setBounds(50,50,150,20);
        JCheckBox CheckSearchByName = new JCheckBox();
        CheckSearchByName.setBounds(150,50,20,20);

        JLabel DateTime = new JLabel("Date/Time");
        DateTime.setBounds(200,50,100,20);
        JCheckBox CheckDateTime = new JCheckBox();
        CheckDateTime.setBounds(275,50,20,20);

        JLabel CreatedBy = new JLabel("Created By");
        CreatedBy.setBounds(325,50,100,20);
        JCheckBox CheckCreatedBy = new JCheckBox();
        CheckCreatedBy.setBounds(425,50,20,20);


        JLabel NameOfMaze = new JLabel("Name of Maze");
        NameOfMaze.setBounds(50,100,200,20);
        JTextArea TextNameOfMaze = new JTextArea();
        TextNameOfMaze.setBounds(150,100,300,20);

        JLabel CreatedByMenu = new JLabel("Created By:");
        CreatedByMenu.setBounds(50,150,100,20);
        JComboBox<String> CreatedByDrop = new JComboBox<>();
        CreatedByDrop.setBounds(150,150,300,20);

        JLabel AccessedCreatedDate = new JLabel("Accessed/Created Date");
        AccessedCreatedDate.setBounds(50,200,200,20);
        JTextArea TextAccessedCreatedDate = new JTextArea();
        TextAccessedCreatedDate.setBounds(200,200,300,20);


        JButton Search = new JButton("Search");
        Search.setBounds(50, 250, 150, 20);

        JLabel Results = new JLabel("Results");
        Results.setBounds(50,300,100,20);
        JComboBox<String> ResultsDrop = new JComboBox<>();
        ResultsDrop.setBounds(50,350,300,20);


        JButton SelectMaze = new JButton(("SelectMaze"));
        SelectMaze.setBounds(50,400,150,20);

        JButton Back = new JButton(("Back"));
        Back.setBounds(50,450,150,20);

        window.add(OpenExistingMaze);
        window.add(SearchByName);
        window.add(CheckSearchByName);
        window.add(DateTime);
        window.add(CheckDateTime);
        window.add(CreatedBy);
        window.add(CheckCreatedBy);
        window.add(NameOfMaze);
        window.add(TextNameOfMaze);
        window.add(CreatedByMenu);
        window.add(CreatedByDrop);
        window.add(AccessedCreatedDate);
        window.add(TextAccessedCreatedDate);
        window.add(Search);
        window.add(Results);
        window.add(ResultsDrop);
        window.add(SelectMaze);
        window.add(Back);

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu();
            }
        });
    }





}
