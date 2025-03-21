import tamagotchi.MainMenu;
import tamagotchi.Tamagotchi;

public class Main {
    public static void main(String[] args) {
        Tamagotchi tamagotchi = new Tamagotchi();
        MainMenu mainMenu = new MainMenu();
        mainMenu.start(tamagotchi, tamagotchi);
    }
}
//TODO: реализовть: жрачку, болячку, игрища, и засирание
