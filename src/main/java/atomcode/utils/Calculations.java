package atomcode.utils;

import atomcode.constants.Constant;
import atomcode.entities.Grade;
import atomcode.entities.School;
import atomcode.entities.SchoolClass;
import atomcode.entities.Subject;
import atomcode.person.Student;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Calculations {

    public static void printSortedStudentAvg (School school) {
        List<Student> sortedStudentAvgList = school.getSchoolStudents().stream()
                .sorted(Comparator.comparingDouble(student -> student.calculateStudentAvgGrade(student.getStudentGrades())))
                .collect(Collectors.toList());

        sortedStudentAvgList.forEach(student -> {
            System.out.println("Student: " + student.getFirstName() + " " + student.getLastName()
                    + " average grade = " + student.calculateStudentAvgGrade(student.getStudentGrades()));
        });
    }

    public static void printSortedSubjectAvg (School school) {
        Map<Subject, List<Grade>> aggregatedGrades = new HashMap<>();
        for (Subject subject : school.getSchoolSubjects()) {
            List<Grade> grades = new ArrayList<>();
            for (Student student : school.getSchoolStudents()) {
                Grade grade = student.getStudentGrades().get(subject);
                if (grade != null) {
                    grades.add(grade);
                }
            }
            aggregatedGrades.put(subject, grades);
        }

        Map<Subject, Double> aggregatedAverageGrades = aggregatedGrades.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().stream()
                                .mapToDouble(grade -> grade.getGrade())
                                .average()
                                .orElse(0.0)
                ));

        List<Subject> sortedSubjectByGradeAvg = aggregatedAverageGrades.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        sortedSubjectByGradeAvg.forEach(subject -> {
            Double averageGrade = aggregatedAverageGrades.get(subject);
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            System.out.println("Subject: " + subject.getSubjectTitle() + ", Average grade: " + Double.parseDouble(decimalFormat.format(averageGrade)));
        });
    }

    public static void sortedClassesBestStudents (School school) {
        List<SchoolClass> sortedClasses = school.getSchoolClassesList().stream()
                .sorted(Comparator.comparing(schoolClass -> schoolClass.getClassName()))
                .collect(Collectors.toList());

        sortedClasses.forEach(schoolClass -> {
            System.out.println("Class: " + schoolClass.getClassName() + " - TOP 3 students by average grade:");
            List<Student> sortedStudents = schoolClass.getClassStudents().stream()
                    .sorted(Comparator.comparingDouble(student -> student.calculateStudentAvgGrade(student.getStudentGrades())))
                    .collect(Collectors.toList());
            for (int i = 0; i < Math.min(sortedStudents.size(), 3); i++) {
                Student student = sortedStudents.get(i);
                double avgGrade = student.calculateStudentAvgGrade(student.getStudentGrades());
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                System.out.println("Last name: " + student.getLastName()
                        + ", First name: " + student.getFirstName()
                        + ", AVG grade: " + Double.parseDouble(decimalFormat.format(avgGrade)));
            }
            System.out.println(Constant.divider);
        });
    }
}
