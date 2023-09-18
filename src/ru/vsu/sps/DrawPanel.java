package ru.vsu.sps;

import ru.vsu.sps.object.AssemblyLine;
import ru.vsu.sps.object.Background;
import ru.vsu.sps.object.PaintedObject;
import ru.vsu.sps.object.ScalableObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class DrawPanel extends JPanel {
    private final PaintedObject[] paintedObjects;

    public DrawPanel() {
        paintedObjects = new PaintedObject[]{new Background(),
                new AssemblyLine()};

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                for (PaintedObject obj : paintedObjects) {
                    if (obj instanceof ScalableObject scalableObject) {
                        scalableObject.changeWindowSize(getWidth(), getHeight());
                    }
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        for (PaintedObject obj : paintedObjects) {
            obj.paint(g2d);
        }
    }
}
