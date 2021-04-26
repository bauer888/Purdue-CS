/**
 * Program Name
 *
 * Abstraction of a college teacher. Each teacher is uniquely identifiable by their ID (starting at 0).
 *
 * @author You
 *
 * @version date of completion
 *
 */

public class Teacher implements Person {
    /**
     * Static variable used to determine the next ID number
     */
    protected static int nextID = 0;

    private String name;
    private int age;
    private String gender;
    private int id;
    private Course courses[];
    private int perCourseSalary;
    private int baseSalary;

    /**
     * Constructs a new Teacher with the corresponding parameters as field values, an array for storing courses, and
     * the next ID value (one greater than the previously created teacher's id value). Set the Teacher's baseSalary to
     * 30,000 and perCourseSalary to 15,000 by default.
     *
     * @param name Name of the new Teacher to be added
     * @param age Age of the new Teacher to be added
     * @param gender Gender of the new Teacher to be added
     */
    public Teacher(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.baseSalary = 30000;
        this.perCourseSalary = 15000;
        this.courses = new Course[10];
        id = nextID;
        nextID++;


        //TODO: Initialize field variables for this Teacher object
    }

    /**
     * Constructs a new Teacher with the corresponding parameters as field values, an array for storing courses, and
     * the next ID value (one greater than the previously created teacher's ID value).
     *
     * @param name Name of the new Teacher to be added
     * @param age Age of the new Teacher to be added
     * @param gender Gender of the new Teacher to be added
     * @param baseSalary Amount the Teacher must be paid
     * @param perCourseSalary Amount the Teacher must be paid for each course they lead
     */
    public Teacher(String name, int age, String gender, int baseSalary, int perCourseSalary) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.baseSalary = baseSalary;
        this.perCourseSalary = perCourseSalary;
        this.courses = new Course[10];
        id = nextID;
        nextID++;

        //TODO: Initialize field variables for this Teacher object
    }

    /**
     * Adds a course to the Teacher's array of courses taught. If the course array is full, then its size is doubled
     * the course is added. A Teacher may teach the same course more than once (like having multiple sections). If
     * course is null, nothing changes.
     *
     * This method should not modify the passed course object.
     *
     * @param course
     */
    public void addCourse(Course course) {
        int counter = 0;
        boolean full = false;

        if (course == null) {
            return;
        }
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == null) {
                courses[i] = course;
                break;
            } else {
                counter++;
            }
        }
        if (counter == courses.length) {
            full = true;
        }
        if (full) {
            Course[] temp = new Course[courses.length];
            for (int i = 0; i < courses.length; i++) {
                temp[i] = courses[i];
            }
            courses = new Course[temp.length * 2];
            for (int i = 0; i < temp.length; i++) {
                courses[i] = temp[i];
                if (courses[i] == null) {
                    courses[i] = course;
                }
            }
        }
//        for (int i = 0; i < courses.length; i++) {
//            if (full) {
//                temp[i] = courses[i];
//                courses = new Course[temp.length * 2];
//                courses[i] = temp[i];
//                if (courses[i] == null) {
//                    courses[i] = course;
//                }
//
//            }
//        }
    }

    /**
     * Removes a course from the Teacher's array of courses taught. If the Teacher teaches multiple instances of the
     * course, only one is removed. Returns true if the course is found, false otherwise.
     *
     * @param course
     * @return boolean
     */
    public boolean dropCourse(Course course) {
        if (course == null) {
            return false;
        }
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] != null && courses[i].getName().equals(course.getName())) {
                courses[i] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * @return a reference to the array of courses taught by this Teacher.
     */
    public Course[] getCourses() {
        return courses;
    }

    /**
     * Returns the number of times the Teacher teaches course. For example, if the course occurs twice this returns 2.
     * If the course is not taught by this Teacher, this returns 0. Returns 0 if course is null.
     *
     * @param course Course being confirmed if taught by teacher
     * @return Course object.
     */
    public int teachesCourse(Course course) {
        int counter = 0;
        if (course == null) {
            return 0;
        }
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] != null && courses[i].equals(course)) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public int getID() {
        return id;
    }

    public int getPerCourseSalary() {
        return perCourseSalary; //pretty much
    }

    public int getBaseSalary() {
        return baseSalary; //welp
    }

    /**
     * Returns a String representation of the Teacher object. This method must exist, but will not be tested.
     *
     * @return String
     */
    public String toString() {
        return "";
    }
}
