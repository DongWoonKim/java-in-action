package part1;

import enums.AppleColor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


class Apple {

    private String color;
    private int weight;

    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

    public static boolean isGreenApple(Apple apple) {
        return AppleColor.GREEN.name().equals(apple.getColor());
    }

    public static boolean isRedApple(Apple apple) {
        return AppleColor.RED.name().equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

}


public class Start {

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {

        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ( p.test(apple) ) {
                result.add(apple);
            }
        }

        return result;
    }

    public static void main(String[] args) {

        List<Apple> inventory = new ArrayList<>();
        Apple ap = new Apple(AppleColor.GREEN.name(), 100);
        inventory.add(ap);
        ap = new Apple(AppleColor.GREEN.name(), 160);
        inventory.add(ap);
        ap = new Apple(AppleColor.RED.name(), 100);
        inventory.add(ap);
        ap = new Apple(AppleColor.RED.name(), 160);
        inventory.add(ap);

        filterApples(inventory, Apple::isGreenApple).stream()
                .forEach( g->System.out.println(g.getColor() + " : " + g.getWeight()) );
        filterApples(inventory, Apple::isRedApple).stream()
                .forEach( r-> System.out.println(r.getColor() + " : " + r.getWeight()) );

//        filterApples(inventory, (Apple a) -> AppleColor.GREEN.name().equals(a.getColor()))
//                .stream()
//                .forEach( g->System.out.println(g.getColor() + " : " + g.getWeight()) );

//        filterApples(inventory, (Apple a) -> AppleColor.RED.name().equals(a.getColor()) && a.getWeight() > 150)
//                .stream()
//                .forEach( o->System.out.println(o.getColor() + " : " + o.getWeight()) );

        inventory.stream()
                .filter((Apple a) -> a.getWeight() > 150)
                .collect(Collectors.toList())
                .forEach(c-> System.out.println(c.getColor() + " : " + c.getWeight()));

        inventory.parallelStream()
                .filter((Apple a) -> a.getWeight() > 150)
                .collect(Collectors.toList())
                .forEach(c-> System.out.println(c.getColor() + " : " + c.getWeight()));



    }

}


