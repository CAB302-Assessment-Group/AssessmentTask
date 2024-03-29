package gui;


import maze.core.Database;
import maze.core.solver.Solver;

import maze.core.Maze;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import maze.core.image.ImageProcessing;

public class Frame {
    public int[] mazeSize = new int[2];
    public Maze myMaze = new Maze(new int[]{100, 100}); //init as adult maze
    public static JFrame window;
    public static JFrame window2;
    public static JFrame window3;
    public static JFrame MetricsWindow;
    public static Solver solver = new Solver();
    public static BufferedImage Startlogo = null;
    public static BufferedImage Endlogo = null;
    public static BufferedImage Centerlogo = null;
    public static boolean childMaze = false;
    public static String logoCellSize;

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
            public void actionPerformed(ActionEvent e) { initialise(); }
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
        JTextField MazeNameInput = new JTextField();
        MazeNameInput.setBounds(150,50, 150,20);

        JLabel DateCreated = new JLabel("Date Created:");
        DateCreated.setBounds(50,80,150,20);
        JTextField DateCreatedInput = new JTextField();
        DateCreatedInput.setBounds(150, 80, 150, 20);

        JLabel DateModified = new JLabel("Date Modified:");
        DateModified.setBounds(50,110,150,20);
        JTextField DateModifiedInput = new JTextField();
        DateModifiedInput.setBounds(150, 110, 150,20);

        JLabel Author = new JLabel("Author:");
        Author.setBounds(50,140,150,20);
        JTextField AuthorInput = new JTextField();
        AuthorInput.setBounds(150,140, 150,20);

        JButton Search = new JButton("Search");
        Search.setBounds(50, 170, 75, 20);

        JButton Back = new JButton(("Back"));
        Back.setBounds(10,10,75,20);

        JLabel orderByLBL = new JLabel("Sort By");
        orderByLBL.setBounds(350, 0, 100, 30);

        String sortByOPT[] = {"name", "creator", "dateCreated", "dateModified"};
        JComboBox sortByPickerCMBO = new JComboBox(sortByOPT);
        sortByPickerCMBO.setBounds(350, 30, 100, 20);

