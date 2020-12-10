package application.logic;

public enum Gender {

    MALE("Male"), FEMALE("Female"), OTHER("Other");

    private String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    public String toString() {
        return this.displayName;
    }
}
