package atomcode.utils;

import atomcode.constants.Gender;
import atomcode.constants.Nationality;
import atomcode.entities.Grade;
import atomcode.entities.School;
import atomcode.entities.SchoolClass;
import atomcode.entities.Subject;
import atomcode.person.Student;
import atomcode.person.Teacher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

public class PullData {

    public static void pullTeacherData (School school) {
        String teachersPath = "/src/main/java/externalFiles/teachers.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(teachersPath))) {
            String line;
            Set<Teacher> schoolTeachers = new HashSet<>();

            while ((line = reader.readLine()) != null) {
                String[] entries = line.split(",");

                if (entries.length != 5) {
                    System.err.println("Error: Incomplete line in teacher data file.");
                    continue;
                }

                String firstName = entries[0];
                String lastName = entries[1];
                LocalDate dateOfBirth = null;
                Nationality nationality = null;
                Gender gender = null;

                try {
                    dateOfBirth = LocalDate.parse(entries[2]);
                    nationality = Nationality.valueOf(entries[3]);
                    gender = Gender.valueOf(entries[4]);
                } catch (DateTimeException e) {
                    System.err.println("Invalid date format in teachers file.");
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid format for nationality or gender entry in teachers file.");
                }

                Teacher teacher = new Teacher(firstName, lastName, dateOfBirth, nationality, gender, school);
                schoolTeachers.add(teacher);
            }
            school.setSchoolTeachers(schoolTeachers);

        } catch (IOException e) {
            System.err.println("Error reading teacher data: " + e.getMessage());
        }
    }


    public static void pullSubjectData (School school) {
        String subjectsPath = "/src/main/java/externalFiles/subjects.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(subjectsPath))) {
            String line;
            Set<Subject> schoolSubjects = new HashSet<>();

            while ((line = reader.readLine()) != null) {
                String[] entries = line.split(",");

                if (entries.length != 2) {
                    System.err.println("Error: Incomplete line in subjects data file.");
                    continue;
                }

                String subjectTitle = entries[0];
                String teacherLastName = entries[1];

                Optional<Teacher> optionalTeacher = school.getSchoolTeachers()
                        .stream()
                        .filter(teacher -> teacher.getLastName().equalsIgnoreCase(teacherLastName))
                        .findFirst();

                if (optionalTeacher.isPresent()) {
                    Teacher subjectTeacher = optionalTeacher.get();
                    Subject subject = new Subject(subjectTitle, subjectTeacher, school);
                    schoolSubjects.add(subject);
                } else {
                    System.err.println("Teacher with last name " + teacherLastName + " not found.");
                }
            }
            school.setSchoolSubjects(schoolSubjects);
        } catch (IOException e) {
            System.err.println("Error reading subjects data: " + e.getMessage());
        }
    }


    public static void pullSchoolClasses (School school) {
        String schoolClassesPath = "/src/main/java/externalFiles/school-classes.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(schoolClassesPath))) {
            String line;
            Set<SchoolClass> schoolClasses = new HashSet<>();

            while ((line = reader.readLine()) != null) {
                String[] entries = line.split(",");

                if (entries.length < 3) {
                    System.err.println("Error: Incomplete line in subjects data file.");
                    continue;
                }

                Set<Subject> classSubjectsSet = new HashSet<>();
                String className = entries[0];
                String primaryTeacherLastName = entries[1];

                for (int i = 2; i < entries.length; i++) {
                    String subjectTitle = entries[i].trim();

                    Optional<Subject> optionalSubject = school.getSchoolSubjects()
                            .stream()
                            .filter(subject -> subject.getSubjectTitle().equalsIgnoreCase(subjectTitle))
                            .findFirst();
                    if (optionalSubject.isPresent()) {
                        classSubjectsSet.add(optionalSubject.get());
                    } else {
                        System.err.println("Error: Subject " + subjectTitle + " not found in the data set.");
                    }
                }

                Optional<Teacher> optionalTeacher = school.getSchoolTeachers()
                        .stream()
                        .filter(teacher -> teacher.getLastName().equalsIgnoreCase(primaryTeacherLastName))
                        .findFirst();

                if (optionalTeacher.isPresent()) {
                    Teacher primaryTeacher = optionalTeacher.get();

                    Optional<SchoolClass> optionalPrimaryTeacher = school.getSchoolClassesList()
                            .stream()
                            .filter(schoolClass -> schoolClass.getPrimaryTeacher().equals(primaryTeacher))
                            .findFirst();

                    if (optionalPrimaryTeacher.isEmpty()) {
                        SchoolClass schoolClass = new SchoolClass(className, primaryTeacher);
                        schoolClass.setClassSubjectsSet(classSubjectsSet);
                        schoolClasses.add(schoolClass);
                    } else {
                        System.err.println("Teacher " + primaryTeacherLastName + " is already assigned as the primary teacher for another class.");
                    }
                } else {
                    System.err.println("Teacher with last name " + primaryTeacherLastName + " not found.");
                }
            }
            school.setSchoolClassesList(schoolClasses);

        } catch (IOException e) {
            System.err.println("Error reading subjects data: " + e.getMessage());
        }
    }

    public static void pullSchoolStudents (School school) {
        String studentsPath = "/src/main/java/externalFiles/students.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(studentsPath))) {
            String line;
            Set<Student> schoolStudents = new HashSet<>();

            while ((line = bufferedReader.readLine()) != null) {
                String[] entries = line.split(",");

                String schoolClassName = entries[0];
                Optional<SchoolClass> optionalSchoolClass = school.getSchoolClassesList()
                        .stream()
                        .filter(schoolClass -> schoolClass.getClassName().equalsIgnoreCase(schoolClassName))
                        .findFirst();

                if (optionalSchoolClass.isPresent()) {
                    SchoolClass schoolClass = optionalSchoolClass.get();
                    String firstName = entries[1];
                    String lastName = entries[2];
                    LocalDate dateOfBirth = null;
                    Nationality nationality = null;
                    Gender gender = null;

                    try {
                        dateOfBirth = LocalDate.parse(entries[3]);
                        nationality = Nationality.valueOf(entries[4]);
                        gender = Gender.valueOf(entries[5]);
                    } catch (DateTimeException e) {
                        System.err.println("Invalid date format in students file");
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid format for nationality or gender entry in students file.");
                    }

                    Map<Subject, Grade> studentGradesPull = new HashMap<>();
                    for (int i = 6; i < entries.length; i++) {
                        String[] subjectAndGrade = entries[i].split("=");
                        String subjectName = subjectAndGrade[0];
                        int subjectGradeValue = Integer.parseInt(subjectAndGrade[1]);

                        Optional<Subject> optionalClassSubject = schoolClass.getClassSubjectsSet()
                                .stream()
                                .filter(subject -> subject.getSubjectTitle().equalsIgnoreCase(subjectName))
                                .findFirst();

                        if (optionalClassSubject.isPresent()) {
                            Subject subject = optionalClassSubject.get();
                            Grade grade = new Grade(subjectGradeValue);
                            studentGradesPull.put(subject, grade);
                        } else {
                            System.err.println("Subject " + subjectName + " not found in the class " + schoolClassName);
                        }
                    }

                    Student student = new Student(firstName, lastName,dateOfBirth, nationality, gender, schoolClass, studentGradesPull);
                    schoolStudents.add(student);

                    assignStudentsToClasses(student, school);

                } else {
                    System.err.println("School class name " + schoolClassName + " not found in the data set.");
                }
            }
            school.setSchoolStudents(schoolStudents);
        }
        catch (IOException e) {
            System.err.println("Error reading subjects data: " + e.getMessage());
        }
    }

    public static void assignStudentsToClasses(Student student, School school) {
        String studentClassName = student.getSchoolClass().getClassName();

        school.getSchoolClassesList().stream()
                .filter(schoolClass -> schoolClass.getClassName().equalsIgnoreCase(studentClassName))
                .forEach(schoolClass -> schoolClass.addStudent(student));
    }
}
