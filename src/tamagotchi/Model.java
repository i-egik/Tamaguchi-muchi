package tamagotchi;

public interface Model {
    record Status(int health, int hanger, int energy, int happiness, boolean fatty, boolean dirty) {
    }
    Status getStatus();
}
