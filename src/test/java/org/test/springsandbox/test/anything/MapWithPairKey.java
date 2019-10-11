package org.test.springsandbox.test.anything;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class MapWithPairKey {

    public enum First {
        A, B, C
    }

    public enum Second {
        X, Y, Z
    }

    @Test
    public void testPairKey() {
        Pair<First, Second> one = Pair.of(First.A, Second.Z);
        Pair<First, Second> two = Pair.of(First.B, Second.Z);
        Pair<First, Second> three = Pair.of(First.A, Second.Z);

        Assert.assertNotEquals(one, two);
        Assert.assertEquals(one, three);

        Map<Pair<First, Second>, String> pairKeyedMap = new HashMap<>();
        pairKeyedMap.put(one, "one");
        pairKeyedMap.put(two, "two");

        // get with other similar(three) key
        Assert.assertEquals("one", pairKeyedMap.get(three));
    }
}
