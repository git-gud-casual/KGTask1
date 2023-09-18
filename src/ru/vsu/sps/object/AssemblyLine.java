package ru.vsu.sps.object;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class AssemblyLine implements AnimatedObject, PaintedObject, ScalableObject {
    public static final Color EXTERNAL_LAYOUT_COLOR = new Color(179, 178, 176);

    private int width, height;

    @Override
    public void next() {

    }

    @Override
    public void paint(Graphics2D gr) {
        gr.setColor(EXTERNAL_LAYOUT_COLOR);
        gr.setStroke(new BasicStroke(25));
        RoundRectangle2D roundRectangle2D = new RoundRectangle2D.Double(0, 0, 400, 100, 100, 150);
        gr.draw(roundRectangle2D);
    }

    @Override
    public void changeWindowSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
