/**
 * College
 *
 * Represents an abstraction of a college. Each college may have courses, professors, students, and teachers.
 *
 * @author You
 *
 * @version date of completion
 *
 */

public class College {

    /**
     * Maximum number of Courses allowed to be added to a College
     */
    protected static final int MAX_COURSES = 10;

    /**
     * Maximum number of Professors allowed to be hired by a College
     */
    protected static final int MAX_PROFESSORS = 5;

    /**
     * Maximum number of Teachers allowed to be hired by a College
     */
    protected static final int MAX_TEACHERS = 25;

    /**
     * Maximum number of Students allowed to be enrolled to a College
     */
    protected static final int MAX_STUDENTS = 500;


    private String name;
    private Professor[] professors;
    private Teacher[] teachers;
    private Student[] students;
    private Course[] courses;
    private int tuition;

    /**
     * Constructs a new College object with the corresponding parameters as field values, and new arrays of Professors,
     * Students, Teachers, and Courses limited by the corresponding MAX variables.
     *
     * @param name Name of the College to be created.
     * @param tuition Cost for students to attend.
     */
    public College(String name, int tuition) {
        this.name = name;
        this.tuition = tuition;
        courses = new Course[10];
        professors = new Professor[5];
        teachers = new Teacher[25];
        students = new Student[500];
    }

    /**
     * Adds course to the College's array of courses. If the course is null, the course is already available at the
     * College, or the College cannot add more courses, nothing changes.
     *
     * @param course A course object to be added to the College's array of courses.
     */
    public void addCourse(Course course) {
        int counter = 0;
        if (course == null) {
            return;
        }

        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == null) {
                counter++;
            }
            if (courses[i] != null && courses[i].getName().equals(course.getName())) {
                return;
            }
        }

        if (counter == 0) {
            return;
        }

        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == null) {
                courses[i] = course;
                return;
            }
        }
    }

    /**
     * Adds professor to the College's array of Professors. If the Professor is null, the College cannot add more
     * Professors, or the Professor is already employed by the College, nothing changes.
     *
     * @param professor Professor to be added to the College's array of Professors
     */
    public void hireProfessor(Professor professor) {
        int counter = 0;
        if (professor == null) {
            return;
        }

        for (int i = 0; i < professors.length; i++) {
            if (professors[i] != null && professors[i].getID() == professor.getID()) {
                return;
            }
            if (professors[i] == null) {
                counter++;
            }
        }

        if (counter == 0) {
            return;
        }

        for (int i = 0; i < professors.length; i++) {
            if (professors[i] == null) {
                professors[i] = professor;
                return;
            }
        }
    }

    /**
     * Adds teacher to the College's array of Teachers. If teacher is null, the College cannot add more Teachers,
     * or teacher is already employed by the College, nothing changes.
     *
     * @param teacher Teacher to be added to the College's array of teachers
     */
    public void hireTeacher(Teacher teacher) {
        int counter = 0;
        if (teacher == null) {
            return;
        }

        for (int i = 0; i < teachers.length; i++) {
            if (teachers[i] != null && teachers[i].getID() == teacher.getID()) {
                return;
            }
            if (teachers[i] == null) {
                counter++;
            }
        }

        if (counter == 0) {
            return;
        }

        for (int i = 0; i < teachers.length; i++) {
            if (teachers[i] == null) {
                teachers[i] = teacher;
                return;
            }
        }
    }

    /**
     * Adds student to the College's array of students. If the student is null, the student is already enrolled at the
     * College, or the College cannot add more students, nothing changes.
     *
     * @param student A Student object to be added to the College's array of students.
     */
    public void addStudent(Student student) {
        int counter = 0;
        if (student == null) {
            return;
        }

        for (int i = 0; i < students.length; i++) {

            if (students[i] != null && students[i].getID() == student.getID()) {
                return;
            }
            if (students[i] == null) {
                counter++;
            }
        }

        if (counter == 0) {
            return;
        }

        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                return;
            }
        }
    }

    /**
     * Removes student from the College's array of Students. If the student is enrolled in any Courses, they should be
     * removed from these courses. If student is null or the student is not currently enrolled, nothing changes.
     *
     * *HINT* If you try to remove a student from a Course, an exception is thrown. Consider using a try-catch block.
     *
     * @param student Student to be removed from the College's array of students
     */
    public void dropStudent(Student student) {
        int counter = 0;
        if (student == null) {
            return;
        }

        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getID() == student.getID()) {
                students[i] = null;
                break;
            } else {
                counter++;
            }
        }

        if (counter == students.length) {
            return;
        }
        try {
            for (int i = 0; i < courses.length; i++) {
                courses[i].dropStudent(student);
//                if (students[i] != null && students[i].getID() == student.getID()) {
//                    students[i] = null;
//                    break;
//                }
            }
        } catch (Exception e) {

        }

    }

    /**
     * Returns the net change in the colleges budget. Tuition per student will increase the net change, and payments for
     * Professors and Teachers according to both their base and per course salaries will decrease the net change.
     *
     * @return Net change in the College's funds
     */
    public int calculateNetBudgetChange() {
        int sCounter = 0;
        int tuition = 0;
        int pSalary = 0;
        int p = 0;
        int tSalary = 0;
        int t = 0;
        int result;
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                sCounter++;
            }
        }
        tuition = sCounter * getTuition();

        for (int i = 0; i < professors.length; i++) {
            int counter = 0;
            if (professors[i] != null) {
                pSalary += professors[i].getBaseSalary();
                for (int j = 0; j < professors[i].getCourses().length; j++) {
                    if (professors[i].getCourses()[j] != null) {
                        counter++;
                    }
                }
                p = counter * professors[i].getPerCourseSalary();
                pSalary += p;
            }
        }

        for (int i = 0; i < teachers.length; i++) {
            int counter = 0;
            if (teachers[i] != null) {
                tSalary += teachers[i].getBaseSalary();
                for (int j = 0; j < teachers[i].getCourses().length; j++) {
                    if (teachers[i].getCourses()[j] != null) {
                        counter++;
                    }
                }
                t = counter * teachers[i].getPerCourseSalary();
                tSalary += t;
            }
        }
        result = tuition - pSalary - tSalary;

        return result;
    }

    /**
     * @return A reference to the array of courses offered by this College.
     */
    public Course[] getCourses() {
        return courses;
    }

    /**
     * @return A reference to the array of Teachers employed by this College.
     */
    public Teacher[] getTeachers() {
        return teachers;
    }

    /**
     * @return A reference to the array of Professors employed by this College.
     */
    public Professor[] getProfessors() {
        return professors;
    }

    /**
     * @return A reference to the array of Students enrolled at this College.
     */
    public Student[] getStudents() {
        return students;
    }

    /**
     * @return The name of the College object
     */
    public String getName() {
        return name;
    }

    /**
     * @return The tuition charged per student
     */
    public int getTuition() {
        return tuition; // I wish
    }
}
