package ru.vsu.sps.object;

import java.awt.*;

public class Lamp implements PaintedObject, ScalableObject, AnimatedObject {
    private double xCoeff;
    private int animationPeriod;
    private int counter = 0;
    private Color currColor = LAMP_COLOR_ON;
    private int width = 0, height = 0;
    public static final Color MAIN_COLOR = new Color(0, 0, 0);
    public static final Color LAMP_COLOR_OFF = new Color(255, 255, 255);
    public static final Color LAMP_COLOR_ON = new Color(238,210, 2);

    public Lamp(double xCoeff, int animationPeriod) {
        this.xCoeff = xCoeff;
        this.animationPeriod = animationPeriod;
    }

    @Override
    public void next(int fps) {
        counter++;
        if (counter / fps == animationPeriod) {
            currColor = currColor == LAMP_COLOR_ON ? LAMP_COLOR_OFF : LAMP_COLOR_ON;
            counter = 0;
        }
    }

    @Override
    public void paint(Graphics2D gr) {
        int xPos = (int) (width * xCoeff);

        gr.setColor(currColor);
        gr.fillOval((int) (xPos - width * 0.015), (int) (height * 0.17), (int) (width * 0.03), (int) (height * 0.05));

        gr.setColor(MAIN_COLOR);
        gr.drawLine(xPos, 0, xPos, (int) (height * 0.15));
        gr.fillPolygon(new int[] {(int) (xPos - width * 0.05), xPos, (int) (xPos + width * 0.05)},
                new int[] {(int) (height * 0.2), (int) (height * 0.15), (int) (height * 0.2)}, 3);

    }

    @Override
    public void changeWindowSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
