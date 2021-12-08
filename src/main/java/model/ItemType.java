package model;

public enum ItemType {

    BOOK("book"),
    EBOOK("ebook");

    private String value;

    ItemType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
