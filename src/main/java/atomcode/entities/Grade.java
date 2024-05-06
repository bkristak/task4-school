package atomcode.entities;

import atomcode.constants.Constant;

public class Grade {
    private int grade;

    public Grade (int grade) {
        if (!isValidGrade(grade)) {
            throw new IllegalArgumentException("Invalid grade value.");
        }
        this.grade = grade;
    }

    public int getGrade() { return grade;}

    public boolean isValidGrade (int grade) {
        return Constant.ALLOWED_GRADES.stream().anyMatch(value -> value == grade);
    }
}
