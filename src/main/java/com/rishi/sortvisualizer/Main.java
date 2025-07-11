package com.rishi.sortvisualizer;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Failed to initialize LaF");
            e.printStackTrace();
        }

        new VisualizerFrame();
    }
}