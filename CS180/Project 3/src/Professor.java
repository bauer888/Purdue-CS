/**
 * Professor
 *
 * Abstraction of a college professor. Each professor is uniquely identifiable by their ID (starting at 0).
 *
 * @author You
 *
 * @version date of completion
 *
 */

public class Professor extends Teacher {
    /**
     * Static variable used to determine the next ID number
     */
    protected static int nextID = 0;

    /**
     * Constructs a new Professor with the corresponding parameters as field values, an array for storing courses, and
     * the next ID value (one greater than the previously created professors's id value). Set the Professors's
     * baseSalary to 50,000 and perCourseSalary to 15,000 by default.
     *
     * @param name Name of the new Professor to be added
     * @param age Age of the new Professor to be added
     * @param gender Gender of the new Professor to be added
     */
    public Professor(String name, int age, String gender) {
        super(name, age, gender, 50000, 15000);
   }

    /**
     * Constructs a new Professor with the corresponding parameters as field values, an array for storing courses, and
     * the next ID value (one greater than the previously created professors's id value).
     *
     * @param name Name of the new Professor to be added
     * @param age Age of the new Professor to be added
     * @param gender Gender of the new Professor to be added
     * @param baseSalary Amount the Professor must be paid
     * @param perCourseSalary Amount the Professor must be paid for each course they lead
     */
    public Professor(String name, int age, String gender, int baseSalary, int perCourseSalary) {
        super(name, age, gender, baseSalary, perCourseSalary);
   }

    /**
     * This method must exist, but will not be tested. Use for your personal testing
     *
     * @return a String representation of the Professor object
     */
    @Override
    public String toString() {
        return "";
    }
}
