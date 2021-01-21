package application.logic;

public enum Gender {

    //enum for all the available genders when creating a new student in the application
    MALE("Male"), FEMALE("Female"), OTHER("Other");

    private String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    public String toString() {
        return this.displayName;
    }
}
