import java.util.ArrayList;

/**
 * Program Name
 *
 * Abstraction of a college course. Each course is uniquely identifiable by its course name.
 *
 * @author You
 *
 * @version date of completion
 *
 */

public class Course {
    /**
     * Maximum number of students allowed to be enrolled in a Course
     */
    protected final static int MAX_STUDENTS = 100;

    private String name;
    private Professor professor;
    private Teacher[] teachers;
    private Student[] students;

    /**
     * Constructs a Course object with the corresponding parameters as its name and a reference to the lead Professor.
     * Creates an array to contain at most 100 students enrolled in the course and ensures the Professor adds the course
     * to their list of courses.
     *
     * @param name Name of the Course to be offered.
     * @param professor A reference to the lead professor for the course.
     */
    public Course(String name, Professor professor) {
        this.name = name;
        this.professor = professor;
        students = new Student[100];
        teachers = new Teacher[10];
        professor.addCourse(this);
    }

    /**
     * Adds student to the course. Throws AddToCourseException if the course is full, if student is null, or
     * if the student is already enrolled in the course
     *
     * @param student Student to be added to the Course
     * @throws AddToCourseException If the course is full, if student is null, or if the student is already enrolled in
     * the course
     */
    public void addStudent(Student student) throws AddToCourseException {
        int counter = 0;
        if (student == null) {
            throw new AddToCourseException("This student does not exist");
        }

        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getID() == student.getID()) {
                throw new AddToCourseException("This student is already enrolled in this class");
            }
        }

        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                break;
            } else {
                counter++;
            }
        }

        if (counter == students.length) {
            throw new AddToCourseException("Course is full!");
        }
    }

    /**
     * Removes student from the course. Throws DropFromCourseException if student is null or if the student
     * is not enrolled in the course.
     *
     * @param student Student to be removed from the course
     * @throws DropFromCourseException If student is null or if the student is not enrolled in the course.
     */
    public void dropStudent(Student student) throws DropFromCourseException {
        int counter = 0;
        if (student == null) {
            throw new DropFromCourseException("This student does not exist");
        }

        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getID() == student.getID()) {
                students[i] = null;
            } else {
                counter++;
            }
        }

        if (counter == students.length) {
            throw new DropFromCourseException("This student is not enrolled in this class");

        }
    }

    /**
     * Adds teacher to the course. If the teachers array is full, then its size is doubled and the teacher is added.
     * A Teacher may teach the same course more than once (like having multiple sections). Throws AddToCourseException
     * if teacher is null. The teacher should add this course to their personal list of courses.
     *
     * @param teacher Teacher to be added to the Course
     * @throws AddToCourseException If the course is full or if teacher is null
     */
    public void addTeacher(Teacher teacher) throws AddToCourseException {
        if (teacher == null) {
            throw new AddToCourseException("This teacher does not exist");
        }

        int counter = 0;
        boolean full = false;

        for (int i = 0; i < teachers.length; i++) {
            if (teachers[i] == null) {
                teachers[i] = teacher;
                teacher.addCourse(this);
                break;
            } else {
                counter++;
            }
        }
        if (counter == teachers.length) {
            full = true;
        }
        if (full) {
            Teacher[] temp = new Teacher[teachers.length];
            for (int i = 0; i < teachers.length; i++) {
                temp[i] = teachers[i];
            }
            teachers = new Teacher[temp.length * 2];
            for (int i = 0; i < teachers.length; i++) {
                if (i < temp.length) {
                    teachers[i] = temp[i];
                }
                if (teachers[i] == null) {
                    teachers[i] = teacher;
                    teacher.addCourse(this);
                    break;
                }
            }
        }
    }

    /**
     * Removes teacher from the courses's array of teachers. If teacher teaches multiple instances of the
     * course, only one is removed. Throws DropFromCourseException if teacher is null or if teacher is not found.
     *
     * @param teacher Teacher to be added to the Course
     * @throws DropFromCourseException Uf teacher is null or if teacher is not found.
     */
    public void dropTeacher(Teacher teacher)throws DropFromCourseException {
        int counter = 0;
        if (teacher == null) {
            throw new DropFromCourseException("This teacher does not exist");
        }
        for (int i = 0; i < teachers.length; i++) {
            if (teachers[i] != null && teachers[i].getID() == teacher.getID()) {
                teachers[i].dropCourse(this);
                teachers[i] = null;
                break;
            } else {
                counter++;
            }
        }
        if (counter == teachers.length) {
            throw new DropFromCourseException("This teacher does not teach this course");
        }
    }

    /**
     * @return Reference to Professor leading this Course
     */
    public Professor getProfessor() {
        return this.professor;
    }

    /**
     * Creates and returns a new array containing the list of Students. The new array should have a length equal to the
     * number of students currently enrolled in the course. Returns an array of length 0 if the course has no Students.
     *
     * @return A new array containing the Course's Students with no null elements.
     */
    public Student[] getRoster() {
        int counter = 0;
        ArrayList<Student> temp = new ArrayList<>();
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                counter++;
                temp.add(students[i]);
            }
        }
        Student[] result = new Student[counter];
        for (int i = 0; i < result.length; i++) {
            result[i] = temp.get(i);
        }
        return result;
    }

    /**
     * @return The name of the Course
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a new array containing the Courses's Teachers. The new array should have a length equal to the
     * number of Teachers currently teaching the course, duplicates included. Returns an array of length 0 if the course
     * has no Teachers.
     *
     * @return A new array containing the Course's Teachers with no null elements.
     */
    public Teacher[] getTeachers() {
        int counter = 0;
        ArrayList<Teacher> temp = new ArrayList<>();
        for (int i = 0; i < teachers.length; i++) {
            if (teachers[i] != null) {
                counter++;
                temp.add(teachers[i]);
            }
        }
        Teacher[] result = new Teacher[counter];
        for (int i = 0; i < result.length; i++) {
            result[i] = temp.get(i);
        }
        return result;
    }

    /**
     * Makes professor the Course professor and updates the old and new Professors involved accordingly.
     *
     * @param professor Reference to Professor object to be made the lead Professor of the course.
     */
    public void changeProfessor(Professor professor) {
        this.professor.dropCourse(this);
        this.professor = professor;
        this.professor.addCourse(this);
    }

    /**
     * Returns a String representation of the Course object. This method must exist, but will not be tested.
     *
     * @return String
     */
    public String toString() {
        return "";
    }
}
