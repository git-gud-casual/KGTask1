package ru.vsu.sps;

import javax.swing.*;

public class MainWindow extends JFrame {
    private final DrawPanel dp;

    public MainWindow() {
        this.setTitle("Assembler");

        dp = new DrawPanel();
        this.add(dp);
    }
}
