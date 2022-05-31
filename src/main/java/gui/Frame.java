package gui;

import org.junit.jupiter.api.Test;
import maze.core.Maze;
import maze.core.Tile;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import gui.Render;
public class Frame {
    public int[] mazeSize = new int[2];
    public Maze myMaze = new Maze(new int[]{100, 100}); //init as adult maze
    public static JFrame window;
    public static JFrame window2;
    public static JFrame window3;

    public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;



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


        window.setLayout(null);

        // make the program process close when the main window is closed
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);


        //Maze Generation window
        window2 = new JFrame("Maze Generation Window");

        /*//DON'T REMOVE!! THIS FIXES THE MAZE REDRAWING ISSUE WHEN YOU PRESS GENERATE BUTTON
        window2.setVisible(true);
        //DON'T REMOVE!! THIS FIXES THE MAZE REDRAWING ISSUE WHEN YOU PRESS GENERATE BUTTON*/

        window2.setVisible(false);


        window3 = new JFrame("Search Results");
        window3.setVisible(false);







        MainMenu();
        // initialise("","");

        // this is where "user customization" will come in

        // needs to be put into seperate "render" class



    }

    public void MainMenu(){
        window.getContentPane().removeAll();
        window.getContentPane().repaint();

        // set frame location to center of screen
        window.setLocation(screenWidth/2 -(250/2), screenHeight/16);
        window.setSize(250, 300);


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
            public void actionPerformed(ActionEvent e) { initialise();}
        });

        // on OpenExistingMaze button press open file chooser
        OpenExistingMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenExistingMaze();
            }
        });

        // on Exit button press close window
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

        // set frame location to center of screen
        window.setLocation(screenWidth/2 -(500/2), screenHeight/16);
        window.setSize(500, 300);


        JLabel SearchForMazeTitle = new JLabel("Search for a Maze");
        SearchForMazeTitle.setBounds((500/2)-(105/2),10,105,20);

        JLabel MazeName = new JLabel("Name:");
        MazeName.setBounds(50,50,150,20);
        JTextArea MazeNameInput = new JTextArea();
        MazeNameInput.setBounds(150,50, 150,20);

        JLabel DateCreated = new JLabel("Date Created:");
        DateCreated.setBounds(50,80,150,20);
        JTextArea DateCreatedInput = new JTextArea();
        DateCreatedInput.setBounds(150, 80, 150, 20);

        JLabel DateModified = new JLabel("Date Modified:");
        DateModified.setBounds(50,110,150,20);
        JTextArea DateModifiedInput = new JTextArea();
        DateModifiedInput.setBounds(150, 110, 150,20);

        JLabel Author = new JLabel("Author:");
        Author.setBounds(50,140,150,20);
        JTextArea AuthorInput = new JTextArea();
        AuthorInput.setBounds(150,140, 150,20);

        JButton Search = new JButton("Search");
        Search.setBounds(50, 170, 75, 20);

        JButton Back = new JButton(("Back"));
        Back.setBounds(10,10,75,20);

        window.add(Back);
        window.add(SearchForMazeTitle);
        window.add(MazeName);
        window.add(MazeNameInput);
        window.add(DateCreated);
        window.add(DateCreatedInput);
        window.add(DateModified);
        window.add(DateModifiedInput);
        window.add(Author);
        window.add(AuthorInput);
        window.add(Search);

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window3.setVisible(false);
                MainMenu();
            }
        });

        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchResults();
            }
        });
    }



    public void SearchResults() {
        window3.setLayout(null);

        window3.getContentPane().removeAll();
        window3.getContentPane().repaint();

        // set frame location to center of screen
        window3.setLocation(screenWidth/2 -(500/2), screenHeight/16+300);
        window3.setSize(500, 300);

        window3.setVisible(true);

        JLabel SearchResultsTitle = new JLabel("Search Results");
        SearchResultsTitle.setBounds((500/2) - (100/2),10,100,20);

        JTable SearchResultsTable = new JTable();
        SearchResultsTable.setBounds((500/2) - (200/2),60,200,60);

        JButton OpenSelectedMazesButton = new JButton("Open");
        OpenSelectedMazesButton.setBounds((500/2) - (80/2), 200, 80, 20);






        window3.add(SearchResultsTitle);
        window3.add(SearchResultsTable);
        window3.add(OpenSelectedMazesButton);


        OpenSelectedMazesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialise();
                window3.setVisible(false);
            }
        });

    }









    /**
     * Intialises the new blank maze GUI
     *
     */
    public static void initialise(){
        window.setLayout(null);

        window.getContentPane().removeAll();
        window.getContentPane().repaint();

        window.setLocation((screenWidth / 6),screenHeight/16);
        window.setSize(330, 700);


        // maze needs to be drawn inside a pane for scrollbars to work and for other buttons to stay constant
        /*JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        window.setContentPane(pane);*/

        JLabel Author = new JLabel("Author:");
        Author.setBounds(10, 80, 100,20);
        JTextArea AuthorInput = new JTextArea();
        AuthorInput.setBounds(160, 80, 100,20);

        JLabel labelx = new JLabel("Width:");
        labelx.setBounds(10,110,50,20);

        JTextArea inputx = new JTextArea();
        inputx.setBounds(160, 110, 30, 20);
        //take inputs from text box for width, "inputx.getText()"

        JLabel labely = new JLabel("Height:");
        labely.setBounds(10,140,50,20);

        JTextArea inputy = new JTextArea();
        inputy.setBounds(160, 140, 30, 20);
        //take inputs from text box for height, "inputy.getText()"

        JLabel LogoCellSizeLabel = new JLabel("Logo Cell Size:");
        LogoCellSizeLabel.setBounds(10,170,100,20);

        JTextArea LogoCellSizeInput = new JTextArea();
        LogoCellSizeInput.setBounds(160,170, 50,20);

        JRadioButton StandardMazeButton = new JRadioButton("Standard Maze");
        StandardMazeButton.setBounds(10, 200, 110, 20);
        StandardMazeButton.setActionCommand("Standard Maze Action");

        JRadioButton ChildrensMazeButton = new JRadioButton("Childrens Maze");
        ChildrensMazeButton.setBounds(160, 200, 120, 20);
        ChildrensMazeButton.setActionCommand("Childrens Maze Action");

        ButtonGroup MazeTypeSelection = new ButtonGroup();
        MazeTypeSelection.add(StandardMazeButton);
        MazeTypeSelection.add(ChildrensMazeButton);

        JRadioButton EasyDifficultyButton = new JRadioButton("Easy");
        EasyDifficultyButton.setActionCommand("Easy Difficulty Action");
        EasyDifficultyButton.setBounds(10,   230, 100, 20);

        JRadioButton MediumDifficultyButton = new JRadioButton("Medium");
        MediumDifficultyButton.setActionCommand("Medium Difficulty Action");
        MediumDifficultyButton.setBounds(110, 230, 100, 20);

        JRadioButton HardDifficultyButton = new JRadioButton("Hard");
        HardDifficultyButton.setActionCommand("Hard Difficulty Action");
        HardDifficultyButton.setBounds(210, 230, 100, 20);

        ButtonGroup DifficultySelection = new ButtonGroup();
        DifficultySelection.add(EasyDifficultyButton);
        DifficultySelection.add(MediumDifficultyButton);
        DifficultySelection.add(HardDifficultyButton);


        JButton BackButton = new JButton("Back");
        BackButton.setBounds(10, 10, 75, 20);

        JButton SaveButton = new JButton("Save");
        SaveButton.setBounds(10, 40, 150, 30);

        JButton ExportMazeButton = new JButton("Export Image");
        ExportMazeButton.setBounds(160, 40, 150, 30);

        JButton ImportStartingLogo = new JButton("Starting Logo");
        ImportStartingLogo.setBounds(10,260,150,20);

        JButton ImportFinishingLogo = new JButton("Finishing Logo");
        ImportFinishingLogo.setBounds( 10,360,150,20);

        JButton ImportStandardLogo = new JButton("Standard Logo");
        ImportStandardLogo.setBounds( 10,460,150,20);

        JButton GenerateBlankMazeButton = new JButton("Create Blank");
        GenerateBlankMazeButton.setBounds(10, 560, 150, 30);

        JButton AutoGenerateMazeButton = new JButton("Auto Generate");
        AutoGenerateMazeButton.setBounds(160,560,150,30);

        JButton SolveMazeButton = new JButton("Solve Maze");
        SolveMazeButton.setBounds(10, 600, 150, 30);

        JLabel ShowSolution = new JLabel("Show Solution");
        ShowSolution.setBounds(180,610,150,20);

        JCheckBox ShowSolutionCheckBox = new JCheckBox();
        ShowSolutionCheckBox.setBounds(270,610,20,20);



        window.add(BackButton);
        window.add(SaveButton);
        window.add(ExportMazeButton);
        window.add(AutoGenerateMazeButton);
        window.add(GenerateBlankMazeButton);
        window.add(SolveMazeButton);
        window.add(ShowSolution);
        window.add(ShowSolutionCheckBox);

        window.add(ImportStartingLogo);
        window.add(ImportFinishingLogo);
        window.add(ImportStandardLogo);

        window.add(labelx);
        window.add(inputx);
        window.add(labely);
        window.add(inputy);

        window.add(LogoCellSizeLabel);
        window.add(LogoCellSizeInput);

        window.add(ChildrensMazeButton);
        window.add(StandardMazeButton);

        window.add(EasyDifficultyButton);
        window.add(MediumDifficultyButton);
        window.add(HardDifficultyButton);

        window.add(Author);
        window.add(AuthorInput);


        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window2.setVisible(false);
                Frame.getInstance().MainMenu();

            }
        });

        ImportStartingLogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser importFile = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
                importFile.showOpenDialog(null);
            }
        });

        ImportFinishingLogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser importFile = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
                importFile.showOpenDialog(null);
            }
        });

        GenerateBlankMazeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //creates the maze based on width and length inputs
                //take inputs from text box, "inputx.getText()"

                //DON'T REMOVE, FIX FOR MAZE DRAWING ISSUE WHEN WINDOW IS RESIZED
                window2.setLayout(null);


                window2.getContentPane().removeAll();
                window2.getContentPane().repaint();
                window2.setLocation((screenWidth /6 + 330),screenHeight/16);
                window2.setSize(850, 700);

                window2.setVisible(true);


                Render.setButtonPressed(inputx.getText(),inputy.getText());
            }
        });



    }
}
