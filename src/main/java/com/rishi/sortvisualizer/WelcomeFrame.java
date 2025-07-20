package com.rishi.sortvisualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeFrame extends JFrame {

    public WelcomeFrame(){
        // Jframe properties
        setTitle("Sorting Visualizer");
        setSize(1200,750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel();
//        mainPanel.setBackground(new Color(35, 35, 35));
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Sorting Visualizer");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("consolas",Font.PLAIN,17));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea text = new JTextArea();
        text.setText("\nThis project visualizes various sorting algorithms in real-time\nusing Java Swing and FlatLaf for modern UI.\n\n" +
                "Algorithms Supported:\n" +
                " - Bubble Sort\n" +
                " - Insertion Sort\n" +
                " - Selection Sort\n" +
                " - Merge Sort\n" +
                " - Quick Sort\n\n" +
                "Functionalities:\n" +
                " - Adjust Array Size\n" +
                " - Control Sorting Speed\n" +
                " - Start, Pause, Resume, and Reset Sorting\n\n" +
                "Created by: Rishi Rana"
        );
        text.setEditable(false);
        text.setFocusable(false);
        text.setBackground(new Color(35,35,35));
        text.setForeground(Color.WHITE);
        text.setFont(new Font("consolas",Font.PLAIN,15));
        text.setAlignmentX(Component.CENTER_ALIGNMENT);

        // start button to open the visualizer frame
        JButton startButton = new JButton("Start Visualizer");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setFont(new Font("consolas",Font.PLAIN,15));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VisualizerFrame();
                dispose();// close the current welcome frame
            }
        });

        mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
        mainPanel.add(text);
        mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
        mainPanel.add(startButton);

        add(mainPanel);
        setVisible(true);
    }
}
