package org.test.springsandbox.test.anything;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class MutableClassKeyedMapTest {
    public static void main(String[] args) {
        MyClass first = new MyClass(1);
        MyClass second = new MyClass(2);

        Map<MyClass, Integer> myMap = new HashMap<>();

        myMap.put(first, 10);
        myMap.put(second, 20);

        System.out.println(myMap.get(first));
        System.out.println(myMap.get(second));

        // меняю состояние ключа
        first.setAge(5);

        // данные потеряны
        System.out.println(myMap.get(first));

        // но вот так обратно можно вернуть
        first.setAge(1);
        System.out.println(myMap.get(first));
    }

    @AllArgsConstructor
    static class MyClass {

        @Getter @Setter
        private int age;

        @Override
        public int hashCode() {
            return age;
        }

        @Override
        public boolean equals(Object object) {
            return  object instanceof MyClass  &&
                    this.age == ((MyClass) object).age;
        }
    }
}


