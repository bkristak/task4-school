package atomcode.entities;

import atomcode.constants.Constant;
import atomcode.person.Teacher;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Subject implements Reportable {
    School school;
    private String subjectTitle;
    private Teacher subjectTeacher;

    public Subject (String subjectTitle, Teacher subjectTeacher, School school) {
        this.subjectTitle = subjectTitle;
        this.subjectTeacher = subjectTeacher;
        this.school = school;
    }

    public String getSubjectTitle () { return subjectTitle;}
    public Teacher getSubjectTeacher () {return subjectTeacher;}

    @Override
    public void report () {
        String taughtByClassesAsString = getTaughtByClassesAsString(school, subjectTitle);

        System.out.println("Title: " + subjectTitle + " , Teacher: " + (subjectTeacher != null
                            ? subjectTeacher.getFirstName() + " " + subjectTeacher.getLastName()
                            : "N/A"));
        System.out.println("Subjects taught by classes: " + taughtByClassesAsString);
        System.out.println(Constant.divider);
    }

    public String getTaughtByClassesAsString (School school, String subjectTitle) {
        Set<SchoolClass> allClasses = school.getSchoolClassesList();
        List<String> taughtByClassesList = allClasses.stream()
                .filter(schoolClass -> schoolClass.getClassSubjectsSet().stream()
                        .anyMatch(subject -> subject.getSubjectTitle().equalsIgnoreCase(subjectTitle)))
                .map(SchoolClass -> SchoolClass.getClassName())
                .collect(Collectors.toList());

        return String.join(", ", taughtByClassesList);
    }
}
