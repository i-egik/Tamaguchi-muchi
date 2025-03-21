package tamagotchi;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class GameCircle {
    private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    private final Ticker ticker;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public GameCircle(Ticker ticker) {
        this.ticker = ticker;
        AtomicInteger counter = new AtomicInteger(0);
        service.scheduleWithFixedDelay(() -> {
            if (running.get()) {
                ticker.tickSecond();
                counter.incrementAndGet();
                if (counter.intValue() == 5 * 60) {
                    ticker.tick5Mins();
                    counter.set(0);
                }
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    public void start() {
        running.set(true);
    }

    public void pause() {
        running.set(false);
    }

    public interface Ticker {
        void tickSecond();

        void tick5Mins();
    }
}
