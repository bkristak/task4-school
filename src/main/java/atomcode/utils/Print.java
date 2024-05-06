package atomcode.utils;

import atomcode.constants.Constant;
import atomcode.entities.School;
import atomcode.entities.SchoolClass;
import atomcode.entities.Subject;
import atomcode.person.Student;
import atomcode.person.Teacher;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Print {
    public static void startScreen (School school) {
        while (true) {
            System.out.println();
            System.out.println(Constant.divider);
            System.out.println("START SELECTION SCREEN");
            System.out.println("Please select user choice:");
            System.out.println("1 - School lists, 2 - School statistics, 3 - Data management, 0 - Quit program");
            System.out.print("User choice input: ");
            int choice = userInput.readInt();
            System.out.println(Constant.divider);

            switch (choice) {
                case 0 -> {
                    System.out.println("The program will close.");
                    System.exit(0);
                }
                case 1 -> {
                    System.out.println("User selected the module to list school entities.");
                    System.out.println(Constant.divider);
                    schoolLists(school);
                    break;
                }
                case 2 -> {
                    System.out.println("User selected to print school statistics.");
                    System.out.println(Constant.divider);
                    schoolStats(school);
                    break;
                }
                case 3 -> {
                    System.out.println("User selected the data management option.");
                    System.out.println(Constant.divider);
                    dataManagement(school);
                    break;
                }
                default -> {
                    System.out.println("ERROR: User selection " + choice + " is not valid input. Try again and follow the instructions.");
                    System.out.println(Constant.divider);
                    break;
                }
            }
        }
    }

    public static void schoolLists (School school) {
        boolean isValid = false;
        while (!isValid) {
            System.out.println("SCHOOL LISTS SELECTION SCREEN");
            System.out.println("Select list to print:");
            System.out.println("1 - Classes");
            System.out.println("2 - Subjects");
            System.out.println("3 - Teachers");
            System.out.println("4 - Students");
            System.out.println("0 - Return to the start selection screen");
            System.out.print("User choice input: ");
            int choice = userInput.readInt();
            System.out.println(Constant.divider);

            switch (choice) {
                case 0 -> {
                    System.out.println("The program will return to start screen.");
                    System.out.println(Constant.divider);
                    isValid = true;
                    break;
                }
                case 1 -> {
                    System.out.println("User selected to print school classes list.");
                    System.out.println(Constant.divider);
                    List<SchoolClass> sortedClasses = school.getSchoolClassesList().stream()
                                    .sorted(Comparator.comparing(schoolClass -> schoolClass.getClassName()))
                                    .collect(Collectors.toList());
                    sortedClasses.forEach(schoolClass -> schoolClass.report());
                    break;
                }
                case 2 -> {
                    System.out.println("User selected to print school subjects list.");
                    System.out.println(Constant.divider);
                    List<Subject> sortedSubjects = school.getSchoolSubjects().stream()
                                    .sorted(Comparator.comparing(subject -> subject.getSubjectTitle()))
                                    .collect(Collectors.toList());
                    sortedSubjects.forEach(subject -> subject.report());
                    break;
                }
                case 3 -> {
                    System.out.println("User selected to print school teachers list.");
                    System.out.println(Constant.divider);
                    List<Teacher> sortedTeacher = school.getSchoolTeachers().stream()
                                    .sorted(Comparator.comparing(teacher -> teacher.getLastName()))
                                    .collect(Collectors.toList());
                    sortedTeacher.forEach(teacher -> teacher.report());
                    break;
                }
                case 4 -> {
                    System.out.println("User selected to print school students list.");
                    System.out.println(Constant.divider);
                    List<Student> sortedStudents = school.getSchoolStudents().stream()
                                    .sorted(Comparator.comparing(student -> student.getLastName()))
                                    .collect(Collectors.toList());
                    sortedStudents.forEach(student -> student.report());
                    break;
                }
                default -> {
                    System.out.println("ERROR: User selection " + choice + " is not valid input. Try again and follow the instructions.");
                    System.out.println(Constant.divider);
                    break;
                }
            }
        }
        startScreen(school);
    }

    public static void schoolStats (School school) {
        boolean isValid = false;
        while (!isValid) {
            System.out.println();
            System.out.println("SCHOOL STATISTICS SELECTION SCREEN");
            System.out.println("Select user choice:");
            System.out.println("1 - Print sorted students by their average grades");
            System.out.println("2 - Print sorted subjects by average of grades given to students");
            System.out.println("3 - Print sorted classes with the best students (by average of each student’s average grade)");
            System.out.println("0 - Return to the start selection screen");
            System.out.print("User choice input: ");
            int choice = userInput.readInt();
            System.out.println(Constant.divider);

            switch (choice) {
                case 0 -> {
                    System.out.println("The program will return to start screen.");
                    System.out.println(Constant.divider);
                    isValid = true;
                    break;
                }
                case 1 -> {
                    System.out.println("User selected to print sorted students by their average grades.");
                    System.out.println(Constant.divider);
                    Calculations.printSortedStudentAvg(school);
                    break;
                }
                case 2 -> {
                    System.out.println("User selected to print sorted subjects by average of grades given to students.");
                    System.out.println(Constant.divider);
                    System.out.println();
                    Calculations.printSortedSubjectAvg(school);
                    break;
                }
                case 3 -> {
                    System.out.println("User selected to print sorted classes with the best students (by average of each student’s average grade).");
                    System.out.println(Constant.divider);
                    System.out.println();
                    Calculations.sortedClassesBestStudents(school);
                    break;
                }
                default -> {
                    System.out.println("ERROR: User selection " + choice + " is not valid input. Try again and follow the instructions.");
                    System.out.println(Constant.divider);
                    System.out.println();
                    break;
                }
            }
        }
        startScreen(school);
    }

    public static void dataManagement (School school) {
        boolean isValid = false;
        while (!isValid) {
            System.out.println();
            System.out.println("SCHOOL DATA MANAGEMENT SELECTION SCREEN");
            System.out.println("*******************************************************");
            System.out.println("NOTE: This module is not yet implemented. Return to the start selection screen.");
            System.out.println();
            System.out.println("Select user choice:");
            System.out.println("0 - Return to the start selection screen");
            System.out.print("User choice input: ");
            int choice = userInput.readInt();
            System.out.println(Constant.divider);

            switch (choice) {
                case 0 -> {
                    System.out.println("The program will return to start screen.");
                    System.out.println(Constant.divider);
                    isValid = true;
                    break;
                }
                default -> {
                    System.out.println("ERROR: User selection " + choice + " is not valid input. Try again and follow the instructions.");
                    System.out.println(Constant.divider);
                    System.out.println();
                    break;
                }
            }
        }
        startScreen(school);
    }
}