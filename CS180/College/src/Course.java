public class Course {

    private Professor professor;
    private Student[] students;
    private String courseName;
    private int numEnrolled;

    public Course(Professor professor, String courseName) {
        this.professor = professor;
        this.courseName = courseName;
        this.students = new Student[100];
        this.numEnrolled = 0;
    }

    public boolean enroll(Student s) {
        int counter = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = s;
                numEnrolled += 1;
                return true;
            }
        }
        return false;
    }

    public Student[] getStudents() {
        return students;
    }

    public Professor getProfessor() {
        return professor;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getNumEnrolled() {
        return numEnrolled;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setNumEnrolled(int numEnrolled) {
        this.numEnrolled = numEnrolled;
    }

    //    public static void main(String[] args) {
//
//        String courseName = "MA16200";
//
//        Professor p = new Professor("Dr. Malcom", "MATH", courseName, "Professor");
//
//        Course c = new Course(p, courseName);                   // creates a new Course
//
//
//        Student s = new Student("Bobby Jones", "Earhart Hall");  // Creates new Student
//
//
//        System.out.println(s.getCourses()[0]);              // prints "none"
//
//        System.out.println(c.enroll(s));                    // prints "true"
//
//        System.out.println(s.getCourses()[0]);              // prints "MA16200"
//
//        System.out.println(c.getStudents()[0].getName());   // prints "Bobby Jones"
//
//        System.out.println(c.getProfessor().getName());
//    }
}
