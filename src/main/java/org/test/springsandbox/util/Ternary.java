package org.test.springsandbox.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.test.springsandbox.exception.RecordNotFoundException;

public final class Ternary {

    private Ternary() {}

    public static <T> T getOrDefault(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    public static <V, R> R executeIfNotNull(V value, Function<V, R> nonNullAction) {
        return (value != null)
                ? nonNullAction.apply(value)
                : null;
    }

    public static <V, R> R executeIf(V value, Function<V, R> nonNullAction, Supplier<R> nullAction) {
        return (value != null)
                ? nonNullAction.apply(value)
                : nullAction.get();
    }

    public static <K, V> V getOrThrow(Map<K, V> map, K key) {
        String message = "Required field '" + key + "' not found";
        return getOrThrow(map, key, message);
    }

    public static <K, V> V safeGet(Map<K, V> map, K key) {
        if (map == null || key == null) {
            return null;
        }

        return map.get(key);
    }


    public static <K, V> V getOrThrow(Map<K, V> map, K key, String message) {
        V result = map.get(key);
        if (result == null) {
            throw new RequiredFieldNotFoundException(message);
        }

        return result;
    }

    public static <R, I> R getOrThrow(JpaRepository<R, I> repository, I id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Requested record with ID:" + id + " not found"));
    }

    public static <R, K, V> Map<K, V> createMap(
            Collection<R> collection,
            Function<R, K> keyMapper,
            Function<R, V> valueMapper) {

        return  (collection == null || collection.isEmpty())
                ? Collections.emptyMap()
                : collection.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }
}
