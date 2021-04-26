import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PurdueStudent extends CollegeStudent {
    private int age;
    private String firstName;
    private String housing;
    private String id;
    private ArrayList<String> idNums = new ArrayList<>();
    private String PURDUE_UNIVERSITY_CITY = "West Lafayette";
    private String[] PURDUE_UNIVERSITY_DORMS = {"Cary Quadrangle", "Earhart Hall", "First Street Towers",
            "Harrison Hall", "Hawkins Hall", "Hillenbrand Hall", "Hilltop Apartments", "Honors College and Residences",
            "McCutcheon Hall", "Meredith Hall", "Owen Hall", "Purdue Village", "Shreve Hall", "Tarkington Hall",
            "Third Street Suites", "Wiley Hall", "Windsor Apartments", "UR Boiler Apartments"};
    private String PURDUE_UNIVERSITY_MASCOT = "Purdue Pete";
    private String PURDUE_UNIVERSITY_STATE = "Indiana";
    private String lastName;
    private String major;
    private String stateOfResidency;
    private double tuition;

    public PurdueStudent() {

    }

    public PurdueStudent(int age, String firstName, String lastName, String stateOfResidency,
                           String major, String housing) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stateOfResidency = stateOfResidency;
        this.major = major;
        this.housing = housing;
        calculateTuition();
        generateID();
    }

    public PurdueStudent(String id) {
        this.id = id;
    }

    @Override
    public void calculateTuition() {
        if (stateOfResidency.equalsIgnoreCase(PURDUE_UNIVERSITY_STATE)) {
            tuition = CollegeConstants.PURDUE_UNIVERSITY_IN_STATE_TUITION;
        } else if (!stateOfResidency.equalsIgnoreCase(PURDUE_UNIVERSITY_STATE)) {
            tuition = CollegeConstants.PURDUE_UNIVERSITY_OUT_OF_STATE_TUITION;
        }
    }

    @Override
    public void generateID() {
        Random rn = new Random();
        String result = "";
        do {
            result = "";
            for (int i = 0; i <= 4; i++) {
                int rando = rn.nextInt(10);
                result += "3" + rando;
            }
            if (!idNums.contains(result)) {
                idNums.add(result);
                break;
            }
        } while (true);
        this.id = result;
    }

    @Override
    public String getCity() {
        return PURDUE_UNIVERSITY_CITY;
    }

    @Override
    public ArrayList<String> getDorms() {
        ArrayList<String> dorms = new ArrayList<>(0);
        dorms.addAll(Arrays.asList(PURDUE_UNIVERSITY_DORMS));
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
        return this.id;
    }

    @Override
    public String getMajor() {
        return major;
    }

    @Override
    public String getMascot() {
        return PURDUE_UNIVERSITY_MASCOT;
    }

    @Override
    public String getState() {
        return PURDUE_UNIVERSITY_STATE;
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
