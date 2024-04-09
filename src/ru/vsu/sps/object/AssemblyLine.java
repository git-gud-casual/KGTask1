package ru.vsu.sps.object;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class AssemblyLine implements AnimatedObject, PaintedObject, ScalableObject {
    public static final Color LINE_COLOR = new Color(179, 178, 176);
    public static final Color INNER_LINE_COLOR = new Color(85, 95, 105);
    public static final Color LEG_COLOR = new Color(82, 95, 103);
    public static final Color CIRCLE_COLOR = new Color(100, 110, 119);
    public static final int LEGS_COUNT = 6;
    public static final double INNER_LINE_RECT_DISTANCE_COEFF = 0.015;
    private int innerLineStartOffset = 0;

    public static final double DISTANCE_FROM_LEFT_COEFF = -0.1, DISTANCE_FROM_BOTTOM_COEFF = 0.5;
    private int width, height;

    @Override
    public void paint(Graphics2D gr) {
        paintLine(gr);
        paintLegs(gr);
    }

    private void paintLine(Graphics2D gr) {
        Stroke stroke = gr.getStroke();

        gr.setColor(LINE_COLOR);
        gr.setStroke(new BasicStroke(20));
        RoundRectangle2D roundRectangle2D = new RoundRectangle2D.Double(DISTANCE_FROM_LEFT_COEFF * width,
                DISTANCE_FROM_BOTTOM_COEFF * height, width * 1.2, height * 0.15, 50 + 0.1 * width, 360);
        gr.draw(roundRectangle2D);
        gr.setStroke(stroke);

        gr.setColor(INNER_LINE_COLOR);
        int step = (int) (INNER_LINE_RECT_DISTANCE_COEFF * width);
        for (int i = 0; i * step + DISTANCE_FROM_LEFT_COEFF * width < (-DISTANCE_FROM_LEFT_COEFF + 1) * width; i++) {
            gr.fillRect((int) (DISTANCE_FROM_LEFT_COEFF * width + i * step) + innerLineStartOffset,
                    (int) (DISTANCE_FROM_BOTTOM_COEFF * height + 10), (int) (width * 0.01), (int) (height * 0.005));
            gr.fillRect((int) (DISTANCE_FROM_LEFT_COEFF * width + i * step) - innerLineStartOffset,
                    (int) ((DISTANCE_FROM_BOTTOM_COEFF + 0.15) * height - 10), (int) (width * 0.01), (int) (height * 0.005));
        }
    }

    private void paintLegs(Graphics2D gr) {
        gr.setColor(LEG_COLOR);

        gr.fillRect((int) ((DISTANCE_FROM_LEFT_COEFF + 0.02) * width), (int) (DISTANCE_FROM_BOTTOM_COEFF * height + height * 0.15),
                (int) (width * 1.2), (int) (height * 0.05));

        int step = width / (LEGS_COUNT - 1);
        for (int i = 0; i < LEGS_COUNT; i++) {
            gr.setColor(CIRCLE_COLOR);
            gr.fillOval((int) ((DISTANCE_FROM_LEFT_COEFF - 0.01) * width + step * i - (height * 0.15 - 20) / 2),
                    (int) (DISTANCE_FROM_BOTTOM_COEFF * height), (int) (width * 0.1), (int) (height * 0.15));
            gr.setColor(LEG_COLOR);
            gr.fillRect((int) ((DISTANCE_FROM_LEFT_COEFF - 0.01) * width + step * i), (int) (DISTANCE_FROM_BOTTOM_COEFF * height + height * 0.15),
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
                Math.max((int) ((INNER_LINE_RECT_DISTANCE_COEFF / fps) * width), 1);
        innerLineStartOffset %= (int) (INNER_LINE_RECT_DISTANCE_COEFF * width);
    }
}
