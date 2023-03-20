package enums;

public enum AppleColor {

    RED("red"),
    GREEN("green");

    AppleColor(String detail) {
        this.detail = detail;
    }

    private String detail;

    public String getDetail() {
        return detail;
    }
}
