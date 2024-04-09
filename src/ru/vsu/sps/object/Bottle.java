package ru.vsu.sps.object;

import java.awt.*;

public class Bottle implements ScalableObject, PaintedObject, AnimatedObject {
    public static final Color
            GLASS_COLOR = new Color(143, 195, 208),
            CAP_COLOR = Color.BLACK;
    public static double SPEED_COEFF = 0.002, MAIN_BODY_WIDTH_COEFF = 0.03, MAIN_BODY_HEIGHT_COEFF = 0.07,
    CAP_WIDTH_COEFF = 0.015, CAP_HEIGHT_COEFF = 0.035;
    private int windowWidth, windowHeight;
    private double xOffsetCoeff;

    public Bottle(double beginXCoeff) {
        xOffsetCoeff = beginXCoeff;
    }


    @Override
    public void paint(Graphics2D gr) {
        gr.setColor(CAP_COLOR);
        int arc = (int) (windowWidth * CAP_WIDTH_COEFF / 3);
        gr.fillRoundRect((int) ((xOffsetCoeff + MAIN_BODY_WIDTH_COEFF / 4) * windowWidth),
                (int) (windowHeight * (AssemblyLine.DISTANCE_FROM_BOTTOM_COEFF - MAIN_BODY_HEIGHT_COEFF - CAP_HEIGHT_COEFF + 0.01)),
                (int) (CAP_WIDTH_COEFF * windowWidth), (int) (windowHeight * CAP_HEIGHT_COEFF),
                arc, arc);

        gr.setColor(GLASS_COLOR);
        arc = (int) (windowWidth * MAIN_BODY_WIDTH_COEFF / 3);
        gr.fillRoundRect((int) (xOffsetCoeff * windowWidth),
                (int) (windowHeight * (AssemblyLine.DISTANCE_FROM_BOTTOM_COEFF - MAIN_BODY_HEIGHT_COEFF)),
                (int) (MAIN_BODY_WIDTH_COEFF * windowWidth), (int) (windowHeight * MAIN_BODY_HEIGHT_COEFF),
                arc, arc);
    }

    @Override
    public void changeWindowSize(int width, int height) {
        windowHeight = height;
        windowWidth = width;
    }

    @Override
    public void next(int fps) {
        xOffsetCoeff += SPEED_COEFF * (25. / fps);
        if (isEndOfMove()) {
            xOffsetCoeff = -MAIN_BODY_WIDTH_COEFF;
        }
    }

    private boolean isEndOfMove() {
        return xOffsetCoeff > ControlPanel.DISTANCE_FROM_LEFT_COEFF;
    }
}
