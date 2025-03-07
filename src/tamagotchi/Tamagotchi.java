package tamagotchi;

import java.util.HashMap;
import java.util.Map;

public class Tamagotchi {
    private final RangeValue health = new RangeValue(2, 10);
    private final RangeValue hanger = new RangeValue(2, 10);
    private final RangeValue energy = new RangeValue(2, 10);
    private final RangeValue happiness = new RangeValue(2, 10);
    private final RangeValue weight = new RangeValue(2, 10);
    //NOTICE: Возможно надо преобразовать также к RangeValue
    private boolean ill = false;
    private boolean dirty = false;
    Map<Stats, Integer> tamagochiStats = new HashMap<>();

    //0         - end
    //critical  - проблемы
    //maximum   - предел нормы
    //> maximum - за нормой
    public static final class RangeValue {
        private final int critical;
        private final int maximum;
        private int value;

        public RangeValue(int critical, int maximum) {
            this.critical = critical;
            this.maximum = maximum;
        }
    }
}
