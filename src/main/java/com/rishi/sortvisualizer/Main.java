package com.rishi.sortvisualizer;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Failed to initialize FlatLaF");
            e.printStackTrace();
        }

        // Customize Title Bar Colors (FlatLaf Decoration)
        UIManager.put("TitlePane.unifiedBackground", false);
        UIManager.put("TitlePane.background", new java.awt.Color(23, 23, 23));
        UIManager.put("TitlePane.buttonSize",new Dimension(40,28));

        // Customize Main Panel Background
        UIManager.put("Panel.background", new java.awt.Color(30, 30, 30));

        new WelcomeFrame();
    }
}