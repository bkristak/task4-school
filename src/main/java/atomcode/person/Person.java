package atomcode.person;

import atomcode.constants.Gender;
import atomcode.constants.Nationality;

import java.time.LocalDate;

public class Person {

    protected String firstName;
    protected String lastName;
    protected LocalDate dateOfBirth;
    protected Gender gender;
    protected Nationality nationality;

    public Person (String firstName, String lastName, LocalDate dateOfBirth, Nationality nationality, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.gender = gender;
    }

    public String getFirstName() { return firstName;}
    public String getLastName() { return lastName;}

}
