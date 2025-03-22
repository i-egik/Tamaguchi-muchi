package tamagotchi.ui;

public final class PixelPanel {
    public final int height;
    public final int width;
    private final boolean[] pixels;
    private boolean changed = true;

    public PixelPanel(int height, int width) {
        this.height = height;
        this.width = width;
        this.pixels = new boolean[width * height];
    }

    public boolean isChanged() {
        return changed;
    }

    public void set(int x, int y, boolean value) {
        pixels[x + y * width] = value;
        changed = true;
    }

    public boolean get(int x, int y) {
        return pixels[x + y * width];
    }

    public void reset() {
        changed = false;
    }

    public void copy(PixelPanel panel) {
        System.arraycopy(pixels, 0, panel.pixels, 0, pixels.length);
        panel.changed = true;
    }
}
