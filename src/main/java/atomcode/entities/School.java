package atomcode.entities;

import atomcode.person.Student;
import atomcode.person.Teacher;
import java.util.HashSet;
import java.util.Set;

public class School {

        Set<SchoolClass> schoolClassesList;
        Set<Subject> schoolSubjects;
        Set<Teacher> schoolTeachers;
        Set<Student> schoolStudents;

    public School () {
        this.schoolClassesList = new HashSet<>();
        this.schoolSubjects = new HashSet<>();
        this.schoolTeachers = new HashSet<>();
        this.schoolStudents = new HashSet<>();
    }

    public Set<SchoolClass> getSchoolClassesList () { return schoolClassesList;}
    public Set<Subject> getSchoolSubjects () { return schoolSubjects;}
    public Set<Teacher> getSchoolTeachers () { return schoolTeachers;}
    public Set<Student> getSchoolStudents () { return schoolStudents;}

    public void setSchoolTeachers(Set<Teacher> schoolTeachers) {
        this.schoolTeachers = schoolTeachers;
    }

    public void setSchoolStudents(Set<Student> schoolStudents) {
        this.schoolStudents = schoolStudents;
    }

    public void setSchoolSubjects (Set<Subject> schoolSubjects) {
        this.schoolSubjects = schoolSubjects;
    }

    public void setSchoolClassesList (Set<SchoolClass> schoolClassesList) {
        this.schoolClassesList = schoolClassesList;
    }



    // !!! TO-DO prepared class to add new school classes by user
    public void addSchoolClass (SchoolClass schoolClass) {
        boolean checkClassExists = schoolClassesList
                .stream()
                .anyMatch(existingClass -> existingClass.getClassName().equals(schoolClass.getClassName()));
        if (checkClassExists) {
            throw new IllegalArgumentException("A class with the same name already exists.");
            // !!! To-DO implement exception handler somewhere in the code !!!
        } else {
            schoolClassesList.add(schoolClass);
        }
    }
}
