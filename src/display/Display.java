package display;

import java.awt.*;
import javax.swing.*;

public class Display {
    private String title;
    private int width;
    private int height;
    private JFrame frame;
    private Canvas canvas;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    public void createDisplay() {
        frame = new JFrame(title);
        canvas = new Canvas();
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);
        ImageIcon image = new ImageIcon(getClass().getResource("/textures/Icon.png"));
        frame.setIconImage(image.getImage());

        canvas.setSize(width, height);
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));

        frame.add(canvas);
        canvas.setFocusable(false);
        frame.setFocusable(true);
        frame.pack();
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
