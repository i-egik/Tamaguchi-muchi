package tamagotchi;

import java.util.Scanner;
import java.util.function.Consumer;

public class MainMenu {
    private static final Menu[] MENUS = new Menu[]{
            new Menu("healing", Control::toHeal),
            new Menu("clearing", Control::toClean),
            new Menu("feed"),
            new Menu("games"),
            new Menu("sleep", Control::toSleep),
    };

    public void start(Control control, Model model) {
        System.out.println("Я родилься");
        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                Model.Status status = model.getStatus();
                System.out.printf("| Ht | Hg | Hs | Eg | Dt | Ft |\n");
                System.out.printf("|%3d |%3d |%3d |%3d | %s  | %s  |\n",
                        status.health(), status.hanger(), status.happiness(),
                        status.energy(), status.dirty() ? "D" : "N", status.fatty() ? "F" : "N");
                System.out.println("0. exit");
                for (int i = 0; i < MENUS.length; i++) {
                    System.out.printf("%d. %s\n", i + 1, MENUS[i].name);
                }
                int n = input.nextInt();
                if (n == 0) {
                    return;
                }
                MENUS[n - 1].action(input, control);
            }
        }
    }

    private static final class Menu {
        private final String name;
        private final Consumer<Control> action;
        private final Item[] items;

        private Menu(String name, Item... items) {
            this.name = name;
            this.items = items;
            this.action = null;
        }

        private Menu(String name, Consumer<Control> action) {
            this.name = name;
            this.action = action;
            this.items = null;
        }

        private void action(Scanner scanner, Control control) {
            if (this.action != null) {
                this.action.accept(control);
            } else if (items != null) {
                System.out.println("0. up");
                for (int i = 0; i < items.length; i++) {
                    System.out.printf("%d. %s\n", i + 1, items[i].name);
                }
                int n = scanner.nextInt();
                if (n == 0) {
                    return;
                }
                items[n - 1].action.accept(control);
            }
        }

        private record Item(String name, Consumer<Control> action) {
        }
    }
}
