package classes;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Leon on 04.03.16.
 */
public class ImagePanel extends JPanel {

    private Image image = null;

    public ImagePanel(String filename) {
        this.image = new ImageIcon(filename).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        ImagePanel panel = new ImagePanel("resources/image.jpg");

        JFrame frame = new JFrame("Frame");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}