package src.main.java.gui;

import src.main.java.maze.core.Maze;
import src.main.java.maze.core.Tile;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame {
    public int[] mazeSize = new int[2];
    public Maze myMaze = new Maze(new int[]{100, 100});
    public JFrame window;

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

        // remove menu bar
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
            public void actionPerformed(ActionEvent e) {
                SelectOption();
            }
        });

        // on OpenExistingMaze button press open file chooser
        OpenExistingMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser importFile = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
                importFile.showOpenDialog(null);
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


    public void SelectOption(){
        window.getContentPane().removeAll();
        window.getContentPane().repaint();

        JLabel SelectOption = new JLabel("Select one of the following options");
        SelectOption.setBounds(10,10,500,20);

        JButton CreateBlankNewMaze = new JButton("Create Blank New Maze");
        CreateBlankNewMaze.setBounds(50, 50, 300, 20);

        JButton AutoGenerateNewMaze = new JButton("Auto-generate New Maze");
        AutoGenerateNewMaze.setBounds(50, 100, 300, 20);

        JButton Back = new JButton(("Back"));
        Back.setBounds(50,150,150,20);

        window.add(SelectOption);
        window.add(CreateBlankNewMaze);
        window.add(AutoGenerateNewMaze);
        window.add(Back);

        // on CreateBlankNewMaze button press move to MazeType
        CreateBlankNewMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MazeType();
            }
        });

        AutoGenerateNewMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MazeType();
            }
        });

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu();
            }
        });
    }


    public void MazeType(){
        window.getContentPane().removeAll();
        window.getContentPane().repaint();

        JLabel MazeType = new JLabel("What Type of Maze would you like to construct?");
        MazeType.setBounds(10,10,500,20);

        JButton StandardLogoMaze = new JButton("Standard Logo Maze");
        StandardLogoMaze.setBounds(50, 50, 300, 20);

        JButton ChildrenMaze = new JButton("Children Maze");
        ChildrenMaze.setBounds(50, 100, 300, 20);

        JButton Back = new JButton(("Back"));
        Back.setBounds(50,150,150,20);

        window.add(MazeType);
        window.add(StandardLogoMaze);
        window.add(ChildrenMaze);
        window.add(Back);

        // on CreateBlankNewMaze button press move to MazeType
        StandardLogoMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InitialLogoMazeDesign();
            }
        });

        ChildrenMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InitialChildrenMazeDesign();
            }
        });

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectOption();
            }
        });
    }



    public void InitialChildrenMazeDesign(){
        window.getContentPane().removeAll();
        window.getContentPane().repaint();

        JLabel InitialMazeDesign = new JLabel("Please specify the initial design of the maze");
        InitialMazeDesign.setBounds(10,10,500,20);

        JLabel ImportStartingLogo = new JLabel("Please import starting logo:");
        ImportStartingLogo.setBounds(50,50,300,20);
        JButton BrowseStartingLogo = new JButton("Browse");
        BrowseStartingLogo.setBounds(400, 50, 300, 20);

        JLabel ImportFinishingLogo = new JLabel("Please import finishing logo:");
        ImportFinishingLogo.setBounds(50,100,300,20);
        JButton BrowseFinishingLogo = new JButton("Browse");
        BrowseFinishingLogo.setBounds(400, 100, 300, 20);

        JLabel SpecifyWidth = new JLabel("Specify Width:");
        SpecifyWidth.setBounds(50,150,300,20);
        JSpinner SpinnerWidth = new JSpinner(new SpinnerNumberModel(2,2,100,1));
        SpinnerWidth.setBounds(400,150,50,20);

        JLabel SpecifyLength = new JLabel("Specify Length:");
        SpecifyLength.setBounds(50,200,300,20);
        JSpinner SpinnerLength = new JSpinner(new SpinnerNumberModel(2,2,100,1));
        SpinnerLength.setBounds(400,200,50,20);

        JLabel MazeAuthor = new JLabel("Maze Author:");
        MazeAuthor.setBounds(50,250,200,20);
        JTextArea MazeAuthorName = new JTextArea();
        MazeAuthorName.setBounds(200, 250, 300, 20);

        JButton CreateMaze = new JButton(("Create Maze"));
        CreateMaze.setBounds(50,300,150,20);

        JButton Back = new JButton(("Back"));
        Back.setBounds(50,350,150,20);

        window.add(InitialMazeDesign);
        window.add(ImportStartingLogo);
        window.add(BrowseStartingLogo);
        window.add(ImportFinishingLogo);
        window.add(BrowseFinishingLogo);
        window.add(SpecifyWidth);
        window.add(SpinnerWidth);
        window.add(SpecifyLength);
        window.add(SpinnerLength);
        window.add(MazeAuthor);
        window.add(MazeAuthorName);
        window.add(CreateMaze);
        window.add(Back);

        // on CreateBlankNewMaze button press move to MazeType
        BrowseStartingLogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser importFile = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
                importFile.showOpenDialog(null);
            }
        });

        BrowseFinishingLogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser importFile = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
                importFile.showOpenDialog(null);
            }
        });

        CreateMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialise("","");
            }
        });

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MazeType();
            }
        });
    }


    public void InitialLogoMazeDesign(){
        window.getContentPane().removeAll();
        window.getContentPane().repaint();

        JLabel InitialMazeDesign = new JLabel("Please specify the initial design of the maze");
        InitialMazeDesign.setBounds(10,10,500,20);

        JLabel ImportMazeLogo = new JLabel("Please import Maze logo:");
        ImportMazeLogo.setBounds(50,50,300,20);
        JButton BrowseMazeLogo = new JButton("Browse");
        BrowseMazeLogo.setBounds(400, 50, 300, 20);

        JLabel SpecifyWidth = new JLabel("Specify Width:");
        SpecifyWidth.setBounds(50,150,300,20);
        JSpinner SpinnerWidth = new JSpinner(new SpinnerNumberModel(2,2,100,1));
        SpinnerWidth.setBounds(400,150,50,20);

        JLabel SpecifyLength = new JLabel("Specify Length:");
        SpecifyLength.setBounds(50,200,300,20);
        JSpinner SpinnerLength = new JSpinner(new SpinnerNumberModel(2,2,100,1));
        SpinnerLength.setBounds(400,200,50,20);

        JLabel MazeAuthor = new JLabel("Maze Author:");
        MazeAuthor.setBounds(50,250,200,20);
        JTextArea MazeAuthorName = new JTextArea();
        MazeAuthorName.setBounds(200, 250, 300, 20);

        JButton CreateMaze = new JButton(("Create Maze"));
        CreateMaze.setBounds(50,300,150,20);

        JButton Back = new JButton(("Back"));
        Back.setBounds(50,350,150,20);

        window.add(InitialMazeDesign);
        window.add(ImportMazeLogo);
        window.add(BrowseMazeLogo);
        window.add(SpecifyWidth);
        window.add(SpinnerWidth);
        window.add(SpecifyLength);
        window.add(SpinnerLength);
        window.add(CreateMaze);
        window.add(MazeAuthor);
        window.add(MazeAuthorName);
        window.add(Back);

        // on CreateBlankNewMaze button press move to MazeType
        BrowseMazeLogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser importFile = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
                importFile.showOpenDialog(null);
            }
        });

        CreateMaze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialise("","");
            }
        });

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MazeType();
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
        labelx.setBounds(10,50,50,20);

        JLabel labely = new JLabel("Height:");
        labely.setBounds(120,50,50,20);

        JTextArea inputx = new JTextArea(xvar);
        inputx.setBounds(50, 50, 50, 20);

        JTextArea inputy = new JTextArea(yvar);
        inputy.setBounds(160, 50, 50, 20);

        JButton Back = new JButton("Back");
        Back.setBounds(10, 10, 75, 20);

        JButton Save = new JButton("Save");
        Save.setBounds(100, 10, 75, 20);

        JButton ImportStartingLogo = new JButton("Import Starting Logo");
        ImportStartingLogo.setBounds(200,10,175,20);

        JButton ImportFinishingLogo = new JButton("Import Finishing Logo");
        ImportFinishingLogo.setBounds( 400,10,175,20);


        JRadioButton StandardLogoMaze = new JRadioButton("Standard Logo Maze");
        StandardLogoMaze.setActionCommand("StandardLogoMaze Action");
        StandardLogoMaze.setBounds(10, 30, 150, 20);

        JRadioButton ChildrensLogoMaze = new JRadioButton("Childrens Logo Maze");
        ChildrensLogoMaze.setActionCommand("ChildrensLogoMaze Action");
        ChildrensLogoMaze.setBounds(160, 30, 150, 20);

        ButtonGroup MazeTypeSelection = new ButtonGroup();
        MazeTypeSelection.add(StandardLogoMaze);
        MazeTypeSelection.add(ChildrensLogoMaze);
        MazeTypeSelection.add(ChildrensLogoMaze);


        JRadioButton EasyDifficulty = new JRadioButton("Easy");
        EasyDifficulty.setActionCommand("Easy Difficulty Action");
        EasyDifficulty.setBounds(360, 30, 100, 20);

        JRadioButton MediumDifficulty = new JRadioButton("Medium");
        MediumDifficulty.setActionCommand("Medium Difficulty Action");
        MediumDifficulty.setBounds(460, 30, 100, 20);

        JRadioButton HardDifficulty = new JRadioButton("Hard");
        HardDifficulty.setActionCommand("Hard Difficulty Action");
        HardDifficulty.setBounds(560, 30, 100, 20);

        ButtonGroup DifficultySelection = new ButtonGroup();
        DifficultySelection.add(EasyDifficulty);
        DifficultySelection.add(MediumDifficulty);
        DifficultySelection.add(HardDifficulty);

        JButton setSize = new JButton("Generate Maze");
        setSize.setBounds(600, 10, 200, 20);



        window.add(inputx);
        window.add(labelx);
        window.add(inputy);
        window.add(labely);

        window.add(Back);
        window.add(setSize);
        window.add(Save);
        window.add(StandardLogoMaze);
        window.add(ChildrensLogoMaze);
        window.add(ImportStartingLogo);
        window.add(ImportFinishingLogo);
        window.add(EasyDifficulty);
        window.add(MediumDifficulty);
        window.add(HardDifficulty);


        setSize.addActionListener(action -> setButtonPressed(inputx.getText(), inputy.getText()));
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MazeType();
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


    }

    /**
     * Validates user input for sizing maze
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
     * @author Jayden
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
        myMaze = new Maze(mazeSize);
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