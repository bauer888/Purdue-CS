public class Student extends Person {

    private String[] courses;
    private char[] grades;

    public Student(String name, String address) {
        super(name, address);
        courses = new String[6];
        grades = new char[6];

        for (int i = 0; i < courses.length; i++) {
            courses[i] = "none";
            grades[i] = 'A';
        }
    }

    public boolean addCourse(String course) {
        for (int i = 0; i < courses.length; i++) {
            if (courses[i].equals(course)) {
                return false;
            }
        }
        for (int i = 0; i < courses.length; i++) {
            if (courses[i].equals("none")) {
                courses[i] = course;
                return true;
            }
        }
        return false;
    }

    public String[] getCourses() {
        return courses;
    }

    public char[] getGrades() {
        return grades;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    public void setGrades(char[] grades) {
        this.grades = grades;
    }
}
