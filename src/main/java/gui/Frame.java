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
            public void actionPerformed(ActionEvent e) { initialise("","");}
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



    /**
     * Intialises the new blank maze GUI
     * @param xvar Suitable screen width
     * @param yvar Suitable screen height
     */
    public void initialise(String xvar, String yvar){
        window.getContentPane().removeAll();
        window.getContentPane().repaint();

        // maze needs to be drawn inside a pane for scrollbars to work and for other buttons to stay constant
        /*JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        window.setContentPane(pane);*/




        JLabel labelx = new JLabel("Width:");
        labelx.setBounds(20,20,50,20);

        JTextArea inputx = new JTextArea(xvar);
        inputx.setBounds(110, 20, 50, 20);


        JLabel labely = new JLabel("Height:");
        labely.setBounds(20,50,50,20);

        JTextArea inputy = new JTextArea(yvar);
        inputy.setBounds(110, 50, 50, 20);


        JLabel LogoCellSizeLabel = new JLabel("Logo Cell Size:");
        LogoCellSizeLabel.setBounds(20,80,100,20);

        JTextArea LogoCellSizeInput = new JTextArea();
        LogoCellSizeInput.setBounds(110, 80, 50,20);


        JRadioButton StandardMazeButton = new JRadioButton("Standard Maze");
        StandardMazeButton.setBounds(20, 20, 100, 20);
        StandardMazeButton.setActionCommand("Standard Maze Action");

        JRadioButton ChildrensMazeButton = new JRadioButton("Childrens Maze");
        ChildrensMazeButton.setBounds(20, 50, 100, 20);
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



        JButton Back = new JButton("Back");
        Back.setBounds(10, 10, 75, 20);

        JButton Save = new JButton("Save");
        Save.setBounds(100, 10, 75, 20);

        JButton ImportStartingLogo = new JButton("Import Starting Logo");
        ImportStartingLogo.setBounds(200,10,175,20);

        JButton ImportFinishingLogo = new JButton("Import Finishing Logo");
        ImportFinishingLogo.setBounds( 400,10,175,20);









        JButton setSize = new JButton("Generate Maze");
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        setSize.setBounds(600, 10, 120, 20);
=======
        setSize.setBounds(600, 10, 200, 20);
>>>>>>> 6eeac4e2771bcff2c3c617915f8a9f7fdf75459b


=======
        setSize.setBounds(0, 30, 130, 20);

        JButton exportBTN = new JButton("Export Maze");
            exportBTN.setBounds(150, 30, 130, 20);
>>>>>>> main
=======
        setSize.setBounds(660, 30, 130, 20);

        JPanel MazePropertiesPanel = new JPanel();
        MazePropertiesPanel.setBounds(10,100,175,125);
        MazePropertiesPanel.setBorder(BorderFactory.createTitledBorder("Maze Properties"));
        MazePropertiesPanel.add(labelx);
        MazePropertiesPanel.add(inputx);
        MazePropertiesPanel.add(labely);
        MazePropertiesPanel.add(inputy);
        MazePropertiesPanel.add(LogoCellSizeLabel);
        MazePropertiesPanel.add(LogoCellSizeInput);

        JPanel MazeTypePanel = new JPanel();
        MazeTypePanel.setBounds(190, 100, 175,125);
        MazeTypePanel.setBorder(BorderFactory.createTitledBorder("Maze Type"));
        MazeTypePanel.add(StandardMazeButton);
        MazeTypePanel.add(ChildrensMazeButton);

        JPanel MazeDifficultyPanel = new JPanel();
        MazeDifficultyPanel.setBounds(370, 100, 175, 125);
        MazeDifficultyPanel.setBorder(BorderFactory.createTitledBorder("Maze Difficulty"));
        MazeDifficultyPanel.add(EasyDifficultyButton);
        MazeDifficultyPanel.add(MediumDifficultyButton);
        MazeDifficultyPanel.add(HardDifficultyButton);


>>>>>>> ab427027de008f95f869a795ca3acea573d1cf9f

        //window.add(inputx);
        //window.add(labelx);
        //window.add(inputy);
        //window.add(labely);

<<<<<<< HEAD
<<<<<<< HEAD
        window.add(Back);
=======
        window.add(exportBTN);


>>>>>>> main
=======
        window.add(Back);


>>>>>>> ab427027de008f95f869a795ca3acea573d1cf9f
        window.add(setSize);
        //window.add(Save);
        //window.add(StandardMazeButton);
        //window.add(ChildrensMazeButton);
        window.add(ImportStartingLogo);
        window.add(ImportFinishingLogo);
        //window.add(EasyDifficulty);
        //window.add(MediumDifficulty);
        //window.add(HardDifficulty);
        window.add(MazePropertiesPanel);
        window.add(MazeTypePanel);
        window.add(MazeDifficultyPanel);


        setSize.addActionListener(action -> setButtonPressed(inputx.getText(), inputy.getText()));
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { MainMenu();}
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


    }

    /**
     * Validates user input for sizing maze
     * @author Hudson
     * @param inputs The width and height specified by the user
     * @return True if input is a number and not less than or equal to 0. False if not
     */
    private boolean validateInput(String[] inputs) {
        for (int i = 0; i < inputs.length; i++) {
            try {
                if (Integer.parseInt(inputs[i]) <= 0) return false;
            } catch(Exception e) {
                return false;
            }
        }

        return true;
    }
    /**
     * Validates the validateInput is working as per user story
     * @author Jack
     */
    @Test
    public void TestValidateInput(){
        //Valid Case 1
        String[] inputs = {"1", "1"};
        assertTrue(this.validateInput(inputs));

        //Valid Case 2
        inputs[0] ="1";inputs[1]="100";
        assertTrue(this.validateInput(inputs));

        //Valid Case 3
        inputs[0] ="100";inputs[1]="1";
        assertTrue(this.validateInput(inputs));

        //Valid Case 4
        inputs[0] ="100";inputs[1]="100";
        assertTrue(this.validateInput(inputs));

        //Invalid Case 1
        inputs[0] ="1 2";inputs[1]="1 0 0";
        assertFalse(this.validateInput(inputs), "Not valid inputs " + inputs);

        //Invalid Case 2
        inputs[0] ="0";inputs[1]="1";
        assertFalse(this.validateInput(inputs), "Under limit for " + inputs);

        //Invalid Case 4
        inputs[0] ="-1";inputs[1]="0";
        assertFalse(this.validateInput(inputs), "Under limit for " + inputs);

        //Invalid Case 5
        inputs[0] ="1F";inputs[1]="one";
        assertFalse(this.validateInput(inputs),"Not valid inputs " + inputs);

        //Invalid Case 6
        inputs[0] ="101";inputs[1]="101";
        assertFalse(this.validateInput(inputs),"Over limit for " + inputs);
    }

    /**
     * @author Jayden and Hudson
     * Actions that take place after the user has clicked the 'Generate Maze' button
     * @param width Width specified by the user
     * @param height Height specified by the user
     */
    public void setButtonPressed(String width, String height){
        String[] inputs = {width, height};

        if (!validateInput(inputs)) {
            System.out.println("[ERROR] Invalid maze size...");
            return;
        }

        initialise(width.trim(), height.trim());

        //System.out.println(width.trim()+", "+height.trim());

        mazeSize = new int[]{Integer.parseInt(width.trim()),Integer.parseInt(height.trim())};
        myMaze = new Maze(mazeSize, false); //need to pass child maze param
        for (int x = 0; x < myMaze.mazeSize()[0]; x++) {
            for (int y = 0; y < myMaze.mazeSize()[1]; y++) {
                // border styling
                JButton tempBTN = new JButton("");
                tempBTN.setBounds(10 + x * 50, 75 + y * 50, 40, 10);
                int finalX = x;
                int finalY = y;
                tempBTN.addActionListener(action -> mazeButtonPressed(finalX,finalY, tempBTN, true));
                tempBTN.setBackground(y == 0 ? Color.BLACK : Color.WHITE);
                window.add(tempBTN);

                JButton tempBTN2 = new JButton("");
                tempBTN2.setBounds(x * 50, 10+75 + y * 50, 10, 40);
                tempBTN2.addActionListener(action -> mazeButtonPressed(finalX,finalY, tempBTN2, false));
                tempBTN2.setBackground(x == 0 ? Color.BLACK : Color.WHITE);
                window.add(tempBTN2);
            }
        }

        for(int i = 0; i < myMaze.mazeSize()[1]; i++){
            JButton tempBTN2 = new JButton("");
            tempBTN2.setBounds(myMaze.mazeSize()[0] * 50, 10+75 + i * 50, 10, 40);
            int finalX = myMaze.mazeSize()[0];
            int finalY = i;
            tempBTN2.addActionListener(action -> mazeButtonPressed(finalX,finalY, tempBTN2, false));
            tempBTN2.setBackground(Color.BLACK);
            window.add(tempBTN2);
        }

        for(int i = 0; i < myMaze.mazeSize()[0]; i++){
            JButton tempBTN = new JButton("");
            tempBTN.setBounds(10 + i * 50, 75 + myMaze.mazeSize()[1] * 50, 40, 10);
            int finalX = i;
            int finalY = myMaze.mazeSize()[1];
            tempBTN.addActionListener(action -> mazeButtonPressed(finalX,finalY, tempBTN, true));
            tempBTN.setBackground(Color.BLACK);
            window.add(tempBTN);
        }


        SwingUtilities.updateComponentTreeUI(window);
    }

    /**
     * Actions that occur after the user selects a tile in the maze
     * @author Jayden
     * @param x X location of that tile
     * @param y Y location of that tile
     * @param button The button representing that tile
     * @param Top Boolean to determine whether it is at the top of the maze
     */
    public void mazeButtonPressed(int x, int y, JButton button, boolean Top){
        boolean set = false;
        if(button.getBackground() == Color.BLACK){
            button.setBackground(Color.WHITE);
        }else{
            button.setBackground(Color.BLACK);
            set = true;
        }

        if(Top){
            if(y == 0){
                myMaze.mazeTile(x,y).setTopWall(set);
            }else if(y==mazeSize[1]){
                myMaze.mazeTile(x,y).setBottomWall(set);
            }else{
                myMaze.mazeTile(x,y).setTopWall(set);
                myMaze.mazeTile(x,y-1).setBottomWall(set);
            }
        }

        //Prints the coords of the button pressed
        System.out.println();

    }
}
