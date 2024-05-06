package atomcode;

import atomcode.entities.School;
import atomcode.utils.Print;
import atomcode.utils.PullData;

public class Main {
    public static void main(String[] args) {
        School school = new School();

        PullData.pullTeacherData(school);

        PullData.pullSubjectData(school);

        PullData.pullSchoolClasses(school);

        PullData.pullSchoolStudents(school);

        Print.startScreen(school);
    }
}