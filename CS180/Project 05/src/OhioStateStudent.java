import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class OhioStateStudent extends CollegeStudent {
    private int age;
    private String firstName;
    private String housing;
    private String id;
    private ArrayList<String> idNums;
    private String OHIO_STATE_UNIVERSITY_CITY = "Columbus";
    private String[] OHIO_STATE_UNIVERSITY_DORMS = {"Archer House", "Baker Hall East", "Baker Hall West",
            "Barrett House", "Blackburn House", "Bowen House", "Bradley Hall", "Busch Hall",
            "Canfield Hall","Drackett Tower", "Fechko House", "German House", "Halloran House", "Hanley House",
            "Haverfield House","Houck House", "Houston House", "Jones Tower", "Lawrence Tower", "Lincoln Tower",
            "Mack Hall", "Mendoze " + "House", "Morill Tower", "Morrison Tower", "Neil Avenue", "Norton House",
            "Nosker House", "Park-Stradley " + "House", "Paterson House", "Pennsylvania House", "Pomerene House",
            "Raney House", "Scholars East", "Scholars West", "Scott House", "Siebert Hall", "Smith-Steeb Hall",
            "Taylor Tower", "The Residence on " + "Tenth", "Torres House", "Veteran's House", "Worthington Building"};
    private String OHIO_STATE_UNIVERSITY_MASCOT = "Brutus Buckeye";
    private String OHIO_STATE_UNIVERSITY_STATE = "Ohio";
    private String lastName;
    private String major;
    private String stateOfResidency;
    private double tuition;

    public OhioStateStudent() {

    }

    public OhioStateStudent(int age, String firstName, String lastName, String stateOfResidency,
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

    public OhioStateStudent(String id) {
        this.id = id;
    }

    @Override
    public void calculateTuition() {
        if (stateOfResidency.equalsIgnoreCase(OHIO_STATE_UNIVERSITY_STATE)) {
            tuition = CollegeConstants.OHIO_STATE_UNIVERSITY_IN_STATE_TUITION;
        } else if (!stateOfResidency.equalsIgnoreCase(OHIO_STATE_UNIVERSITY_STATE)) {
            tuition = CollegeConstants.OHIO_STATE_UNIVERSITY_OUT_OF_STATE_TUITION;
        }
    }

    @Override
    public void generateID() {
        Random rn = new Random();
        String result = "";
        do {
            result = "";
            for (int i = 0; i <= 9; i++) {
                int rando = rn.nextInt(2);
                int random = rn.nextInt(10);
                result += random + rando;
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
        return OHIO_STATE_UNIVERSITY_CITY;
    }

    @Override
    public ArrayList<String> getDorms() {
        ArrayList<String> dorms = new ArrayList<>(0);
        dorms.addAll(Arrays.asList(OHIO_STATE_UNIVERSITY_DORMS));
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
        return OHIO_STATE_UNIVERSITY_MASCOT;
    }

    @Override
    public String getState() {
        return OHIO_STATE_UNIVERSITY_STATE;
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
