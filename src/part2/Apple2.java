package part2;

public class Apple2 {

    private String color;
    private Integer weight;


    private Integer part;

    public Apple2() {}
    public Apple2(Integer weight) {
        this.weight = weight;
    }
    public Apple2(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }
    public Apple2(String color, Integer weight, Integer part) {
        this.color = color;
        this.weight = weight;
        this.part = part;
    }

    public String getColor() {
        return color;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getPart() {
        return part;
    }
}
