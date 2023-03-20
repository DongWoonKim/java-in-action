package part2;

import enums.AppleColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Start {

    public static List<Apple2> filterApples(List<Apple2> inventory, ApplePredicate predicate) {

        List<Apple2> result = new ArrayList<>();
        for (Apple2 apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }

        return result;
    }

    public static <T>List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if ( p.test(t) ) {
                result.add(t);
            }
        }

        return result;
    }

    public static void main(String[] args) {

        List<Apple2> inventory = Arrays.asList(
                new Apple2(AppleColor.GREEN.name(), 100)
                , new Apple2(AppleColor.GREEN.name(), 160)
                , new Apple2(AppleColor.RED.name(), 100)
                , new Apple2(AppleColor.RED.name(), 160)
        );

        ApplePredicate applePredicate = new AppleGreenColorPredicate();

        List<Apple2> apples = filterApples(inventory, new AppleGreenColorPredicate());
        for (Apple2 apple : apples) {
            System.out.println(apple.getColor() + " : " + apple.getWeight());
        }

        List<Apple2> apples2 = filterApples(inventory, new AppleRedAndHeavyPredicate());
        for (Apple2 apple : apples2) {
            System.out.println(apple.getColor() + " : " + apple.getWeight());
        }

        List<Apple2> apples3 = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple2 apple) {
                return AppleColor.RED.name().equals(apple.getColor());
            }
        });
        for (Apple2 apple : apples3) {
            System.out.println(apple.getColor() + " : " + apple.getWeight());
        }

        List<Apple2> apples4 = filterApples(inventory, (Apple2 apple) -> AppleColor.RED.name().equals(apple.getColor()));
        for (Apple2 apple : apples4) {
            System.out.println(apple.getColor() + " : " + apple.getWeight());
        }

        List<Apple2> apples5 = filter(inventory, (Apple2 apple) -> AppleColor.GREEN.name().equals(apple.getColor()));
        for (Apple2 apple : apples5) {
            System.out.println(apple.getColor() + " : " + apple.getWeight());
        }

        System.out.println("====");
        inventory.sort( (Apple2 a1, Apple2 a2)->a2.getWeight().compareTo(a1.getWeight()) );
        for (Apple2 apple : inventory) {
            System.out.println(apple.getColor() + " : " + apple.getWeight());
        }

    }

}
