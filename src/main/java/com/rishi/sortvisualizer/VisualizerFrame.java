package com.rishi.sortvisualizer;

import com.rishi.sortvisualizer.algorithms.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;


public class VisualizerFrame extends JFrame {

    private SortPanel sortPanel;
    private JPanel controlPanel;
    private JComboBox<String> algoDropdown;
    private JSlider speedSlider , sizeSlider;

    /**
     * Create the frame.
     */
    public VisualizerFrame() {
        // Frame Properties
        setTitle("Sorting Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sorting Area Panel
        sortPanel = new SortPanel();

        // ScrollPane
        JScrollPane scrollPane = new JScrollPane(sortPanel);
        add(scrollPane,BorderLayout.CENTER);

        // Control Panel
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Algorithm Selector Dropdown
        algoDropdown = new JComboBox<>(new String[] {"Bubble Sort", "Selection Sort","Insertion Sort","Merge Sort","Quick Sort"});
        controlPanel.add(algoDropdown);

        // Speed Sliders
        speedSlider = new JSlider(0, 50, 30);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setMajorTickSpacing(10);
        speedSlider.setValueIsAdjusting(true);
        controlPanel.add(new JLabel("Speed:"));
        controlPanel.add(speedSlider);

        // Size Slider
        sizeSlider = new JSlider(10, 1000, 150); // means no of bars
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);
        sizeSlider.setMajorTickSpacing(190);
        controlPanel.add(new JLabel("Size:"));
        controlPanel.add(sizeSlider);

        // Shuffle (Resets the array)
        JButton shuffleButton = new JButton("Shuffle");
        controlPanel.add(shuffleButton);
        shuffleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==shuffleButton){
                    int size = sizeSlider.getValue();
                    sortPanel.generateRandomArray(size);
                    sortPanel.setCurrentSize(size);

                    // Again repaint the sort panel to update the view
                    sortPanel.repaint();
                }
            }
        });

        // Start Button
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(false);
                shuffleButton.setEnabled(false);

                String algoName = (String)algoDropdown.getSelectedItem();
                sortPanel.setAlgorithmInfo(algoName, getComplexity(algoName));

                SortingAlgorithm algorithm;
                int delay = speedSlider.getValue();
                int size = sizeSlider.getValue();

                // Update the size and size to display them
                sortPanel.setCurrentSpeed(delay);
                sortPanel.setCurrentSize(size);

                // Check which Algorithm to implement
                if(algoName.equals("Bubble Sort")){
                    algorithm = new BubbleSort();
                }
                else if(algoName.equals("Selection Sort")){
                    algorithm = new SelectionSort();
                }
                else if(algoName.equals("Insertion Sort")){
                    algorithm = new InsertionSort();
                }
                else if(algoName.equals("Merge Sort")){
                    algorithm = new MergeSort();
                }
                else if(algoName.equals("Quick Sort")){
                    algorithm = new QuickSort();
                }
                else{
                    throw new IllegalStateException("Unknown Algorithm Selected");
                }

                // Create a new thread so that Sorting happens in background in different thread
                // so that it does not freezes the main GUI
               Thread sortThread = new Thread(new Runnable() {
                   @Override
                   public void run() {
                       try {
                           algorithm.sort(sortPanel.getArray(),sortPanel,delay);
                       } catch (InterruptedException ex) {
                           System.out.println("Sorting cancelled");
                       }

                       // when swing is again ready after the whole sorting process
                       // then run this
                       SwingUtilities.invokeLater(new Runnable() {
                           @Override
                           public void run() {
                               sortPanel.repaint(); // redraw the panel
                               startButton.setEnabled(true);
                               shuffleButton.setEnabled(true);
                           }
                       });
                   }
               });
                sortThread.start();
            }
        });
        controlPanel.add(startButton);


        // Pause / Resume Button
        JButton pauseButton = new JButton("Stop");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortPanel.togglePause();
                pauseButton.setText( sortPanel.isPaused()? "Resume" : "Pause");
            }
        });
        controlPanel.add(pauseButton);

        // reset button
        JButton resetButton = new JButton("Reset(Cancel)");
        controlPanel.add(resetButton);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // as soon  the button is preseed cancel the sorting
                sortPanel.cancel();

                // open a new thread so that you can give time to sort thread to close
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Thread.sleep(50);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }

                        SwingUtilities.invokeLater(() -> {
                            // completely reset all settings of the sort panel
                            sortPanel.generateRandomArray(150);
                            sortPanel.setCurrentSize(150);
                            sortPanel.setCurrentSpeed(30);
                            sortPanel.resetComparisons();
                            sortPanel.resetTimeTaken();
                            sortPanel.setHighlightIndex(-1);
                            sortPanel.resetCancel(); // to make it false again
                            sortPanel.setAlgorithmInfo("","");
                            sortPanel.repaint();
                            startButton.setEnabled(true);
                            shuffleButton.setEnabled(true);
                            pauseButton.setText("Stop"); // manually set it to stop if it was resume
                        });
                    }
                }).start();
            }
        });

        add(controlPanel,BorderLayout.NORTH);
        setVisible(true);
    }

    private String getComplexity(String algoName){
            switch(algoName){
                case "Bubble Sort": return "T.C = O(n²) | S.C = O(1)";
                case "Selection Sort": return "T.C = O(n²) | S.C = O(1)";
                case "Insertion Sort": return "T.C = O(n²) , O(n) Best | S.C = O(1)";
                case "Merge Sort": return "T.C = O(n.logn) | S.C = O(n)";
                case "Quick Sort": return "T.C = O(n.logn), worst O(n²) | S.C = O(logn)";
                default: return "";
            }
    }
}

