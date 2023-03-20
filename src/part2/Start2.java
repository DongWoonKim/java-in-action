package part2;

import enums.AppleColor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class Start2 {

    static Runnable r1 = () -> System.out.println("Hello World!1");
    static Runnable r2 = new Runnable() {
        @Override
        public void run() {
            System.out.println("Hello World!2");
        }
    };

    public static void process(Runnable r) {
        r.run();
    }

    public void exam1() {
        process(r1);
        process(r2);
        process(()-> System.out.println("Hello World!3"));
    }

    public static String processFile(BufferedReaderProcessor p) throws Exception {
        try ( BufferedReader br = new BufferedReader(new FileReader("data.txt")) ) {
            return p.process(br);
        }
    }

    public static void exam2() throws Exception {
        String oneLine = processFile((br) -> br.readLine());
        String twoLine = processFile((br) -> br.readLine() + br.readLine());
    }

    public static void exam3() {
        List<String> str = Arrays.asList("a", "b", "A", "B");
//        str.sort((s1, s2)->s1.compareToIgnoreCase(s2));
        str.sort(String::compareToIgnoreCase);
        str.stream().forEach(s-> System.out.println(s));
    }

    public boolean startsWithNumber(String s) {
        return s.startsWith("^[0-9]*$");
    }

    public void exam4() {

        ToIntFunction<String> strToInt = (s)->Integer.parseInt(s);
        strToInt = Integer::parseInt;
        BiPredicate<List<String>, String> contains = (list, ele)->list.contains(ele);
        contains = List::contains;
        Predicate<String> startsWithNum = (String s) -> this.startsWithNumber(s);
        startsWithNum = this::startsWithNumber;

    }

    public void exam5() {

        Supplier<Apple2> c1 = new Supplier<Apple2>() {
            @Override
            public Apple2 get() {
                return new Apple2();
            }
        };
        Apple2 a1 = c1.get();

        Supplier<Apple2> c2 = Apple2::new;
        Apple2 a2 = c2.get();

        Function<Integer, Apple2> c3 = Apple2::new;
        Apple2 a3 = c3.apply(110);
        System.out.println(a3.getWeight());

        Function<Integer, Apple2> c4 = (w)-> new Apple2(w);
        Apple2 a4 = c4.apply(80);
        System.out.println(a4.getWeight());

        List<Integer> weights = Arrays.asList(3, 5, 7, 9);
        this.myMap(weights, Apple2::new);

        BiFunction<String, Integer, Apple2> c5 = Apple2::new;
        Apple2 a5 = c5.apply("Green", 15);
        System.out.println(a5.getColor() + " : " + a5.getWeight());

    }

    public List<Apple2> myMap(List<Integer> list, Function<Integer, Apple2> f) {
        List<Apple2> results = new ArrayList<>();
        list.stream().forEach(a->results.add( f.apply(a) ));
        return results;
    }

    public void exam6() {
        TriFunction<String, Integer, Integer, Apple2> c1 = Apple2::new;
        Apple2 a1 = c1.apply("red", 20, 1);
        System.out.println(a1.getColor() + " : " + a1.getWeight() + " : " + a1.getPart());
    }

    public static List<Apple2> filterApples(List<Apple2> inventory, ApplePredicate predicate) {

        List<Apple2> result = new ArrayList<>();
        for (Apple2 apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }

        return result;
    }

    public void exam7() {
        List<Apple2> inventory = Arrays.asList(
                new Apple2(AppleColor.GREEN.name(), 100)
                , new Apple2(AppleColor.GREEN.name(), 160)
                , new Apple2(AppleColor.RED.name(), 100)
                , new Apple2(AppleColor.RED.name(), 160)
        );

//        inventory.sort(new Apple2Comparator());
//
//        inventory.sort(new Comparator<Apple2>() {
//            @Override
//            public int compare(Apple2 o1, Apple2 o2) {
//                return o1.getWeight().compareTo(o2.getWeight());
//            }
//        });

//        inventory.sort( (Apple2 a1, Apple2 a2) -> a1.getWeight().compareTo(a2.getWeight()) );
        inventory.sort(Comparator.comparing(Apple2::getWeight));
        inventory.stream().forEach(i-> System.out.println(i.getColor() + " : " + i.getWeight()));
        System.out.println("==============================");
        inventory.sort(Comparator.comparing(Apple2::getWeight).reversed());
        inventory.stream().forEach(i-> System.out.println(i.getColor() + " : " + i.getWeight()));
        System.out.println("==============================");
        inventory.sort(
                Comparator.comparing( Apple2::getWeight )
                        .reversed()
                        .thenComparing( Apple2::getColor )
        );
        inventory.stream().forEach(i-> System.out.println(i.getColor() + " : " + i.getWeight()));
        System.out.println("==============================");
        inventory.sort(
                Comparator.comparing( Apple2::getWeight )
                        .reversed()
                        .thenComparing( Apple2::getColor )
                        .reversed()
        );
        inventory.stream().forEach(i-> System.out.println(i.getColor() + " : " + i.getWeight()));
        System.out.println("==============================");

        List<Apple2> redApples = filterApples(inventory, new AppleRedColorPredicate());

        java.util.function.Predicate<Apple2> redApple = (a)->AppleColor.RED.name().equals(a.getColor());
        Boolean isRed = redApple.test(new Apple2(AppleColor.RED.name(), 100));
        System.out.println(isRed);

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        Function<Integer, Integer> i = f.compose(g);

        Integer res = h.apply(5);
        System.out.println("res ::" + res);

        Integer res2 = i.apply((5));
        System.out.println("res ::" + res2);


    }

    public void exam8() {
        Function<String, String> letterHeader = Letter::addHeader;
        Function<String, String> res = letterHeader
                .andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);

        String resStr = res.apply("My labda example");
        System.out.println(resStr);

        Function<String, String> res2 = letterHeader
                .compose(Letter::checkSpelling)
                .compose(Letter::addFooter);
        String resStr2 = res2.apply("My labda example");
        System.out.println(resStr2);

    }

    public static void main(String[] args) {
        Start2 s2 = new Start2();
        s2.exam8();
    }
}

class Letter {

    public static String addHeader(String text) {
        return "From KDW " + text;
    }

    public static String addFooter(String text) {
        return " Kind regards " + text;
    }

    public static String checkSpelling(String text) {
        return text.replaceAll("labda", "lambda");
    }
}
