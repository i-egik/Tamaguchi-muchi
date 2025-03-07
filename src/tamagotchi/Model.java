package tamagotchi;

public interface Model {
    record Status(int health, int hanger, int energy, int happyness, boolean fatty, boolean dirty) {
    }
    Status getStatus();
}
