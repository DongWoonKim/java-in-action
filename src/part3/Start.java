package part3;

import enums.Type;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Start {

    public static final List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Type.MEAT),
            new Dish("beef", false, 700, Type.MEAT),
            new Dish("chicken", false, 400, Type.MEAT),
            new Dish("french fries", true, 530, Type.OTHER),
            new Dish("rice", true, 350, Type.OTHER),
            new Dish("season fruit", true, 120, Type.OTHER),
            new Dish("pizza", true, 550, Type.OTHER),
            new Dish("prawns", false, 400, Type.FISH),
            new Dish("salmon", false, 450, Type.FISH)
    );

    public void exam1() {

        menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .forEach(System.out::println);

    }

    public void exam2() {

        List<String> strList = Arrays.asList(
                "java8"
                , "java7"
                , "java6"
        );

        Stream<String> stream = strList.stream();
        stream.forEach(System.out::println);
//        stream.forEach(System.out::println);

    }

    public void exam3() {

        List<String> hCalDishes = new ArrayList<>();
        Iterator<Dish> iterator = menu.iterator();
        while (iterator.hasNext()) {
            Dish dish = iterator.next();
            if ( dish.getCalories() > 300 ) hCalDishes.add(dish.getName());
        }

    }

    public void exam4() {

        List<String> collect = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .sorted( Comparator.comparing(Dish::getCalories) )
                .map(Dish::getName)
                .collect(Collectors.toList());

        collect.stream()
                .forEach(System.out::println);

    }

    public static void main(String[] args) {

        Start start = new Start();
        start.exam4();

    }

}
