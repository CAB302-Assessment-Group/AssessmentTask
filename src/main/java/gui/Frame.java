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
    public Maze myMaze = new Maze(new int[]{100, 100}); //init as adult maze
    public static JFrame window;

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
            public void actionPerformed(ActionEvent e) { initialise("","");}
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
    /**
     * Intialises the new blank maze GUI
     * @param xvar Suitable screen width
     * @param yvar Suitable screen height
     */
    public static void initialise(String xvar, String yvar){
        window.getContentPane().removeAll();
        window.getContentPane().repaint();


        // maze needs to be drawn inside a pane for scrollbars to work and for other buttons to stay constant
        /*JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        window.setContentPane(pane);*/




        JLabel labelx = new JLabel("Width:");
        labelx.setBounds(20,100,50,20);

        JTextArea inputx = new JTextArea(xvar);
        inputx.setBounds(70, 100, 30, 20);


        JLabel labely = new JLabel("Height:");
        labely.setBounds(20,130,50,20);

        JTextArea inputy = new JTextArea(yvar);
        inputy.setBounds(70, 130, 30, 20);


        JLabel LogoCellSizeLabel = new JLabel("Logo Cell Size:");
        LogoCellSizeLabel.setBounds(0,0,100,20);

        JTextArea LogoCellSizeInput = new JTextArea();
        LogoCellSizeInput.setBounds(0,0, 50,20);


        JRadioButton StandardMazeButton = new JRadioButton("Standard Maze");
        StandardMazeButton.setBounds(20, 20, 110, 20);
        StandardMazeButton.setActionCommand("Standard Maze Action");

        JRadioButton ChildrensMazeButton = new JRadioButton("Childrens Maze");
        ChildrensMazeButton.setBounds(20, 50, 120, 20);
        ChildrensMazeButton.setActionCommand("Childrens Maze Action");

        ButtonGroup MazeTypeSelection = new ButtonGroup();
        MazeTypeSelection.add(StandardMazeButton);
        MazeTypeSelection.add(ChildrensMazeButton);



        JRadioButton EasyDifficultyButton = new JRadioButton("Easy");
        EasyDifficultyButton.setActionCommand("Easy Difficulty Action");
        EasyDifficultyButton.setBounds(20,   20, 100, 20);

        JRadioButton MediumDifficultyButton = new JRadioButton("Medium");
        MediumDifficultyButton.setActionCommand("Medium Difficulty Action");
        MediumDifficultyButton.setBounds(20, 50, 100, 20);

        JRadioButton HardDifficultyButton = new JRadioButton("Hard");
        HardDifficultyButton.setActionCommand("Hard Difficulty Action");
        HardDifficultyButton.setBounds(20, 80, 100, 20);

        ButtonGroup DifficultySelection = new ButtonGroup();
        DifficultySelection.add(EasyDifficultyButton);
        DifficultySelection.add(MediumDifficultyButton);
        DifficultySelection.add(HardDifficultyButton);



        JButton BackButton = new JButton("Back");
        BackButton.setBounds(10, 20, 75, 20);

        JButton SaveButton = new JButton("Save");
        SaveButton.setBounds(100, 20, 75, 20);

        JButton ExportMazeButton = new JButton("Export Maze Image");
        ExportMazeButton.setBounds(150, 30, 130, 20);

        JButton GenerateBlankMazeButton = new JButton("Generate Blank Maze");
        GenerateBlankMazeButton.setBounds(140, 100, 175, 20);

        JButton AutoGenerateMazeButton = new JButton("Auto Generate Maze");
        AutoGenerateMazeButton.setBounds(140,130,175,20);

        JButton ImportStartingLogo = new JButton("Import Starting Logo");
        ImportStartingLogo.setBounds(200,10,175,20);

        JButton ImportFinishingLogo = new JButton("Import Finishing Logo");
        ImportFinishingLogo.setBounds( 400,10,175,20);



        JPanel MenuBarPanel = new JPanel();
        MenuBarPanel.setBounds(10, 10, 500, 75);
        MenuBarPanel.setBorder(BorderFactory.createTitledBorder("Menu Bar"));
        MenuBarPanel.add(BackButton);
        MenuBarPanel.add(SaveButton);
        //MenuBarPanel.add(ExportMazeButton);








        /*JPanel MazePropertiesPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridwidth = 3;
        constraints.gridheight = 3;


        MazePropertiesPanel.setBounds(10,100,175,125);
        MazePropertiesPanel.setBorder(BorderFactory.createTitledBorder("Maze Properties"));


        constraints.gridx = 0;
        constraints.gridy = 0;

        MazePropertiesPanel.add(labelx, constraints);

        *//*constraints.gridx = 1;
        constraints.gridy = 0;
        MazePropertiesPanel.add(inputx, constraints);*//*

        constraints.gridx = 0;
        constraints.gridy = 1;
        //c.weighty = 0.01;
        MazePropertiesPanel.add(labely, constraints);

        *//*c.gridx = 2;
        c.gridy = 1;
        //c.weighty = 0.01;
        MazePropertiesPanel.add(inputy, c);*//*

        constraints.gridx = 0;
        constraints.gridy = 2;
        //c.weighty = 0.01;*/
        /*MazePropertiesPanel.add(LogoCellSizeLabel, constraints);*/

        /*c.gridx = 2;
        c.gridy = 2;
        //c.weighty = 0.01;
        MazePropertiesPanel.add(LogoCellSizeInput, c);*/

        JPanel MazeTypePanel = new JPanel();
        MazeTypePanel.setBounds(190, 100, 150,125);
        MazeTypePanel.setBorder(BorderFactory.createTitledBorder("Maze Type"));
        MazeTypePanel.add(StandardMazeButton);
        MazeTypePanel.add(ChildrensMazeButton);

        JPanel MazeDifficultyPanel = new JPanel();
        MazeDifficultyPanel.setBounds(345, 100, 125, 125);
        MazeDifficultyPanel.setBorder(BorderFactory.createTitledBorder("Maze Difficulty"));
        MazeDifficultyPanel.add(EasyDifficultyButton);
        MazeDifficultyPanel.add(MediumDifficultyButton);
        MazeDifficultyPanel.add(HardDifficultyButton);

        JPanel MazeGenerationPanel = new JPanel();
        MazeGenerationPanel.setBounds(10, 225, 500, 400);
        MazeGenerationPanel.setBorder(BorderFactory.createTitledBorder("Maze Generation Preview"));







        //window.add(exportBTN);
        //window.add(Back);
        //window.add(setSize);
        //window.add(ImportStartingLogo);
        //window.add(ImportFinishingLogo);
        window.add(labelx);
        window.add(inputx);
        window.add(labely);
        window.add(inputy);
        window.add(GenerateBlankMazeButton);
        window.add(AutoGenerateMazeButton);


        window.add(MenuBarPanel);
        //window.add(MazePropertiesPanel);
        //window.add(MazeTypePanel);
        //window.add(MazeDifficultyPanel);
        window.add(MazeGenerationPanel);


        //setSize.addActionListener(action -> Render.setButtonPressed(inputx.getText(), inputy.getText()));

        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { Frame.getInstance().MainMenu();}
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
                initialise(inputx.getText(),inputy.getText());
            }
        });


    }
}
