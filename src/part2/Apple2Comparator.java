package part2;

import java.util.Comparator;

public class Apple2Comparator implements Comparator<Apple2> {
    @Override
    public int compare(Apple2 o1, Apple2 o2) {
        return o1.getWeight().compareTo(o2.getWeight());
    }
}
