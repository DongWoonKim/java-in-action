package part2;

import enums.AppleColor;

public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple2 apple) {
        return AppleColor.GREEN.name().equals(apple.getColor());
    }
}
