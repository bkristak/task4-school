package atomcode.entities;

import atomcode.constants.Constant;
import atomcode.person.Student;
import atomcode.person.Teacher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SchoolClass implements Reportable {

    private String className;
    private Teacher primaryTeacher;
    private List<Student> classStudents;
    private Set<Subject> classSubjectsSet;


    public SchoolClass(String className, Teacher primaryTeacher) {
        this.className = className;
        this.classStudents = new ArrayList<>();
        this.primaryTeacher = primaryTeacher;
        this.classSubjectsSet = new HashSet<>();
    }

    public String getClassName() { return className;}
    public Teacher getPrimaryTeacher() { return primaryTeacher;}
    public List<Student> getClassStudents() { return classStudents;}
    public Set<Subject> getClassSubjectsSet() { return classSubjectsSet;}

    public void setClassSubjectsSet (Set<Subject> classSubjectsSet) {
        this.classSubjectsSet = classSubjectsSet;
    }

    public void addStudent (Student student) {
        classStudents.add(student);
    }

    @Override
    public void report () {
        List<String> classSubjectSetAsList = getClassSubjectsSet().stream()
                        .map(subject -> subject.getSubjectTitle())
                        .collect(Collectors.toList());
        String classSubjectSetAsString = String.join(", ", classSubjectSetAsList);
        int studentNumber = getClassStudents().size();

        System.out.println("Class name: " + className + ", Number of class students: " + studentNumber);
        System.out.println("Class primary teacher: " + (primaryTeacher != null
                ? primaryTeacher.getFirstName() + " " + primaryTeacher.getLastName()
                : "N/A"));
        System.out.println("Class subjects: " + classSubjectSetAsString);
        System.out.println(Constant.divider);
    }
}