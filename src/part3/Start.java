package part3;

import enums.Type;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static enums.Type.*;

public class Start {

    public static final List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, MEAT),
            new Dish("beef", false, 700, MEAT),
            new Dish("chicken", false, 400, MEAT),
            new Dish("french fries", true, 530, OTHER),
            new Dish("rice", true, 350, OTHER),
            new Dish("season fruit", true, 120, OTHER),
            new Dish("pizza", true, 550, OTHER),
            new Dish("prawns", false, 400, FISH),
            new Dish("salmon", false, 450, FISH)
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

    public void exam5() {
        menu.stream()
                .filter(Dish::isVegetarian)
                .map(Dish::getName)
                .forEach(System.out::println);
    }

    public void exam6() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i%2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    public void exam7() {

        menu.stream()
                .sorted(Comparator.comparing(Dish::getCalories))
                .dropWhile(dish -> dish.getCalories() < 500)
                .map(Dish::getCalories)
                .forEach(System.out::println);

    }

    public void exam8() {

        menu.stream()
                .filter( dish -> dish.getCalories() > 300 )
                .limit(3)
                .map(Dish::getCalories)
                .forEach(System.out::println);

    }

    public void exam9() {
        menu.stream()
                .filter( dish -> dish.getType() == MEAT )
                .skip(1)
                .map(Dish::getType)
                .forEach(System.out::println);
    }

    public void exam10() {
        menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .forEach(System.out::println);
    }

    public void exam11() {
        List<String> words = Arrays.asList("Hello", "World");
        List<String[]> collect = words.stream()
                .map(word -> word.split(""))
                .collect(Collectors.toList());
        collect.stream()
                .forEach(strings -> Arrays.stream(strings).forEach(System.out::println));

    }

    public void exam12() {
        List<String> words = Arrays.asList("Hello", "World");
        words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::print);

    }

    public void exam13() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

        // 제곱근 반환
        nums.stream()
                .map(x->x*x)
                .forEach(System.out::println);

    }

    public void exam14() {
        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(3, 4);

        num1.stream()
                .flatMap(i -> num2.stream().map(j -> ("[" + i + "," + j + "]") ))
                .forEach(System.out::print);
    }

    public void exam15() {
        // 합이 3으로 나누어 떨어지는 쌍만 반환

        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(3, 4);

        num1.stream()
                .flatMap(
                        i -> num2.stream()
                                .filter( j -> (i + j) % 3 == 0 )
                                .map( k->"[" + i + "," + k + "]" )

                )
                .forEach(System.out::print);
    }

    public void exam16() {

        if ( menu.stream().anyMatch(Dish::isVegetarian) )
            System.out.println("inininin~");

    }

    public void exam17() {
        System.out.println(
                menu.stream().allMatch(dish -> dish.getCalories() < 500)
        );
    }

    public void exam18() {
        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(dish -> System.out.println(dish.getName()));
    }

    public void exam19() {

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(
                nums.stream().reduce(0, (a, b)->a+b)
        );

        System.out.println(
                nums.stream().reduce(0, Integer::sum)
        );

        System.out.println(
                nums.stream().reduce(1, (a, b)-> a*b)
        );

        System.out.println(
                menu.stream()
                        .map(d->1)
                        .reduce(0, (a, b)->a+b)
        );

    }

    public void exam20() {
        int sum = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        System.out.println(sum);
    }

    public void exam21() {

        OptionalInt maxCals = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();

        System.out.println(maxCals.orElse(-1));

    }

    public void exam22() {

        IntStream evenNums = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(evenNums.count());

    }

    public static void main(String[] args) {

        Start start = new Start();
        start.exam22();

    }

}
