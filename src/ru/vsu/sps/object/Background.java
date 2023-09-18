package ru.vsu.sps.object;

import java.awt.*;

public class Background implements PaintedObject, ScalableObject {
    public static final Color TOP_COLOR = new Color(12, 157, 152);
    public static final Color MID_COLOR = new Color(211, 211, 211);
    public static final Color BOTTOM_COLOR = new Color(198, 198, 198);

    public static final Color TUBE_COLOR1 = new Color(13, 128, 125);
    public static final Color TUBE_COLOR2 = new Color(10, 112, 114);
    public static final int TUBE_RINGS_COUNT = 10;

    private int width;
    private int height;

    private void paintBackground(Graphics2D gr) {
        gr.setPaint(TOP_COLOR);
        gr.fillRect(0, 0, width, height);
        gr.setPaint(MID_COLOR);
        gr.fillRect(0, (int) (height * 0.7), width, height);
        gr.setPaint(BOTTOM_COLOR);
        gr.fillRect(0, (int) (height * 0.83), width, height);
    }

    private void paintTubes(Graphics2D gr) {
        int tubeHeight = (int) (height * 0.07);
        int ringStep = (int) ((double) width / (TUBE_RINGS_COUNT + 1));

        gr.setPaint(TUBE_COLOR1);
        gr.fillRect(0, 0, width, tubeHeight);
        gr.setPaint(TUBE_COLOR2);
        for (int i = 1; i <= TUBE_RINGS_COUNT; i++) {
            gr.fillRect(i * ringStep, 0,
                    15, tubeHeight + 10);
        }
    }

    public void paint(Graphics2D gr) {
        paintBackground(gr);
        paintTubes(gr);
    }

    @Override
    public void changeWindowSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
