package Tamagochi;

public class Stats {
    public enum stats{
        HEALTH(), //по умолчанию 100. если упадает в 0, пит помирает
        HANGER(), //по умолчанию 100, если упадёт в 0, то пит начинает терять здоровье по 5хп в минуту
        ENERGY(), //по уполчанию 100, если падает в 0, то пит вырубается спать самили теряет хп
        HAPPYNES(); // по умолчанию 100, если падает в 0, пит уходит в запой
    }

}
