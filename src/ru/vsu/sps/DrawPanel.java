package ru.vsu.sps;

import ru.vsu.sps.object.*;
import ru.vsu.sps.object.Box;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    public static final int LAMP_COUNT = 4;
    public static final int FPS = 25;
    private final List<PaintedObject> paintedObjects;

    public DrawPanel() {
        paintedObjects = new ArrayList<>();
        paintedObjects.add(new Background());
        paintedObjects.add(new AssemblyLine());

        double step = 1. / (LAMP_COUNT + 1);
        for (int i = 0; i < LAMP_COUNT; i++) {
            paintedObjects.add(new Lamp(step * (i + 1), i == LAMP_COUNT - 2 ? 1 : -1));
        }
        paintedObjects.add(new Box());
        for (int i = 0; i < 4; i++) {
            paintedObjects.add(new Bottle(ControlPanel.DISTANCE_FROM_LEFT_COEFF / 4 * i));
        }
        paintedObjects.add(new ControlPanel());

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

        Timer timer = new Timer(1000 / FPS, e -> {
            for (PaintedObject obj : paintedObjects) {
                if (obj instanceof AnimatedObject animatedObject) {
                    animatedObject.next(FPS);
                }
            }
            repaint();
        });
        timer.start();
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
