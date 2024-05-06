package atomcode.person;

import atomcode.constants.Constant;
import atomcode.constants.Gender;
import atomcode.constants.Nationality;
import atomcode.entities.Reportable;
import atomcode.entities.School;
import atomcode.entities.SchoolClass;
import atomcode.entities.Subject;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Teacher extends Person implements Reportable {

    School school;

    public Teacher (String firstName, String lastName, LocalDate dateOfBirth,
                    Nationality nationality, Gender gender, School school) {
        super(firstName, lastName, dateOfBirth, nationality, gender);
        this.school = school;
    }

    @Override
    public void report () {
        LocalDate currentDate = LocalDate.now();
        Period teacherAge = Period.between(dateOfBirth, currentDate);
        long years = teacherAge.get(ChronoUnit.YEARS);
        String taughtSubjects = getTaughtSubjectsAsString(school, lastName);
        String primaryTeacher = getPrimaryTeacherOfClass(school, lastName);

        System.out.println("Last name: " + lastName + ", First name: " + firstName);
        System.out.println("Age: " + years + ", Gender: " + gender + ", Nationality: " + nationality);
        System.out.println("Primary teacher: " + primaryTeacher + " school class");
        System.out.println("Taught subjects: " + taughtSubjects);
        System.out.println(Constant.divider);
    }


    public String getTaughtSubjectsAsString (School school, String lastName) {
        Set<Subject> allSubjects = school.getSchoolSubjects();
        List<String> taughtSubjects = allSubjects.stream()
                .filter(subject -> subject.getSubjectTeacher().getLastName().equalsIgnoreCase(lastName))
                .map(Subject -> Subject.getSubjectTitle())
                .collect(Collectors.toList());

        return String.join(", ", taughtSubjects);
    }

    public String getPrimaryTeacherOfClass (School school, String lastName) {
        Set<SchoolClass> allClasses = school.getSchoolClassesList();
        String primaryTeacherClass = allClasses.stream()
                .filter(schoolClass -> schoolClass.getPrimaryTeacher().getLastName().equalsIgnoreCase(lastName))
                .map(SchoolClass -> SchoolClass.getClassName())
                .findFirst()
                .orElse("N/A");

        return primaryTeacherClass;
    }
}
