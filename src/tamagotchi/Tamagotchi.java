package tamagotchi;

public class Tamagotchi implements Control, Model, GameCircle.Ticker {
    private final RangeValue health = new RangeValue(2, 10);
    private final RangeValue hunger = new RangeValue(2, 10);
    private final RangeValue energy = new RangeValue(2, 10);
    private final RangeValue happiness = new RangeValue(2, 10);
    private final RangeValue weight = new RangeValue(2, 10);
    private final RangeValue dirty = new RangeValue(6, 10);
    private final RangeCounter cHunger = new RangeCounter(1, hunger);

    //NOTICE: Возможно надо преобразовать также к RangeValue
    private boolean ill = false;
    private boolean isSleep = false;

    private static final class RangeCounter {
        private final int barier;
        private final RangeValue value;
        private int counter;

        private RangeCounter(int barier, RangeValue value) {
            this.barier = barier;
            this.value = value;
        }

        private void reset() {
            this.counter = 0;
        }

        private void tick() {
            this.counter++;
            if (counter > barier) {
                reset();
                value.decrement();
            }
        }
    }

    @Override
    public void tickSecond() {

    }

    @Override
    public void tick5Mins() {
        cHunger.tick();
        if (hunger.isCritical() || ill) {
            health.decrement();
        }

        if (hunger.isMoreMaximum()) {
            weight.increment();
        }

        if (dirty.isCritical() || weight.isMoreMaximum() || weight.isCritical()) {
            ill = true;
        }

        if (isSleep) {
            energy.increment();
        }

        if (happiness.isMoreMaximum()) {
            energy.decrement();
        }
    }

    public Tamagotchi() {
        health.value = 5;
        hunger.value = 5;
        energy.value = 5;
        happiness.value = 5;
        weight.value = 5;
        dirty.value = 10;
    }

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
        isSleep = true;
    }

    @Override
    public void toHeal() {
        if (health.value < health.maximum) {
            health.increment();
        }
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

        public boolean isCritical() {
            return value <= critical;
        }

        public boolean isMoreMaximum() {
            return value > maximum;
        }

        public void increment() {
            incrementDelta(1);
        }

        public void incrementDelta(int delta) {
            value += delta;
        }

        public void toBeHunger(RangeValue hunger) {

        }

        public void decrement() {
            value--;
        }
    }
}
