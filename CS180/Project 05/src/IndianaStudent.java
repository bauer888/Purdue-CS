import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class IndianaStudent extends CollegeStudent {

    private int age;
    private String firstName;
    private String housing;
    private String id;
    private ArrayList<String> idNums;
    private String INDIANA_UNIVERSITY_CITY = "Bloomington";
    private String[] INDIANA_UNIVERSITY_DORMS = {"Ashton Center", "Collins Hall", "Eigenmann Hall", "Forest Quad",
            "Mason Hall", "Read Hall", "Briscoe Hall", "McNutt Hall", "Foster Hall", "BBHN Apartments", "Campus View "
            + "Apartments", "Evermann Apartments"};
    private String INDIANA_UNIVERSITY_MASCOT = "Hoosiers";
    private String INDIANA_UNIVERSITY_STATE = "Indiana";
    private String lastName;
    private String major;
    private String stateOfResidency;
    private double tuition;

    public IndianaStudent() {

    }

    public IndianaStudent(int age, String firstName, String lastName,
                          String stateOfResidency, String major, String housing) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stateOfResidency = stateOfResidency;
        this.major = major;
        this.housing = housing;
        calculateTuition();
        generateID();
    }

    public IndianaStudent(String id) {
        this.id = id;
    }

    @Override
    public void calculateTuition() {
        if (stateOfResidency.equalsIgnoreCase("In-state")) {
            tuition = CollegeConstants.INDIANA_UNIVERSITY_IN_STATE_TUITION;
        } else if (stateOfResidency.equalsIgnoreCase("Out-of-state")) {
            tuition = CollegeConstants.INDIANA_UNIVERSITY_OUT_OF_STATE_TUITION;
        }
    }

    @Override
    public void generateID() {
        Random rn = new Random();
        String result = "";
        do {
            result = "8";
            for (int i = 0; i <= 6; i++) {
                int rando = rn.nextInt(10);
                result += rando;
            }
            result += "21";
            if (!idNums.contains(result)) {
                idNums.add(result);
                break;
            }
        } while (true);
        this.id = result;
    }

    @Override
    public String getCity() {
        return INDIANA_UNIVERSITY_CITY;
    }

    @Override
    public ArrayList<String> getDorms() {
        ArrayList<String> dorms = new ArrayList<>(0);
        dorms.addAll(Arrays.asList(INDIANA_UNIVERSITY_DORMS));
        return dorms;
    }

    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String getHousing() {
        return housing;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String getMajor() {
        return major;
    }

    @Override
    public String getMascot() {
        return INDIANA_UNIVERSITY_MASCOT;
    }

    @Override
    public String getState() {
        return INDIANA_UNIVERSITY_STATE;
    }

    @Override
    public String getStateOfResidence() {
        return stateOfResidency;
    }

    @Override
    public int getStudentAge() {
        return age;
    }

    @Override
    public String getStudentFirstName() {
        return firstName;
    }

    @Override
    public String getStudentLastName() {
        return lastName;
    }

    @Override
    public double getTuition() {
        return tuition;
    }
}
