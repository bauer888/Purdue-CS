/**
 * Program Name
 *
 * Abstraction of a college student. Each student is uniquely identifiable by their ID (starting at 0).
 *
 * @author You
 *
 * @version date of completion
 *
 */

public class Student implements Person {
    /**
     * Static variable used to determine the next ID number
     */
    protected static int nextID = 0;

    private int id;
    private String name;
    private int age;
    private String gender;

    /**
     * Constructs a new Student with the corresponding parameters as field values, and the next ID value (one greater
     * than the previously created students's id value).
     *
     * @param name Name to be assigned to this Student
     * @param age Age of this Student
     * @param gender Gender of this Student
     */
    public Student(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        id = nextID;
        nextID++;
    }

    @Override
    public int getID() {
        //TODO: Return the Student's ID number
        return id;
    }

    @Override
    public void setName(String name){
        this.name = name;
        //TODO: Set the Student's name to name
    }

    @Override
    public void setGender(String gender){
        this.gender = gender;
        //TODO: Set the Student's gender to gender
    }

    @Override
    public void setAge(int age){
        this.age = age;
        //TODO: Set the Student's age to age
    }

    @Override
    public String getName(){
        //TODO: Return the Student's name
        return name;
    }

    @Override
    public String getGender(){
        //TODO: Return the Student's gender
        return gender;
    }

    @Override
    public int getAge(){
        //TODO: Return the Student's age
        return age;
    }

    /**
     * Returns a String representation of the Student object. This method must exist, but will not be tested.
     *
     * @return String
     */
    public String toString() {
        return "";
    }
}
