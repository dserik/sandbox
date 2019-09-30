package org.test.springsandbox;

import org.junit.After;
import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;


public class ReactorTest {

    private static Subscription sub;

    @Test
    public void firstTest() {
        Flux<Long> ints = Flux
                .interval(Duration.ofSeconds(1));

        ints.subscribe(number -> {
                    if (number == 4) {
                        sub.cancel();
                    }
                    System.out.println(number);
                },
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"),
                subscription -> sub = subscription);

        sub.request(20);
    }

    @Test
    public void secondTest() {
        Flux<String> fastClock = Flux.interval(Duration.ofSeconds(1)).map(tick -> "fast " + tick);
        Flux<String> slowClock = Flux.interval(Duration.ofSeconds(2)).map(tick -> "slow " + tick);

        Flux<String> merge = Flux.merge(fastClock, slowClock);

        Flux<LocalTime> feed = Flux.interval(Duration.ofSeconds(1)).map(tick -> LocalTime.now());

        merge.zipWith(feed, (tick, time) -> tick + " " + time)
                .subscribe(System.out::println);

    }

    @After
    public void after() throws InterruptedException {
        TimeUnit.SECONDS.sleep(7);
    }


}
