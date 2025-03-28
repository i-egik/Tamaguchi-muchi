package tamagotchi.ui;

import tamagotchi.GameCircle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.util.concurrent.atomic.AtomicInteger;

public final class MainWindow extends JFrame {
    private final DrawingPanel drawingPanel;

    public MainWindow(PixelPanel pixelPanel) {
        setTitle("Tamagotchi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 230);
        setLocationRelativeTo(null);
        setResizable(false);

        JButton select = new JButton("A"); //Select
        JButton execute = new JButton("B"); //Execute
        JButton cancel = new JButton("C"); //Cancel

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(select);
        buttonPanel.add(execute);
        buttonPanel.add(cancel);

        drawingPanel = new DrawingPanel(pixelPanel);
        add(drawingPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        select.addActionListener(e -> {
            //TODO:
        });

        execute.addActionListener(e -> {
            //TODO:
        });

        cancel.addActionListener(e -> {
            //TODO:
        });
    }

    public static void main(String[] args) {
        PixelPanel pixelPanel = new PixelPanel(16, 32);
        PixelPanel[] pixelPanels = Sprite.EGG.pixelPanels();
        AtomicInteger counter = new AtomicInteger(0);
        MainWindow window = new MainWindow(pixelPanel);
        GameCircle circle = new GameCircle(new GameCircle.Ticker() {
            @Override
            public void tick() {

            }

            @Override
            public void signal() {
                pixelPanels[counter.intValue()].copy(pixelPanel);
                counter.incrementAndGet();
                if (counter.intValue() > 1) {
                    counter.set(0);
                }
                window.update();
            }
        });
        circle.start();

        SwingUtilities.invokeLater(() -> window.setVisible(true));
    }

    public void update() {
        drawingPanel.repaint();
    }

    public interface DrawingImage {
        void repaint();
    }

}
