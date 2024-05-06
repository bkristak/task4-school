package atomcode.constants;

public enum Gender {

    MALE("Person's sex is male."),
    FEMALE("Person's sex is female."),
    OTHER("Person's sex does not fit strictly into male or female categories. " +
            "This may include intersex individuals, or those who identify as non-binary or genderqueer.");


    private final String description;

    Gender (String description) {
        this.description = description;
    }

    public String getDescription() { return description;}
}
