package ru.vsu.sps.object;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class AssemblyLine implements AnimatedObject, PaintedObject, ScalableObject {
    public static final Color LINE_COLOR = new Color(179, 178, 176);
    public static final Color INNER_LINE_COLOR = new Color(85, 95, 105);
    public static final Color LEG_COLOR = new Color(82, 95, 103);
    public static final Color CIRCLE_COLOR = new Color(100, 110, 119);
    public static final int LEGS_COUNT = 5;
    public static final double INNER_LINE_RECT_DISTANCE_COEFF = 0.015;
    private int innerLineStartOffset = 0;

    private double xCoeff, yCoeff;
    private int width, height;

    public AssemblyLine(double xCoeff, double yCoeff) {
        this.xCoeff = xCoeff;
        this.yCoeff = yCoeff;
    }

    @Override
    public void paint(Graphics2D gr) {
        paintLine(gr);
        paintLegs(gr);
    }

    private void paintLine(Graphics2D gr) {
        Stroke stroke = gr.getStroke();

        gr.setColor(LINE_COLOR);
        gr.setStroke(new BasicStroke(20));
        RoundRectangle2D roundRectangle2D = new RoundRectangle2D.Double(xCoeff * width,
                yCoeff * height, width * 0.75, height * 0.15, 50 + 0.1 * width, 360);
        gr.draw(roundRectangle2D);
        gr.setStroke(stroke);

        gr.setColor(INNER_LINE_COLOR);
        int step = (int) (INNER_LINE_RECT_DISTANCE_COEFF * width);
        for (int i = 0; i * step + xCoeff * width < (xCoeff + 0.70) * width; i++) {
            gr.fillRect((int) (xCoeff * width + i * step) + innerLineStartOffset,
                    (int) (yCoeff * height + 10), (int) (width * 0.01), (int) (height * 0.005));
            gr.fillRect((int) (xCoeff * width + i * step) - innerLineStartOffset,
                    (int) ((yCoeff + 0.15) * height - 10), (int) (width * 0.01), (int) (height * 0.005));
        }
    }

    private void paintLegs(Graphics2D gr) {
        gr.setColor(LEG_COLOR);

        gr.fillRect((int) ((xCoeff + 0.02) * width), (int) (yCoeff * height + height * 0.15),
                (int) (width * 0.7), (int) (height * 0.05));

        int step = (int) (width * 0.71 / (LEGS_COUNT - 1));
        for (int i = 0; i < LEGS_COUNT; i++) {
            gr.setColor(CIRCLE_COLOR);
            gr.fillOval((int) ((xCoeff - 0.01) * width + step * i - (height * 0.15 - 20) / 2),
                    (int) (yCoeff * height), (int) (width * 0.1), (int) (height * 0.15));
            gr.setColor(LEG_COLOR);
            gr.fillRect((int) ((xCoeff - 0.01) * width + step * i), (int) (yCoeff * height + height * 0.15),
                    (int) (width * 0.03), (int) (height * 0.2));
        }
    }

    @Override
    public void changeWindowSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void next(int fps) {
        innerLineStartOffset = innerLineStartOffset +
                (int) ((INNER_LINE_RECT_DISTANCE_COEFF / fps) * width);
        innerLineStartOffset %= (int) (width * 0.01);
    }
}
