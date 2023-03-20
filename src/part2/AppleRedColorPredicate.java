package part2;

import enums.AppleColor;

public class AppleRedColorPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple2 apple) {
        return AppleColor.RED.name().equals(apple.getColor());
    }
}
