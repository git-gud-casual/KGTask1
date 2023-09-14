package ru.vsu.sps.object;

import java.awt.*;

public class Background implements PaintedObject {
    public static final Color TOP_COLOR = new Color(12, 157, 152);
    public static final Color MID_COLOR = new Color(211, 211, 211);
    public static final Color BOTTOM_COLOR = new Color(198, 198, 198);

    private final int width;
    private final int height;

    public Background(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void paint(Graphics2D gr) {
        gr.setPaint(TOP_COLOR);
        gr.fillRect(0, 0, width, height);
        gr.setPaint(MID_COLOR);
        gr.fillRect(0, height / 2, width, height / 2);
        gr.setPaint(BOTTOM_COLOR);
        gr.fillRect(0, (int) (height * 0.75), width, height / 4);
    }
}
