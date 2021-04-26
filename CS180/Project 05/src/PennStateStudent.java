import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PennStateStudent extends CollegeStudent {
    private int age;
    private String firstName;
    private String housing;
    private String id;
    private ArrayList<String> idNums;
    private String PENN_STATE_UNIVERSITY_CITY = "University Park";
    private String[] PENN_STATE_UNIVERSITY_DORMS = {"Bigler Hall", "Brumbaugh Hall", "Curtin Hall","Findlay " +
            "Commons", "Fisher Hall", "Geary Hall", "Hastings Hall", "Johnston Commons", "McKean Hall", "Packer Hall",
            "Pennypacker Hall", "Pinchot Hall", "Snyder Hall", "Sproul Hall", "Stone Hall", "Stuart Hall", "Tener Hall",
            "Brill Hall", "Curry Hall", "Harris Hall", "Miller Hall", "Nelson Hall", "Panofsky Hall", "Young Hall",
            "Nittany Apartments", "Nittany Hall", "Beam Hall", "Holmes Hall", "Leete Hall", "Runkle Hall",
            "Warnock Commons", "Beaver Hall", "Hartranft Hall", "Hiester Hall", "Mifflin Hall", "Pollock Commons",
            "Porter Hall", "Ritner Hall", "Shulze Hall", "Shunk Hall", "Wolf Hall", "Atherton Hall", "Cooper Hall",
            "Cross Hall", "Ewing Hall", "Haller Hall", "Hibbs Hall", "Hoyt Hall", "Lyons Hall", "McElwain Hall",
            "Redifer Commons", "Simmons Hall", "Stephens Hall", "Hamilton Hall", "Irvin Hall", "Jordan Hall",
            "McKee Hall", "Thompson Hall", "Waring Commons", "Watts Hall", "Berneuter Hall", "Cunningham Hall",
            "Donkin Hall", "Durham Hall", "Farrell Hall", "Ferguson Hall", "Garban Hall", "Grubb " + "Hall",
            "Haffner Hall", "Holderman Hall", "Ikenberry Hall", "Lovejoy Hall", "Osborn Hall", "Palladino Hall",
            "Patterson Hall", "Ray Hall", "Weston Community Center"};
    private String PENN_STATE_UNIVERSITY_MASCOT = "Nittany Lion";
    private String PENN_STATE_UNIVERSITY_STATE = "Pennsylvania";
    private String lastName;
    private String major;
    private String stateOfResidency;
    private double tuition;

    public PennStateStudent() {

    }

    public PennStateStudent(int age, String firstName, String lastName, String stateOfResidency,
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

    public PennStateStudent(String id) {
        this.id = id;
    }

    @Override
    public void calculateTuition() {
        if (stateOfResidency.equalsIgnoreCase("In-state")) {
            tuition = CollegeConstants.PENN_STATE_UNIVERSITY_IN_STATE_TUITION;
        } else if (stateOfResidency.equalsIgnoreCase("Out-of-state")) {
            tuition = CollegeConstants.PENN_STATE_UNIVERSITY_OUT_OF_STATE_TUITION;
        }
    }

    @Override
    public void generateID() {
        Random rn = new Random();
        String result = "";
        do {
            result = "";
            for (int i = 0; i <= 9; i++) {
                int rando = rn.nextInt(4);
                int random = rn.nextInt(10);
                result += rando + random;
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
        return PENN_STATE_UNIVERSITY_CITY;
    }

    @Override
    public ArrayList<String> getDorms() {
        ArrayList<String> dorms = new ArrayList<>(0);
        dorms.addAll(Arrays.asList(PENN_STATE_UNIVERSITY_DORMS));
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
        return PENN_STATE_UNIVERSITY_MASCOT;
    }

    @Override
    public String getState() {
        return PENN_STATE_UNIVERSITY_STATE;
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
