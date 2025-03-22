package tamagotchi.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

public final class MainWindow extends JFrame {

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

        DrawingPanel drawingPanel = new DrawingPanel(pixelPanel);
        add(drawingPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        select.addActionListener(e -> {
            //TODO:
            PixelPanel[] pixelPanels = Sprite.EGG.pixelPanels();
            pixelPanels[0].copy(pixelPanel);
            drawingPanel.repaint();
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
        SwingUtilities.invokeLater(() -> new MainWindow(pixelPanel).setVisible(true));
    }

    public interface DrawingImage {
        void repaint();
    }

}
