package tamagotchi;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class GameCircle {
    private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    private final Ticker ticker;

    public GameCircle(Ticker ticker) {
        this.ticker = ticker;
    }

    public void start() {//TODO
    }

    public void pouse() {//TODO
    }

    public interface Ticker {
        void tickSecond();

        void tick5Mins();
    }
}
