package part3;

import enums.Type;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static enums.Type.*;
import static java.util.stream.Collectors.*;
import static part3.Start.CalLevels.*;

public class Start {

    enum CalLevels { DIET, NORMAL, FAT }

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

    public void exam23() {
        IntStream.rangeClosed(1, 100)
                        .boxed()
                                .flatMap(
                                        a -> IntStream.rangeClosed(a, 100)
                                                .filter(b-> Math.sqrt(a*a + b*b) % 1 == 0)
                                                .mapToObj(b -> new int[]{a, b, (int)Math.sqrt( (a*a) + (b*b) )})
                                ).forEach( v -> System.out.println( v[0] + " : " + v[1] + " : " + v[2] ));
    }

    public void exam24() {
        Stream<String> stream = Stream.of("Modern", "Java", "In", "Action");
        stream.forEach(System.out::println);
    }

    public void exam25() {

        String home = System.getProperty("home");
        System.out.println(home);

        Stream<String> home1 = Stream.ofNullable(System.getProperty("home"));
        home1.forEach(System.out::println);

    }

    public void exam26() {
        Stream.iterate(0, n->n+2)
                .limit(10)
                .forEach(System.out::println);
    }

    public void exam27() {
        Stream.iterate( new int[]{0, 1}, ar-> new int[]{ar[1], ar[0] + ar[1]} )
                .limit(20)
                .forEach( t -> System.out.println( "( " + t[0] + ", " + t[1] + " )") );
    }

    public void exam28() {
        Stream.iterate( 0, n->n<100, n->n+4 )
                .forEach(System.out::println);
    }

    public void exam29() {
        Stream.iterate(0, n->n+4)
                .filter( n->n<100 )
                .forEach(System.out::println);
    }

    public void exam30() {
        Stream.iterate(0, n->n+4)
                .takeWhile(n->n<100)
                .forEach(System.out::println);
    }

    public void exam31() {
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);
    }

    public void exam32() {
        System.out.println(menu.stream().count());
    }

    public void exam33() {
        Comparator<Dish> comparing = Comparator.comparing(Dish::getCalories);
        menu.stream()
                .collect( Collectors.maxBy(comparing) )
                .ifPresent(System.out::println);
    }

    public void exam34() {
        int collect = menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(collect);

        double collect1 = menu.stream()
                .collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println((int)collect1);
    }

    public void exam35() {
        IntSummaryStatistics collect = menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));

        System.out.println(collect);
    }

    public void exam36() {
        String collect = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(", "));
        System.out.println(collect);
    }

    public void exam37() {
        int sumVal = menu.stream()
                .collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(sumVal);
    }

    public void exam38() {
        menu.stream()
                .reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)
                .ifPresent(dish -> System.out.println(dish));
    }

    public void exam39() {
        int sumVal = menu.stream()
                .collect(
                        reducing(
                                0, Dish::getCalories, Integer::max
                        )
                );
        System.out.println(sumVal);
    }

    public void exam40() {
        int resVal = menu.stream()
                .map(Dish::getCalories)
                .reduce(Integer::sum).get();
        System.out.println(resVal);

        int sumVal = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(sumVal);
    }

    public void exam41() {
        Map<Type, List<Dish>> dishesType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));

        System.out.println(dishesType);
    }

    public void exam42() {
        Map<CalLevels, List<Dish>> resMap = menu.stream()
                .collect(
                        Collectors.groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return DIET;
                            else if (dish.getCalories() <= 700) return NORMAL;
                            else return FAT;
                        })
                );
        System.out.println(resMap);
    }

    public void exam43() {
        Map<Type, List<Dish>> collect = menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .collect(groupingBy(Dish::getType));

        System.out.println(collect);

        Map<Type, List<Dish>> collect1 = menu.stream()
                .collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));

        System.out.println(collect1);

    }

    public static void main(String[] args) {

        Start start = new Start();
        start.exam43();

    }

}
