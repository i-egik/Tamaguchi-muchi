package tamagotchi.ui;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

final class DrawingPanel extends JPanel implements MainWindow.DrawingImage {
    private static final int FACTOR = 10;
    private final PixelPanel pixels;
    private Image buffer;
    private Graphics2D graphics;

    DrawingPanel(PixelPanel pixels) {
        this.pixels = pixels;
        setSize(pixels.width * FACTOR, pixels.height * FACTOR);
        setPreferredSize(new Dimension(pixels.width * FACTOR, pixels.height * FACTOR));
        setMinimumSize(new Dimension(pixels.width * FACTOR, pixels.height * FACTOR));
        setMaximumSize(new Dimension(pixels.width * FACTOR, pixels.height * FACTOR));
        setBounds(0, 0, pixels.width * FACTOR, pixels.height * FACTOR);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (buffer == null ||
                buffer.getWidth(null) != getWidth() ||
                buffer.getHeight(null) != getHeight()) {
            buffer = createImage(getWidth(), getHeight());
            graphics = (Graphics2D) buffer.getGraphics();
        }
        drawBuffer(graphics);
        g.drawImage(buffer, 0, 0, this);
    }

    private void drawBuffer(Graphics2D g) {
        if (pixels.isChanged()) {
            g.setColor(Color.WHITE);
            g.clearRect(0, 0, getWidth(), getHeight());

            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setStroke(new BasicStroke(1));
            g.setColor(Color.DARK_GRAY);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

            for (int x = 0; x < pixels.width; x++) {
                for (int y = 0; y < pixels.height; y++) {
                    if (pixels.get(x, y)) {
                        g.fillRect(x * FACTOR, y * FACTOR, FACTOR, FACTOR);
                    } else {
                        g.drawRect(x * FACTOR, y * FACTOR, FACTOR, FACTOR);
                    }
                }
            }
            pixels.reset();
        }
    }
}
