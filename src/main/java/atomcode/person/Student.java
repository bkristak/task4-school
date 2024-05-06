package atomcode.person;

import atomcode.constants.Constant;
import atomcode.constants.Gender;
import atomcode.constants.Nationality;
import atomcode.entities.Grade;
import atomcode.entities.Reportable;
import atomcode.entities.SchoolClass;
import atomcode.entities.Subject;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class Student extends Person implements Reportable {

    private SchoolClass schoolClass;
    private Map<Subject, Grade> studentGrades;

    public Student (String firstName, String lastName, LocalDate dateOfBirth, Nationality nationality,
                    Gender gender, SchoolClass schoolClass, Map<Subject, Grade> studentGrades) {
        super(firstName, lastName, dateOfBirth, nationality, gender);
        this.schoolClass = schoolClass;
        this.studentGrades = studentGrades;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public Map<Subject, Grade> getStudentGrades () { return studentGrades;}

    @Override
    public void report () {
        LocalDate currentDate = LocalDate.now();
        Period studentAge = Period.between(dateOfBirth, currentDate);
        long years = studentAge.get(ChronoUnit.YEARS);
        double avgGrade = this.calculateStudentAvgGrade(studentGrades);
        System.out.println("Last name: " + lastName + ", First name: " + firstName);
        System.out.println("Student school class: " + schoolClass.getClassName());
        System.out.println("Age: " + years + ", Gender: " + gender + ", Nationality: " + nationality);
        System.out.println("Average grade: " + avgGrade);
        System.out.println(Constant.divider);
    }

    public double calculateStudentAvgGrade (Map<Subject, Grade> studentGrades) {
        if (studentGrades.isEmpty()) {
            return 0.0;
        }

        double avgGrade = studentGrades.entrySet().stream()
                .mapToDouble(entry -> entry.getValue().getGrade())
                .average()
                .orElse(0.0);

        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(avgGrade));
    }
}
