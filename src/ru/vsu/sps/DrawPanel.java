package ru.vsu.sps;

import ru.vsu.sps.object.Background;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    public DrawPanel() {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        Background bg = new Background(getWidth(), getHeight());

        bg.paint(g2d);
    }
}
