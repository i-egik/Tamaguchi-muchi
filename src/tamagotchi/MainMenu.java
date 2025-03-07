package tamagotchi;

import java.util.Scanner;

public class MainMenu {
    public void start() {
        System.out.println("Время яиц!!!!");
        Scanner input = new Scanner(System.in);
        String inputer = " ";
        while (!"exit".equals(inputer)) {
            inputer = input.nextLine().trim().toLowerCase();
        }
    }
    //вылечить нет действия
    // помыть нет действия
    // покормить -- выбор жрачки
    // поиграть -- выбор игры

    //вылечить  1
    // помыть 2
    // покормить снеками 3
    // покормить норм хавкой 4
    // поиграть в угадай число 5
    //поиграть в литрболл  6
    //......
}
