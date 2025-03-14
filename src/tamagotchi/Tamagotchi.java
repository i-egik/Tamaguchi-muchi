package tamagotchi;

import java.util.HashMap;
import java.util.Map;

public class Tamagotchi implements Control, Model {
    private final RangeValue health = new RangeValue(2, 10);
    private final RangeValue hunger = new RangeValue(2, 10);
    private final RangeValue energy = new RangeValue(2, 10);
    private final RangeValue happiness = new RangeValue(2, 10);
    private final RangeValue weight = new RangeValue(2, 10);
    private final RangeValue dirty = new RangeValue(6, 10);
    //NOTICE: Возможно надо преобразовать также к RangeValue
    private boolean ill = false;

    @Override
    public void toFeed(Food food) {
        Food.Value value = food.value();
        health.incrementDelta(value.heals());
        hunger.incrementDelta(value.hunger());
        weight.incrementDelta(value.weight());
        happiness.incrementDelta(value.happiness());
    }

    @Override
    public void toClean() {
        if (dirty.value < dirty.maximum) {
            dirty.incrementDelta(5);
        }
    }

    @Override
    public void toPlay(Play play) {
        if (happiness.value < happiness.maximum) {
            happiness.increment();
        }
    }

    //FIXME: Сделать шедулер на 1 минуту и в течение 1 минуты смотреть спим или не спим
    @Override
    public void toSleep() {
        if (energy.value < energy.maximum) {

        }
    }

    @Override
    public void toHeal() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Status getStatus() {
        return new Status(health.value, hunger.value, energy.value, happiness.value, weight.value > weight.maximum, dirty.value < dirty.critical);
    }

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

        public void increment() {
            incrementDelta(1);
        }

        public void incrementDelta(int delta) {
            value += delta;
        }
    }
}
