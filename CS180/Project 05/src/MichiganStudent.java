import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MichiganStudent extends CollegeStudent {

    private int age;
    private String firstName;
    private String housing;
    private String id;
    private ArrayList<String> idNums;
    private String MICHIGAN_UNIVERSITY_CITY = "Ann Arbor";
    private String[] MICHIGAN_UNIVERSITY_DORMS = {"Betsy Barbour", "East Quadrangle", "Fletcher Hall","Helen Newberry",
            "Henderson House", "Martha Cook Building", "North Quadrangle", "South Quadrangle",
            "West Quadrangle and Cambridge House", "Baits II", "Bursley Hall", "Northwood III", "Alice Lloyd Hall",
            "Couzens Hall", "Mary Markley Hall", "Mosher-Jordan Hall", "Oxford Hall", "Stockwell Hall"};
    private String MICHIGAN_UNIVERSITY_MASCOT = "Biff the Wolverine";
    private String MICHIGAN_UNIVERSITY_STATE = "Michigan";
    private String lastName;
    private String major;
    private String stateOfResidency;
    private double tuition;

    public MichiganStudent() {

    }

    public MichiganStudent(int age, String firstName, String lastName, String stateOfResidency,
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

    public MichiganStudent(String id) {
        this.id = id;
    }

    @Override
    public void calculateTuition() {
        if (stateOfResidency.equalsIgnoreCase("In-state")) {
            tuition = CollegeConstants.UNIVERSITY_OF_MICHIGAN_IN_STATE_TUITION;
        } else if (stateOfResidency.equalsIgnoreCase("Out-of-state")) {
            tuition = CollegeConstants.UNIVERSITY_OF_MICHIGAN_OUT_OF_STATE_TUITION;
        }
    }

    @Override
    public void generateID() {
        Random rn = new Random();
        String result = "";
        do {
            result = rn.nextInt(10) + "13";
            for (int i = 0; i <= 6; i++) {
                int rando = rn.nextInt(10);
                result += rando;
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
        return MICHIGAN_UNIVERSITY_CITY;
    }

    @Override
    public ArrayList<String> getDorms() {
        ArrayList<String> dorms = new ArrayList<>(0);
        dorms.addAll(Arrays.asList(MICHIGAN_UNIVERSITY_DORMS));
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
        return MICHIGAN_UNIVERSITY_MASCOT;
    }

    @Override
    public String getState() {
        return MICHIGAN_UNIVERSITY_STATE;
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
