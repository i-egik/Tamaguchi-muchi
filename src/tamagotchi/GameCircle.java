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
                counter.incrementAndGet();
                if (counter.intValue() == 5 * 60) {
                    ticker.tick();
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

    public void toggle(){
        if(running.get()){
            pause();
        } else {
            start();
        }
    }

    public interface Ticker {
        void tick();
    }

}
