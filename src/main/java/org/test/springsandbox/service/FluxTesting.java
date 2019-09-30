package org.test.springsandbox.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.*;
import java.util.function.Function;

@Service
public class FluxTesting {

    public static final Set<String> NAMES = new LinkedHashSet<>(Arrays.asList(
            "Prohor", "Petrovick", "Victor", "Simon", "Rick", "Morty", "Beth", "Jerry", "Summer"
    ));

    public Flux<Integer> twelveToZero() {
        return Flux.range(0, 13)
                .map(number -> 12 - number).checkpoint("numbers");
    }

    public Flux<Integer> operateOnTwelveToZero(Function<Integer, Integer> operation) {
        return twelveToZero().map(operation).checkpoint("mapped");
    }

    public Flux<String> namesPer200Millisecond() {
        List<String> randomizedNames = new ArrayList<>(NAMES);
        Collections.reverse(randomizedNames);

        return Flux.fromIterable(randomizedNames)
                .delayElements(Duration.ofMillis(200))
                .checkpoint("timed");
    }
}
