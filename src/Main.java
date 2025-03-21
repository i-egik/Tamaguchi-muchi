import tamagotchi.GameCircle;
import tamagotchi.MainMenu;
import tamagotchi.Tamagotchi;

public class Main {
    public static void main(String[] args) {
        Tamagotchi tamagotchi = new Tamagotchi();
        GameCircle circle = new GameCircle(tamagotchi);
        MainMenu mainMenu = new MainMenu();
        circle.start();
        mainMenu.start(tamagotchi, tamagotchi);
    }
}
//TODO: реализовть: , , игрища
