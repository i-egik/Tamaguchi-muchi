package tamagotchi.ui;

import java.util.Arrays;

public final class PixelPanel {
    public final int height;
    public final int width;
    private final boolean[] pixels;
    private boolean changed = true;

    enum Position {
        CENTRAL,
        LEFT,
        RIGHT,
        DOWN,
        UP;

        private Coordinate getCoordinate(PixelPanel source, PixelPanel target) {
            switch (this) {
                case CENTRAL:
                    return new Coordinate(target.width / 2 - source.width / 2, target.height / 2 - source.height / 2) ;
                case LEFT:
                    return new Coordinate(0, target.height / 2 - source.height / 2) ;
                case RIGHT:
                    return new Coordinate(target.width - source.width, target.height / 2 - source.height / 2);
                case DOWN:
                    return new Coordinate(target.width / 2 - source.width / 2, target.height - source.height) ;
                case UP:
                    return new Coordinate(target.width / 2 - source.width / 2, 0) ; //FIXME: не отступает 1 пиксель сверху
                default:
                    break;
            }
            throw new UnsupportedOperationException("unhandled position \"this\"");
        }
    }

    private record Coordinate (int x, int y) {}

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

    public void clean() {
        Arrays.fill(pixels, false);
        changed = true;
    }

    public void copy(PixelPanel panel) {
        System.arraycopy(pixels, 0, panel.pixels, 0, pixels.length);
        panel.changed = true;
    }

    public void insert(PixelPanel into, Position position) {
        var offset = position.getCoordinate(this, into);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                into.set(x + offset.x, y + offset.y, get(x, y));
            }
        }
    }
}


