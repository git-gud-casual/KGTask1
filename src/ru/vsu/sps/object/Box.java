package ru.vsu.sps.object;

import java.awt.*;

public class Box implements ScalableObject, PaintedObject, AnimatedObject {
    private int windowWidth, windowHeight;
    public static final Color MAIN_COLOR = new Color(251, 196, 105),
                              SHADOW_COLOR = new Color(240, 184, 87);
    public static final double
            WIDTH_COEFF = 0.1,
            SPEED_COEFF = 0.002,
            HEIGHT_COEFF = 0.14,
            SHADOW_PART_WIDTH_COEFF = 0.03;
    private double xOffsetCoeff = 0;

    @Override
    public void paint(Graphics2D gr) {
        gr.setColor(MAIN_COLOR);
        int x = (int) ((ControlPanel.DISTANCE_FROM_LEFT_COEFF + xOffsetCoeff + ControlPanel.WIDTH_COEFF - WIDTH_COEFF) * windowWidth),
            y = (int) (windowHeight * AssemblyLine.DISTANCE_FROM_BOTTOM_COEFF - windowHeight * HEIGHT_COEFF);
        gr.fillRect(x, y,
                (int) (windowWidth * WIDTH_COEFF), (int) (windowHeight * HEIGHT_COEFF));

        gr.setColor(SHADOW_COLOR);
        gr.fillRect(x, y,
                (int) (windowWidth * SHADOW_PART_WIDTH_COEFF), (int) (windowHeight * HEIGHT_COEFF));
    }

    @Override
    public void changeWindowSize(int width, int height) {
        windowWidth = width;
        windowHeight = height;
    }

    @Override
    public void next(int fps) {
        xOffsetCoeff += SPEED_COEFF * (25. / fps);
        if (!onDisplay()) {
            xOffsetCoeff = 0;
        }
    }

    private boolean onDisplay() {
        return ControlPanel.DISTANCE_FROM_LEFT_COEFF + xOffsetCoeff + ControlPanel.WIDTH_COEFF - WIDTH_COEFF <= 1.;
    }
}
