package ru.vsu.sps.object;

import java.awt.*;

public class ControlPanel implements PaintedObject, ScalableObject{
    public static final Color MAIN_COLOR = new Color(247, 141, 99);
    public static final Color BUTTON_COLOR = Color.WHITE;
    public static final Color DETAILS_COLOR = new Color(144, 81, 46);
    private int windowWidth, windowHeight;
    public static final double WIDTH_COEFF = 0.25, HEIGHT_COEFF = 0.52,
            DISTANCE_FROM_LEFT_COEFF = 0.4, DISTANCE_FROM_BOTTOM = 0.15;

    @Override
    public void paint(Graphics2D gr) {
        gr.setColor(MAIN_COLOR);
        int x = (int) (windowWidth * DISTANCE_FROM_LEFT_COEFF),
                height = (int) (windowHeight * HEIGHT_COEFF),
                y = (int) (windowHeight * (1 - DISTANCE_FROM_BOTTOM) - height),
                width = (int) (windowWidth * WIDTH_COEFF);
        gr.fillRect(x, y, width, height);

        gr.setColor(DETAILS_COLOR);
        int stepX = (int) (width / 5.);
        int margin = (int) (width * 0.12);
        for (int i = 0; i < 4; i++) {
            gr.fillOval(x + stepX * i + margin,
                    (int) (y + height * 0.95 - height / 2.5), (int) (width / 6.), (int) (height / 2.5));
        }

        gr.setColor(BUTTON_COLOR);
        stepX = (int) (width / 6.);
        for (int i = 0; i < 5; i++) {
            gr.fillOval(x + stepX * i + margin,
                    (int) (y + height * 0.2), (int) (width * 0.1), (int) (height * 0.05));
        }
    }

    @Override
    public void changeWindowSize(int width, int height) {
        windowWidth = width;
        windowHeight = height;
    }
}