        window.add(sortByPickerCMBO);
        window.add(orderByLBL);

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
                SearchResults(
                        MazeNameInput.getText(),
                        AuthorInput.getText(),
                        DateCreatedInput.getText(),
                        DateModifiedInput.getText(),
                        sortByPickerCMBO.getSelectedItem().toString()
                );
            }
        });
    }


    /**
     * Sets up the window for importing mazes from the SQLite db
     * @param mazeName Name of maze loaded
     * @param author Author of maze to load
     * @param dateCreated
     * @param dateModified
     * @author Vim and Hudson
     */
    public void SearchResults(String mazeName, String author, String dateCreated, String dateModified, String sortBy) {
        window3.setLayout(null);

        window3.getContentPane().removeAll();
        window3.getContentPane().repaint();

        // set frame location to center of screen
        window3.setLocation(screenWidth/2 -(700/2), screenHeight/16+300);
        window3.setSize(700, 300);

        window3.setVisible(true);

        Database db = new Database();
        db.getInstance();
        ArrayList<Maze> loadedMazes =  db.loadMaze(mazeName, author, dateCreated, dateModified, sortBy);

        JLabel SearchResultsTitle = new JLabel("Search Results");
        SearchResultsTitle.setBounds((500/2) - (100/2),10,100,20);

        JTable SearchResultsTable = new JTable();
        SearchResultsTable.setBounds(50,30,400,loadedMazes.size() * 30);

        // render the mazes that have been loaded from the database in a list
        for (int i = 0; i < loadedMazes.size(); i++) {
            Maze loadingMaze = loadedMazes.get(i);

            System.out.println(loadingMaze.getMazeName());

            JButton loadBTN = new JButton("Load");
            loadBTN.setBounds(0, i * 30, 100, 30);

            JLabel loadMazeName = new JLabel(loadingMaze.getMazeName());
            loadMazeName.setBounds(100, i * 30, 100, 30);

            JLabel loadMazeAuthor = new JLabel(loadingMaze.getAuthor());
            loadMazeAuthor.setBounds(200, i * 30, 100, 30);

            JLabel loadMazeDateCreated = new JLabel(loadingMaze.getDateCreated());
            loadMazeDateCreated.setBounds(300, i * 30, 200, 30);

            JLabel loadMazeDateModified = new JLabel(loadingMaze.getDateEdited());
            loadMazeDateModified.setBounds(500, i * 30, 200, 30);


            loadBTN.addActionListener(action -> {
//                try {
//                    Endlogo = ImageProcessing.fromByteArray(loadingMaze.mazeTile(loadingMaze.mazeSize()[0], loadingMaze.mazeSize()[0]).getImage());
//                    Startlogo = ImageProcessing.fromByteArray(loadingMaze.mazeTile(0,0).getImage());
//                    Centerlogo = ImageProcessing.fromByteArray(loadingMaze.mazeTile(loadingMaze.getLogoTopCorner()[0],loadingMaze.getLogoTopCorner()[0]).getImage());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                window3.setVisible(false);
                window2.setLayout(null);
                window2.setLocation((screenWidth /6 + 330),screenHeight/16);
                Frame.initialise();
                Frame.getInstance().myMaze = loadingMaze;

                try {

                    Centerlogo = ImageProcessing.fromByteArray(loadingMaze.mazeTile(loadingMaze.getLogoTopCorner()[0],loadingMaze.getLogoTopCorner()[1]).getImage());
                    Startlogo = ImageProcessing.fromByteArray(loadingMaze.mazeTile(0,0).getImage());
                    Endlogo = ImageProcessing.fromByteArray(loadingMaze.mazeTile(mazeSize[0]-1,mazeSize[1]-1 ).getImage());
                    System.out.println("Restored images");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SwingUtilities.updateComponentTreeUI(window2);
                Render.renderMazeOBJ(loadingMaze, true);

            });

            SearchResultsTable.add(loadBTN);
            SearchResultsTable.add(loadMazeName);
            SearchResultsTable.add(loadMazeAuthor);
            SearchResultsTable.add(loadMazeDateCreated);
            SearchResultsTable.add(loadMazeDateModified);
        }
        //window3.add(SearchResultsTable);
        window3.setTitle("Search Results");
        JLabel MazeName = new JLabel("Name");
        MazeName.setBounds(110,10,100,20);

        JLabel Author = new JLabel("Author");
        Author.setBounds(210,10,100,20);

        JLabel DateCreated = new JLabel("Date Created");
        DateCreated.setBounds(310,10,100,20);

        JLabel DateModified = new JLabel("Date Modified");
        DateModified.setBounds(510,10,100,20);


        JPanel SearchPanel = new JPanel();
        SearchPanel.setLayout(new BorderLayout());
        JScrollPane jsp = new JScrollPane(SearchPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setBounds(10,40,window3.getWidth()-30,window3.getHeight()-80);

        SearchPanel.add(SearchResultsTable);

        SearchPanel.setPreferredSize(new Dimension(window3.getWidth() - 30, loadedMazes.size() * 30));

        window3.add(MazeName);
        window3.add(Author);
        window3.add(DateCreated);
        window3.add(DateModified);
        window3.add(jsp);
        //window3.setVisible(false);
        window3.setVisible(true);

        //window3.add(SearchResultsTable);

        //window3.setVisible(false);
        //window3.setVisible(true);
    }









    /**
     * Intialises the new blank maze GUI
     * @author Vim
     */
    public static void initialise(){
        window.setLayout(null);

        window.getContentPane().removeAll();
        window.getContentPane().repaint();

        window.setLocation((screenWidth / 6),screenHeight/16);
        window.setSize(330, 720);


        // maze needs to be drawn inside a pane for scrollbars to work and for other buttons to stay constant
        /*JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        window.setContentPane(pane);*/

        JLabel MazeName = new JLabel("Maze Name:");
        MazeName.setBounds(10,80,100,20);
        JTextField MazeNameInput = new JTextField();
        MazeNameInput.setBounds(160,80,100,20);

        JLabel MazeAuthor = new JLabel("Author:");
        MazeAuthor.setBounds(10, 110, 100,20);
        JTextField MazeAuthorInput = new JTextField();
        MazeAuthorInput.setBounds(160, 110, 100,20);

        JLabel MazeWidth = new JLabel("Width:");
        MazeWidth.setBounds(10,140,50,20);
        JTextField MazeWidthInput = new JTextField();
        MazeWidthInput.setBounds(160, 140, 30, 20);
        //take inputs from text box for width, "inputx.getText()"

        JLabel MazeHeight = new JLabel("Height:");
        MazeHeight.setBounds(10,170,50,20);
        JTextField MazeHeightInput = new JTextField();
        MazeHeightInput.setBounds(160, 170, 30, 20);
        //take inputs from text box for height, "inputy.getText()"

        JLabel LogoCellSizeLabel = new JLabel("Logo Cell Size:");
        LogoCellSizeLabel.setBounds(10,200,100,20);
        JTextField LogoCellSizeInput = new JTextField();
        LogoCellSizeInput.setBounds(160,200, 30,20);

        JRadioButton StandardMazeButton = new JRadioButton("Standard Maze");
        StandardMazeButton.setBounds(10, 230, 110, 20);
        StandardMazeButton.setActionCommand("Standard Maze Action");


        JRadioButton ChildrensMazeButton = new JRadioButton("Childrens Maze");
        ChildrensMazeButton.setBounds(160, 230, 120, 20);
        ChildrensMazeButton.setActionCommand("Childrens Maze Action");

        ButtonGroup MazeTypeSelection = new ButtonGroup();

        MazeTypeSelection.add(StandardMazeButton);
        MazeTypeSelection.add(ChildrensMazeButton);
        StandardMazeButton.doClick();




        JButton BackButton = new JButton("Back");
        BackButton.setBounds(10, 10, 75, 20);

        JButton SaveButton = new JButton("Export Maze");
        SaveButton.setBounds(10, 40, 150, 30);

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

        JLabel autoSolveLBL = new JLabel("Automatically Solve");
        autoSolveLBL.setBounds(170,630,140,20);

        JCheckBox autoSolveCHKBOX = new JCheckBox();
        autoSolveCHKBOX.setBounds(290,630,20,20);

        JLabel showSolutionLBL = new JLabel("Show Solution");
        showSolutionLBL.setBounds(170,650,140,20);

        JCheckBox showSolutionCHKBOX = new JCheckBox();
        showSolutionCHKBOX.setBounds(290,650,20,20);
        showSolutionCHKBOX.setSelected(true);

        JButton clearAllIm = new JButton("Clear All Images");
        clearAllIm.setBounds( 160,490,150,20);

        window.add(showSolutionLBL);
        window.add(showSolutionCHKBOX);

        window.add(BackButton);
        window.add(SaveButton);
        window.add(ExportMazeButton);
        window.add(AutoGenerateMazeButton);
        window.add(GenerateBlankMazeButton);
        window.add(SolveMazeButton);
        window.add(autoSolveLBL);
        window.add(autoSolveCHKBOX);

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
        window.add(clearAllIm);
        /*
        window.add(EasyDifficultyButton);
        window.add(MediumDifficultyButton);
        window.add(HardDifficultyButton);

        */


        window.add(MazeName);
        window.add(MazeNameInput);
        window.add(MazeAuthor);
        window.add(MazeAuthorInput);




        MetricsWindow = new JFrame();
        MetricsWindow.setLayout(null);

        MetricsWindow.getContentPane().removeAll();
        MetricsWindow.getContentPane().repaint();

        MetricsWindow.setLocation((screenWidth / 6),730+screenHeight/16);
        MetricsWindow.setSize(330, 160);
        MetricsWindow.setName("Maze Metrics");


        ChildrensMazeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                childMaze = ChildrensMazeButton.isSelected();

            }
        });
        StandardMazeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                childMaze = !StandardMazeButton.isSelected();

            }
        });

        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window2.setVisible(false);
                MetricsWindow.setVisible(false);
                Frame.getInstance().MainMenu();

            }
        });

        SolveMazeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveMyMaze();
            }
        });

        autoSolveCHKBOX.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                Render.autoSolveMaze = autoSolveCHKBOX.isSelected();
            }
        });

        showSolutionCHKBOX.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) { Render.toggleSolutionVisualisation( showSolutionCHKBOX.isSelected() ); }
        });


		ExportMazeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Save to database first
                if(Util.UpdateMazeFromInput(MazeAuthorInput.getText(),MazeNameInput.getText())){


                    Solver solver2 = new Solver();
                    try{
                        solver2.DFS(Frame.getInstance().myMaze,
                                new Integer[]{Frame.getInstance().myMaze.getStart()[0],
                                        Frame.getInstance().myMaze.getStart()[1]});
                    }catch(Exception solverError){
                        solver2.DFS(Frame.getInstance().myMaze,new Integer[]{0,0});
                    }

                    if(solver2.tilesVisited()>0) {
                        JFileChooser exportFile = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
                        // From https://stackoverflow.com/questions/10621687/how-to-get-full-path-directory-from-file-chooser
                        exportFile.setCurrentDirectory(new java.io.File("."));
                        exportFile.setDialogTitle("Export Maze");
                        exportFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        exportFile.setAcceptAllFileFilterUsed(false);


                        String fileLocation ="";
                        if (exportFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                            fileLocation = exportFile.getSelectedFile().getAbsolutePath();
                        } else {
                            System.out.println("No Selection ");
                            PopUp popUp = new PopUp("Please select a location");
                        }
                        try {
                            if(MazeNameInput.getText()!=""){
                                if(showSolutionCHKBOX.isSelected() && autoSolveCHKBOX.isSelected()){
                                    //Generate solution + unsolved maze
                                    ImageProcessing.ExportImage(window2,fileLocation,
                                            Frame.getInstance().myMaze.getMazeName()+"Solution");
                                    showSolutionCHKBOX.doClick();
                                    ImageProcessing.ExportImage(window2,fileLocation,Frame.getInstance().myMaze.getMazeName());
                                    showSolutionCHKBOX.doClick();
                                    Frame.solveMyMaze();
                                } else {
                                    //Generate just unsolved maze
                                    ImageProcessing.ExportImage(window2,fileLocation,Frame.getInstance().myMaze.getMazeName());
                                }
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        System.out.println("Maze is not solvable");
                        PopUp popUp = new PopUp("Maze is not solvable");
                    }
                } else {
                    PopUp popUp = new PopUp("Could not export");
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

                SaveButton.setText("Export Complete!");
                SaveButton.setBackground(Color.green);

                new Thread(() -> {
                    try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    SaveButton.setText("Export Maze");
                    SaveButton.setBackground(null);
                }).start();
            }
        });

        ImportStandardLogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser importFile = new JFileChooser(FileSystemView.getFileSystemView());
                importFile.setFileSelectionMode(JFileChooser.FILES_ONLY);


                if (importFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = new File(importFile.getSelectedFile().toString());
                    try {
                        Centerlogo = ImageProcessing.GetLogo(file);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }



            }
        });

        ImportStartingLogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser importFile = new JFileChooser(FileSystemView.getFileSystemView());
                importFile.setFileSelectionMode(JFileChooser.FILES_ONLY);


                if (importFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = new File(importFile.getSelectedFile().toString());
                    try {
                        Startlogo = ImageProcessing.GetLogo(file);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }



            }
        });

        ImportFinishingLogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser importFile = new JFileChooser(FileSystemView.getFileSystemView());
                importFile.setFileSelectionMode(JFileChooser.FILES_ONLY);


                if (importFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = new File(importFile.getSelectedFile().toString());
                    try {
                        Endlogo = ImageProcessing.GetLogo(file);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        clearAllIm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Startlogo = null;
                Endlogo = null;
                Centerlogo = null;
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
                window2.setSize(800, 800);

                logoCellSize = LogoCellSizeInput.getText();

                boolean shouldAutoSolve = autoSolveCHKBOX.isSelected();
                Render.autoSolveMaze = shouldAutoSolve;

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
                window2.setLocation((screenWidth /6 + 330),screenHeight/16);
                window2.setSize(1600, 1600);



                boolean shouldAutoSolve = autoSolveCHKBOX.isSelected();
                Render.autoSolveMaze = shouldAutoSolve;
                logoCellSize = LogoCellSizeInput.getText();
                Render.setButtonPressed(MazeWidthInput.getText(),MazeHeightInput.getText(), LogoCellSizeInput.getText(),true, shouldAutoSolve);
                SetMetrics(MetricsWindow);
                try {
                    if(solver.tilesVisited()<=0){
                        int count = 0;
                        while(solver.tilesVisited()<=0 && count<5){
                            Render.setButtonPressed(MazeWidthInput.getText(),MazeHeightInput.getText(), LogoCellSizeInput.getText(),true, shouldAutoSolve);
                            SetMetrics(MetricsWindow);
                            count++;
                        }
                    }
                } catch (Exception generationError){
                    PopUp popUp= new PopUp(generationError.getMessage());
                }

                window2.setVisible(true);
                MetricsWindow.setVisible(true);


            }
        });


    }

    /**
     * Attempts to solve maze and draw it on
     * @author Hudson
     */
    public static void solveMyMaze() {
        // solve the maze with the solver object
        Solver mazeSolver = new Solver();

        Integer[] tempDFS = mazeSolver.DFS(Frame.getInstance().myMaze, new Integer[] {0,0});

        ArrayList<Integer[]> mazeSolution = mazeSolver.Solution();

        Render.drawSolution(mazeSolution);
    }

    /**
     * Draws on the metrics for the solved maze including number of tiles visited and dead ends
     * @param MetricsWindow The JFrame which sits below the editor window to draw upon
     * @author Jayden
     */
    public static void SetMetrics(JFrame MetricsWindow){

        try{
            solver.DFS(Frame.getInstance().myMaze,new Integer[]{Frame.getInstance().myMaze.getStart()[0],Frame.getInstance().myMaze.getStart()[1]});
        }catch(Exception e){
            solver.DFS(Frame.getInstance().myMaze,new Integer[]{0,0});
        }

        MetricsWindow.getContentPane().removeAll();

        JLabel CellsVisited = new JLabel("Number of Cells Visited in Solution:");
        CellsVisited.setBounds(0, 0, 230, 40);

        JLabel DeadEnds = new JLabel("Number of Dead End Cells:");
        DeadEnds.setBounds(0, 60, 230, 40);
        MetricsWindow.add(CellsVisited);
        MetricsWindow.add(DeadEnds);

        JLabel CellsVisitedNum = new JLabel(((solver.tilesVisited()*100.0)+"%"));
        CellsVisitedNum.setBounds(250, 0, 40, 40);
        System.out.println(solver.tilesVisited()+"");

        JLabel DeadEndsNum = new JLabel(Frame.getInstance().myMaze.numDeadEnds()+"");
        DeadEndsNum.setBounds(250, 60, 230, 40);
        System.out.println(Frame.getInstance().myMaze.numDeadEnds()+"");
        MetricsWindow.add(CellsVisitedNum);

        MetricsWindow.add(DeadEndsNum);

        MetricsWindow.getContentPane().repaint();
        MetricsWindow.setVisible(true);
    }





}
