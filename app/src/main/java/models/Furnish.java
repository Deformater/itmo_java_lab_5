package models;

public enum Furnish {
    DESIGNER("DESIGNER"),
    FINE("FINE"),
    BAD("BAD");

    private final String type;

    Furnish(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}