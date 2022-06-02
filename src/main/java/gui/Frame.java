package gui;


import maze.core.Database;
import maze.core.solver.Solver;
import org.junit.jupiter.api.Test;

import maze.core.Maze;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Frame {
    public int[] mazeSize = new int[2];
    public Maze myMaze = new Maze(new int[]{100, 100}); //init as adult maze
    public static JFrame window;
    public static JFrame window2;
    public static JFrame window3;

    public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;




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
                SearchResults(MazeNameInput.getText());
            }
        });
    }



    public void SearchResults(String query) {
        window3.setLayout(null);

        window3.getContentPane().removeAll();
        window3.getContentPane().repaint();

        // set frame location to center of screen
        window3.setLocation(screenWidth/2 -(500/2), screenHeight/16+300);
        window3.setSize(500, 300);

        window3.setVisible(true);

        Database db = new Database();
        db.getInstance();
        ArrayList<Maze> loadedMazes =  db.loadMaze(query);

        JLabel SearchResultsTitle = new JLabel("Search Results");
        SearchResultsTitle.setBounds((500/2) - (100/2),10,100,20);

        JTable SearchResultsTable = new JTable();
        SearchResultsTable.setBounds(50,30,400,120);

        // render the mazes that have been loaded from the database in a list
        for (int i = 0; i < loadedMazes.size(); i++) {
            Maze loadingMaze = loadedMazes.get(i);

            JPanel loadMazeContainer = new JPanel();
            loadMazeContainer.setBounds(0, 35 * i - 30, 400, 30);

            JButton loadBTN = new JButton("Load");
            loadBTN.setBounds(0, 0, 200, 30);

            JLabel loadMazeName = new JLabel("Maze Name: " + loadingMaze.getMazeName());
            loadMazeName.setBounds(200, 0, 100, 30);

            JLabel loadMazeAuthor = new JLabel("Author: " + loadingMaze.getAuthor());
            loadMazeAuthor.setBounds(300, 0, 200, 30);

            loadBTN.addActionListener(action -> {
                Render.renderMazeOBJ(loadingMaze, true);
                window2.setSize(850, 710);
            });

            loadMazeContainer.add(loadBTN);
            loadMazeContainer.add(loadMazeName);
            loadMazeContainer.add(loadMazeAuthor);

            SearchResultsTable.add(loadMazeContainer);
        }

        window3.add(SearchResultsTitle);
        window3.add(SearchResultsTable);
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
        window.setSize(330, 710);


        // maze needs to be drawn inside a pane for scrollbars to work and for other buttons to stay constant
        /*JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        window.setContentPane(pane);*/

        JLabel MazeName = new JLabel("Maze Name:");
        MazeName.setBounds(10,80,100,20);
        JTextArea MazeNameInput = new JTextArea();
        MazeNameInput.setBounds(160,80,100,20);

        JLabel MazeAuthor = new JLabel("Author:");
        MazeAuthor.setBounds(10, 110, 100,20);
        JTextArea MazeAuthorInput = new JTextArea();
        MazeAuthorInput.setBounds(160, 110, 100,20);

        JLabel MazeWidth = new JLabel("Width:");
        MazeWidth.setBounds(10,140,50,20);
        JTextArea MazeWidthInput = new JTextArea();
        MazeWidthInput.setBounds(160, 140, 30, 20);
        //take inputs from text box for width, "inputx.getText()"

        JLabel MazeHeight = new JLabel("Height:");
        MazeHeight.setBounds(10,170,50,20);
        JTextArea MazeHeightInput = new JTextArea();
        MazeHeightInput.setBounds(160, 170, 30, 20);
        //take inputs from text box for height, "inputy.getText()"

        JLabel LogoCellSizeLabel = new JLabel("Logo Cell Size:");
        LogoCellSizeLabel.setBounds(10,200,100,20);
        JTextArea LogoCellSizeInput = new JTextArea();
        LogoCellSizeInput.setBounds(160,200, 50,20);

        JRadioButton StandardMazeButton = new JRadioButton("Standard Maze");
        StandardMazeButton.setBounds(10, 230, 110, 20);
        StandardMazeButton.setActionCommand("Standard Maze Action");

        JRadioButton ChildrensMazeButton = new JRadioButton("Childrens Maze");
        ChildrensMazeButton.setBounds(160, 230, 120, 20);
        ChildrensMazeButton.setActionCommand("Childrens Maze Action");

        ButtonGroup MazeTypeSelection = new ButtonGroup();
        MazeTypeSelection.add(StandardMazeButton);
        MazeTypeSelection.add(ChildrensMazeButton);

        /* JRadioButton EasyDifficultyButton = new JRadioButton("Easy");
        EasyDifficultyButton.setActionCommand("Easy Difficulty Action");
        EasyDifficultyButton.setBounds(10,   260, 100, 20);

        JRadioButton MediumDifficultyButton = new JRadioButton("Medium");
        MediumDifficultyButton.setActionCommand("Medium Difficulty Action");
        MediumDifficultyButton.setBounds(110, 260, 100, 20);

        JRadioButton HardDifficultyButton = new JRadioButton("Hard");
        HardDifficultyButton.setActionCommand("Hard Difficulty Action");
        HardDifficultyButton.setBounds(210, 260, 100, 20);

        ButtonGroup DifficultySelection = new ButtonGroup();
        DifficultySelection.add(EasyDifficultyButton);
        DifficultySelection.add(MediumDifficultyButton);
        DifficultySelection.add(HardDifficultyButton);

        */


        JButton BackButton = new JButton("Back");
        BackButton.setBounds(10, 10, 75, 20);

        JButton SaveButton = new JButton("Export Maze");
        SaveButton.setBounds(100, 20, 150, 20);

        JButton ExportMazeButton = new JButton("Export Image");
        ExportMazeButton.setBounds(160, 40, 150, 30);

        JButton ImportStartingLogo = new JButton("Starting Logo");
        ImportStartingLogo.setBounds(10,290,150,20);

        JButton ImportFinishingLogo = new JButton("Finishing Logo");
        ImportFinishingLogo.setBounds( 10,390,150,20);

        JButton ImportStandardLogo = new JButton("Standard Logo");
        ImportStandardLogo.setBounds( 10,490,150,20);

        JButton GenerateBlankMazeButton = new JButton("Create Blank");
        GenerateBlankMazeButton.setBounds(10, 590, 150, 30);

        JButton AutoGenerateMazeButton = new JButton("Auto Generate");
        AutoGenerateMazeButton.setBounds(160,590,150,30);

        JButton SolveMazeButton = new JButton("Solve Maze");
        SolveMazeButton.setBounds(10, 630, 150, 30);

        JLabel ShowSolution = new JLabel("Show Solution");
        ShowSolution.setBounds(180,630,150,20);

        JCheckBox ShowSolutionCheckBox = new JCheckBox();
        ShowSolutionCheckBox.setBounds(270,630,20,20);

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

        window.add(MazeWidth);
        window.add(MazeWidthInput);
        window.add(MazeHeight);
        window.add(MazeHeightInput);

        window.add(LogoCellSizeLabel);
        window.add(LogoCellSizeInput);

        window.add(ChildrensMazeButton);
        window.add(StandardMazeButton);
        /*
        window.add(EasyDifficultyButton);
        window.add(MediumDifficultyButton);
        window.add(HardDifficultyButton);

        */


        window.add(MazeName);
        window.add(MazeNameInput);
        window.add(MazeAuthor);
        window.add(MazeAuthorInput);

        JLabel CellsVisited = new JLabel("Number of Cells Visited in Optimal Solution:");
        CellsVisited.setBounds(0, 0, 230, 40);

        JLabel DeadEnds = new JLabel("Number of Dead End Cells:");
        DeadEnds.setBounds(0, 60, 230, 40);


        JFrame MetricsWindow = new JFrame();
        MetricsWindow.setLayout(null);

        MetricsWindow.getContentPane().removeAll();
        MetricsWindow.getContentPane().repaint();

        MetricsWindow.setLocation((screenWidth / 6),730+screenHeight/16);
        MetricsWindow.setSize(330, 160);
        MetricsWindow.setName("Maze Metrics");

        MetricsWindow.add(CellsVisited);
        MetricsWindow.add(DeadEnds);

        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window2.setVisible(false);
                Frame.getInstance().MainMenu();

            }
        });


        SolveMazeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Solver mazeSolver = new Solver();

                Integer[] tempDFS = mazeSolver.DFS(Frame.getInstance().myMaze, new Integer[] {0,0});

                ArrayList<Integer[]> mazeSolution = mazeSolver.Solution();

                Render.drawSolution(mazeSolution);
            }
        });


		ExportMazeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save to database first

                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

                Frame.getInstance().myMaze.setAuthor(MazeAuthorInput.getText());
                Frame.getInstance().myMaze.setMazeName(MazeNameInput.getText());
                Frame.getInstance().myMaze.setDateCreated(timeStamp);
                Frame.getInstance().myMaze.setDateEdited(timeStamp);
                Frame.getInstance().myMaze.SetLastEditor(MazeAuthorInput.getText());
                Database.exportMaze(Frame.getInstance().myMaze);

                JFileChooser exportFile = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
                // From https://stackoverflow.com/questions/10621687/how-to-get-full-path-directory-from-file-chooser
                exportFile.setCurrentDirectory(new java.io.File("."));
                exportFile.setDialogTitle("Export Maze");
                exportFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                exportFile.setAcceptAllFileFilterUsed(false);
                String fileLocation ="";
                if (exportFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    fileLocation = exportFile.getCurrentDirectory().toString();
                } else {
                    System.out.println("No Selection ");
                }
                try {
                    if(MazeNameInput.getText()!="" ){
                        ExportImage(window2,fileLocation,Frame.getInstance().myMaze.getMazeName());
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

                Frame.getInstance().myMaze.setAuthor(MazeAuthorInput.getText());
                Frame.getInstance().myMaze.setMazeName(MazeNameInput.getText());
                Frame.getInstance().myMaze.setDateCreated(timeStamp);
                Frame.getInstance().myMaze.setDateEdited(timeStamp);
                Frame.getInstance().myMaze.SetLastEditor(MazeAuthorInput.getText());
                Database.exportMaze(Frame.getInstance().myMaze);
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
                window2.setSize(850, 710);



                boolean shouldAutoSolve = ShowSolutionCheckBox.isSelected();
                Render.setButtonPressed(MazeWidthInput.getText(),MazeHeightInput.getText(), LogoCellSizeInput.getText(),false, shouldAutoSolve);
                SetMetrics(MetricsWindow);
                window2.setVisible(true);
                MetricsWindow.setVisible(true);
            }
        });

        AutoGenerateMazeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window2.setLayout(null);


                window2.getContentPane().removeAll();
                window2.getContentPane().repaint();
                window2.setLocation((screenWidth / 2 - (850/2)),screenHeight/2 - 230);
                window2.setSize(850, 650);



                boolean shouldAutoSolve = ShowSolutionCheckBox.isSelected();
                Render.setButtonPressed(MazeWidthInput.getText(),MazeHeightInput.getText(), LogoCellSizeInput.getText(),true, shouldAutoSolve);

                window2.setVisible(true);
                SetMetrics(MetricsWindow);
                MetricsWindow.setVisible(true);
            }
        });


    }

    public static void SetMetrics(JFrame MetricsWindow){
        Solver solver = new Solver();
        //solver.DFS(Frame.getInstance().myMaze,new Integer[]{Frame.getInstance().myMaze.getStart()[0],Frame.getInstance().myMaze.getStart()[1]});


        JLabel CellsVisitedNum = new JLabel(solver.tilesVisited()+"");
        CellsVisitedNum.setBounds(250, 0, 230, 40);
        System.out.println(solver.tilesVisited()+"");

        JLabel DeadEndsNum = new JLabel(Frame.getInstance().myMaze.numDeadEnds()+"");
        DeadEndsNum.setBounds(250, 60, 230, 40);
        System.out.println(Frame.getInstance().myMaze.numDeadEnds()+"");
        MetricsWindow.add(CellsVisitedNum);

        MetricsWindow.add(DeadEndsNum);

        MetricsWindow.getContentPane().repaint();
    }

    /**
     * Obtained fromhttps://stackoverflow.com/questions/30335787/take-snapshot-of-full-jframe-and-jframe-only
     * Takes a picture output of the JFrame and converts to png format
     * @param mazeBox the JFrame to screenshot
     * @author Hudson
     * @throws IOException
     *
     */
    public static void ExportImage(JFrame mazeBox, String location, String name) throws IOException {
        //TODO
        //Save the maze to database and make sure all fields are matching object
        BufferedImage img = new BufferedImage(mazeBox.getWidth(), mazeBox.getHeight(), BufferedImage.TYPE_INT_RGB);
        mazeBox.paint(img.getGraphics());
        String path = location+"/"+name+".png";
        File outputfile = new File(path);
        ImageIO.write(img, "png", outputfile);
    }



}
