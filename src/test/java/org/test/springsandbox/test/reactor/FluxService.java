package org.test.springsandbox.test.reactor;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.*;
import java.util.function.Function;

class FluxService {

    static final Set<String> NAMES = new LinkedHashSet<>(Arrays.asList(
            "Prohor", "Petrovick", "Victor", "Simon", "Rick", "Morty", "Beth", "Jerry", "Summer"
    ));

    Flux<Integer> twelveToZero() {
        return Flux.range(0, 13)
                .map(number -> 12 - number).checkpoint("numbers");
    }

    Flux<Integer> operateOnTwelveToZero(Function<Integer, Integer> operation) {
        return twelveToZero().map(operation).checkpoint("mapped");
    }

    Flux<String> namesPer200Millisecond() {
        List<String> randomizedNames = new ArrayList<>(NAMES);
        Collections.reverse(randomizedNames);

        return Flux.fromIterable(randomizedNames)
                .delayElements(Duration.ofMillis(200))
                .checkpoint("timed");
    }
}
