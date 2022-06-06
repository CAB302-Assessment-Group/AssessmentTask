package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUp {
    private String message;
    public static JFrame popUp;

    private final int WIDTH = 200;
    private final int HEIGHT = 150;

    public PopUp(String message) {
        this.message = message;
        popUp = new JFrame("Error Message Popup");

        popUp.setLayout(null);

        // make the program process close when the main window is closed
        popUp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        popUp.setVisible(true);
        popUp.setSize(WIDTH,HEIGHT);
        popUp.setLocation(Frame.window2.getLocation());
        JLabel Title = new JLabel(message);
        Title.setBounds(10,10,WIDTH-10,20);

        JButton Exit = new JButton(("Ok"));
        Exit.setBounds(60,60,50,20);

        popUp.getContentPane().removeAll();
        popUp.getContentPane().repaint();

        popUp.add(Title);
        popUp.add(Exit);

        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popUp.setVisible(false);
            }
        });

    }
}

