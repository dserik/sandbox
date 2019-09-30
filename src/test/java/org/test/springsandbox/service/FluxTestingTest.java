package org.test.springsandbox.service;

import org.junit.Test;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Objects;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;

public class FluxTestingTest {

    private FluxTesting fluxTesting = new FluxTesting();


    @Test
    public void twelveToZero() {
        StepVerifier.create(fluxTesting.twelveToZero())
                .expectNext(12)
                .expectNext(11, 10, 9)
                .expectNextCount(5)
                .expectNext(3)
                .expectNextCount(3)
                .expectComplete()
                .verify();
    }

    @Test
    public void multiplyByTwelveToZero() {
        StepVerifier.create(fluxTesting.operateOnTwelveToZero(i -> i * 10))
                .expectSubscription()
                .expectNextCount(5)
                .assertNext(v -> assertEquals(v.longValue(), 70))
                .expectNextCount(7)
                .verifyComplete();
    }

    @Test
    public void divideByTwelveToZeroErrors() {
        StepVerifier.create(fluxTesting.operateOnTwelveToZero(i -> 10 / i))
                .expectNextCount(10)
                .expectNext(5, 10)
                .verifyErrorSatisfies(
                        e -> assertThat(e, instanceOf(ArithmeticException.class)));
    }

    @Test
    public void namesPer200Millisecond() {
        StepVerifier
                .create(fluxTesting.namesPer200Millisecond()
                        .doOnNext(System.out::println))
                .expectSubscription()
                .expectNoEvent(Duration.ofMillis(180))
                .recordWith(ArrayList::new)
                .thenConsumeWhile(Objects::nonNull)
                .consumeRecordedWith(l -> assertTrue(FluxTesting.NAMES.containsAll(l)))
                .verifyComplete();

    }

    @Test
    public void namesPer200MillisecondWithoutTime() {
        StepVerifier
                .withVirtualTime(() -> fluxTesting.namesPer200Millisecond()
                        .doOnNext(System.out::println))
                .expectSubscription()
                .expectNoEvent(Duration.ofMillis(200))
                .expectNext("Summer")
                .thenAwait(Duration.ofMillis(500))
                .expectNextCount(2)
                .thenAwait(Duration.ofSeconds(2))
                .expectNextCount(2)
                .expectNext("Simon", "Victor", "Petrovick", "Prohor")
                .verifyComplete();
    }
}