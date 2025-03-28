package tamagotchi.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public enum Sprite {
    EGG("egg", 1, 2);
    private final String name;
    private final int[] nums;

    Sprite(String name, int... nums) {
        this.name = name;
        this.nums = nums;
    }

    public PixelPanel[] pixelPanels() {
        PixelPanel[] pixelPanels = new PixelPanel[nums.length];
        for (int i = 0; i < nums.length; i++) {
            try (var stream = Sprite.class.getResourceAsStream(String.format("/sprites/%s_%d.txt", name, nums[i]));
                 var reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(stream)))) {
                PixelPanel panel = new PixelPanel(16, 32);
                for (int k = 0; k < panel.height; k++) {
                    String line = reader.readLine();
                    for (int j = 0; j < panel.width; j++) {
                        panel.set(j, k, line.charAt(j) == '1');
                    }
                }
                pixelPanels[i] = panel;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return pixelPanels;
    }
}
