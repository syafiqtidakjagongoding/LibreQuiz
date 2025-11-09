package helper;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image image;
    private int margin;
    private boolean cover;

    public BackgroundPanel(Image image, boolean cover, int margin) {
        this.image = image;
        this.cover = cover;
        this.margin = margin;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int panelWidth = getWidth() - 2 * margin;   // sisakan margin kiri + kanan
            int panelHeight = getHeight() - 2 * margin; // sisakan margin atas + bawah
            int imgWidth = image.getWidth(null);
            int imgHeight = image.getHeight(null);

            // hitung rasio gambar vs panel
            double scaleX = (double) panelWidth / imgWidth;
            double scaleY = (double) panelHeight / imgHeight;
            double scale =  Math.min(scaleX, scaleY);

            int newWidth = (int) (imgWidth * scale);
            int newHeight = (int) (imgHeight * scale);

            // center supaya rapi
            int x = (panelWidth - newWidth) / 2;
            int y = (panelHeight - newHeight) / 2;

            g.drawImage(image, x, y, newWidth, newHeight, this);
        }
    }
}
