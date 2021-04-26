import java.io.*;
import java.util.Scanner;

/**
 * The main class of the program, that links together all of the other classes in this project.
 *
 * @author your name here
 * @date the date of submission
 */
public class StudentOperator {

    private static String firstName;
    private static String lastName;
    private static int age;
    private static String ageInput;
    private static String major;
    private static String collegeName;
    private static String state;
    private static CollegeStudent student;
    private static String housing;
    private static CollegeStudent lookedupStudent;


    public static void main(String[] args) throws InvalidStateException, InvalidCollegeException,
            InvalidStudentException {
        Scanner s = new Scanner(System.in);
        boolean main = true;
        boolean menu = true;

        // Listed below are all the prompts you will need for main.
        System.out.println("Welcome to the College Student Database!");
        do {
            System.out.println("What would you like to do today?");
            System.out.println("(1) Add my information to the database");
            System.out.println("(2) Find out more information on my future college");
            System.out.println("(3) Lookup Student Information");
            System.out.println("(4) Exit");
            String choice = s.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("What college are you attending?");
                    collegeName = s.nextLine();
                    if (!verifyCollege(collegeName)) {
                        throw new InvalidCollegeException();
                    }
                    System.out.println("What is your first name?");
                    firstName = s.nextLine();
                    if (!verifyName(firstName)) {
                        throw new InvalidStudentException();
                    }
                    System.out.println("What is your last name?");
                    lastName = s.nextLine();
                    if (!verifyName(lastName)) {
                        throw new InvalidStudentException();
                    }
                    System.out.println("How old are you?");
                    ageInput = s.nextLine();
                    if (!verifyAge(ageInput)) {
                        throw new InvalidStudentException();
                    }
                    System.out.println("What is your major?");
                    major = s.nextLine();
                    if (!verifyMajor(major)) {
                        throw new InvalidStudentException();
                    }
                    System.out.println("What state do you currently reside in?");
                    state = s.nextLine();
                    if (!verifyState(state)) {
                        throw new InvalidStateException();
                    }
                    System.out.println("Do you plan to live on or off campus?");
                    housing = s.nextLine();
                    if (!verifyHousing(housing)) {
                        throw new InvalidStudentException();
                    }
                    if (collegeName.equals(CollegeConstants.INDIANA_UNIVERSITY_NAME)) {
                        CollegeStudent iu = new IndianaStudent(age, firstName, lastName, state, major, housing);
                        writeStudentToFile(iu);
                        System.out.println("Here is your new student ID : " + iu.getID());
                    } else if (collegeName.equals(CollegeConstants.PURDUE_UNIVERSITY_NAME)) {
                        CollegeStudent pu = new PurdueStudent(age, firstName, lastName, state, major, housing);
                        writeStudentToFile(pu);
                        System.out.println("Here is your new student ID : " + pu.getID());
                    } else if (collegeName.equals(CollegeConstants.OHIO_STATE_UNIVERSITY_NAME)) {
                        CollegeStudent os = new OhioStateStudent(age, firstName, lastName, state, major, housing);
                        writeStudentToFile(os);
                        System.out.println("Here is your new student ID : " + os.getID());
                    } else if (collegeName.equals(CollegeConstants.UNIVERSITY_OF_MICHIGAN_NAME)) {
                        CollegeStudent um = new MichiganStudent(age, firstName, lastName, state, major, housing);
                        writeStudentToFile(um);
                        System.out.println("Here is your new student ID : " + um.getID());
                    } else if (collegeName.equals(CollegeConstants.PENN_STATE_UNIVERSITY_NAME)) {
                        CollegeStudent ps = new PennStateStudent(age, firstName, lastName, state, major, housing);
                        writeStudentToFile(ps);
                        System.out.println("Here is your new student ID : " + ps.getID());
                    }
                    System.out.println("Thank you for the information! Your information has been documented. " +
                            "Good luck in college!");
                    menu = false;
                    break;
                case "2":
                    System.out.println("What college are you planning on attending?");
                    collegeName = s.nextLine();
                    System.out.println("What information would you like to know about " + collegeName + "?");
                    System.out.println("(1) What dorms are there at my college?");
                    System.out.println("(2) What is the college mascot?");
                    System.out.println("(3) What city is my college in?");
                    System.out.println("(4) What state is my college in?");
                    boolean menu2 = true;
                    String option = s.nextLine();
                    while (menu2) {
                        switch (option) {
                            case "1":
                                if (collegeName.equals(CollegeConstants.PURDUE_UNIVERSITY_NAME)) {
                                    PurdueStudent ps = new PurdueStudent();
                                    System.out.println(ps.getDorms());
                                } else if (collegeName.equals(CollegeConstants.INDIANA_UNIVERSITY_NAME)) {
                                    IndianaStudent is = new IndianaStudent();
                                    System.out.println(is.getDorms());
                                } else if (collegeName.equals(CollegeConstants.PENN_STATE_UNIVERSITY_NAME)) {
                                    PennStateStudent pss = new PennStateStudent();
                                    System.out.println(pss.getDorms());
                                } else if (collegeName.equals(CollegeConstants.UNIVERSITY_OF_MICHIGAN_NAME)) {
                                    MichiganStudent ms = new MichiganStudent();
                                    System.out.println(ms.getDorms());
                                } else if (collegeName.equals(CollegeConstants.OHIO_STATE_UNIVERSITY_NAME)) {
                                    OhioStateStudent os = new OhioStateStudent();
                                    System.out.println(os.getDorms());
                                }
                                menu2 = false;
                                break;
                            case "2":
                                if (collegeName.equals(CollegeConstants.PURDUE_UNIVERSITY_NAME)) {
                                    PurdueStudent ps = new PurdueStudent();
                                    System.out.println(ps.getMascot());
                                } else if (collegeName.equals(CollegeConstants.INDIANA_UNIVERSITY_NAME)) {
                                    IndianaStudent is = new IndianaStudent();
                                    System.out.println(is.getMascot());
                                } else if (collegeName.equals(CollegeConstants.PENN_STATE_UNIVERSITY_NAME)) {
                                    PennStateStudent pss = new PennStateStudent();
                                    System.out.println(pss.getMascot());
                                } else if (collegeName.equals(CollegeConstants.UNIVERSITY_OF_MICHIGAN_NAME)) {
                                    MichiganStudent ms = new MichiganStudent();
                                    System.out.println(ms.getMascot());
                                } else if (collegeName.equals(CollegeConstants.OHIO_STATE_UNIVERSITY_NAME)) {
                                    OhioStateStudent os = new OhioStateStudent();
                                    System.out.println(os.getMascot());
                                }
                                menu2 = false;
                                break;
                            case "3":
                                if (collegeName.equals(CollegeConstants.PURDUE_UNIVERSITY_NAME)) {
                                    PurdueStudent ps = new PurdueStudent();
                                    System.out.println(ps.getCity());
                                } else if (collegeName.equals(CollegeConstants.INDIANA_UNIVERSITY_NAME)) {
                                    IndianaStudent is = new IndianaStudent();
                                    System.out.println(is.getCity());
                                } else if (collegeName.equals(CollegeConstants.PENN_STATE_UNIVERSITY_NAME)) {
                                    PennStateStudent pss = new PennStateStudent();
                                    System.out.println(pss.getCity());
                                } else if (collegeName.equals(CollegeConstants.UNIVERSITY_OF_MICHIGAN_NAME)) {
                                    MichiganStudent ms = new MichiganStudent();
                                    System.out.println(ms.getCity());
                                } else if (collegeName.equals(CollegeConstants.OHIO_STATE_UNIVERSITY_NAME)) {
                                    OhioStateStudent os = new OhioStateStudent();
                                    System.out.println(os.getCity());
                                }
                                menu2 = false;
                                break;
                            case "4":
                                if (collegeName.equals(CollegeConstants.PURDUE_UNIVERSITY_NAME)) {
                                    PurdueStudent ps = new PurdueStudent();
                                    System.out.println(ps.getState());
                                } else if (collegeName.equals(CollegeConstants.INDIANA_UNIVERSITY_NAME)) {
                                    IndianaStudent is = new IndianaStudent();
                                    System.out.println(is.getState());
                                } else if (collegeName.equals(CollegeConstants.PENN_STATE_UNIVERSITY_NAME)) {
                                    PennStateStudent pss = new PennStateStudent();
                                    System.out.println(pss.getState());
                                } else if (collegeName.equals(CollegeConstants.UNIVERSITY_OF_MICHIGAN_NAME)) {
                                    MichiganStudent ms = new MichiganStudent();
                                    System.out.println(ms.getState());
                                } else if (collegeName.equals(CollegeConstants.OHIO_STATE_UNIVERSITY_NAME)) {
                                    OhioStateStudent os = new OhioStateStudent();
                                    System.out.println(os.getState());
                                }
                                menu2 = false;
                                break;
                            default:
                                menu2 = true;
                        }
                    }

                    menu = false;
                    break;
                case "3":
                    System.out.println("Please enter the College of the student you would like to lookup.");
                    collegeName = s.nextLine();
                    System.out.println("Enter the ID of the student you would like to know information about.");
                    String id = s.nextLine();
                    if (collegeName.equals(CollegeConstants.PURDUE_UNIVERSITY_NAME)) {
                        CollegeStudent pu = new PurdueStudent(id);
                        if (!lookupID(pu)) {
                            System.out.println("That student doesn't exist in the database. Thank you for using the Lookup" +
                                    " Tool!");
                            break;
                        }
                        student = lookedupStudent;
                    } else if (collegeName.equals(CollegeConstants.INDIANA_UNIVERSITY_NAME)) {
                        CollegeStudent iu = new IndianaStudent(id);
                        if (!lookupID(iu)) {
                            System.out.println("That student doesn't exist in the database. Thank you for using the Lookup" +
                                    " Tool!");
                            break;
                        }
                        student = lookedupStudent;
                    } else if (collegeName.equals(CollegeConstants.PENN_STATE_UNIVERSITY_NAME)) {
                        CollegeStudent psu = new PennStateStudent(id);
                        if (!lookupID(psu)) {
                            System.out.println("That student doesn't exist in the database. Thank you for using the Lookup" +
                                    " Tool!");
                            break;
                        }
                        student = lookedupStudent;
                    } else if (collegeName.equals(CollegeConstants.UNIVERSITY_OF_MICHIGAN_NAME)) {
                        CollegeStudent um = new MichiganStudent(id);
                        if (!lookupID(um)) {
                            System.out.println("That student doesn't exist in the database. Thank you for using the Lookup" +
                                    " Tool!");
                            break;
                        }
                        student = lookedupStudent;
                    } else if (collegeName.equals(CollegeConstants.OHIO_STATE_UNIVERSITY_NAME)) {
                        CollegeStudent osu = new OhioStateStudent(id);
                        if (!lookupID(osu)) {
                            System.out.println("That student doesn't exist in the database. Thank you for using the Lookup" +
                                    " Tool!");
                            break;
                        }
                        student = lookedupStudent;
                    }
                    System.out.println("What information would you like to know about the student?");
                    System.out.println("(1) Name");
                    System.out.println("(2) Age");
                    System.out.println("(3) Housing");
                    System.out.println("(4) Major");
                    System.out.println("(5) Student Origin");
                    String numbers = s.nextLine();
                    boolean menu3 = true;
                    while (menu3) {
                        switch (numbers) {
                            case "1":
                                System.out.println("The student's name is " + lookedupStudent.getStudentFirstName() + " " +
                                        lookedupStudent.getStudentLastName());
                                menu3 = false;
                                break;
                            case "2":
                                System.out.println("The student's age is " + lookedupStudent.getStudentAge());
                                menu3 = false;
                                break;
                            case "3":
                                System.out.println("The student's housing is considered " + lookedupStudent.getHousing());
                                menu3 = false;
                                break;
                            case "4":
                                System.out.println("The student's major is " + lookedupStudent.getMajor());
                                menu3 = false;
                                break;
                            case "5":
                                System.out.println("The student's home is in " + lookedupStudent.getStateOfResidence());
                                menu3 = false;
                                break;
                            default:
                                menu3 = true;
                        }
                    }

                    menu = false;
                    break;
                case "4":
                    System.out.println("Thank you for using the College Database Program!");
                    main = false;
                    break;
                default:
                    main = true;
                    break;
            }
        } while (main);
    }

    /**
     * The {@code verifyState()} method takes in a state and makes sure it is present in the enum {@code States}. If
     * the state is not present, then the method returns false. If it is, then the method returns true. If the name
     * of the state is two words, like New York, then this method should also convert it to where the space is now
     * an underscore character.If this method returns false, the method that called it should throw a {@code
     * InvalidStateException}.
     *
     * @param str The state to be checked
     * @return The result of whether the state is in the United States
     */
    private static boolean verifyState(String str) {
        if (str == null) {
            return false;
        }
        String[] two = str.split(" ");
        if (two.length == 2) {
            str = two[0] + "_" + two[1];
        } else {
            str = two[0];
        }

        for (int i = 0; i < States.values().length; i++) {
            if (States.values()[i].toString().equals(str)) {
                return true;
            }
        }
        return false;
        //TODO verify that the state entered by the student is a valid state
    }

    /**
     * The {@code verifyAge()} method verifies that the age is between the specified bounds of being 16 years or older
     * and being 22 years or younger. Since the main method will read any input, you should also ensure that the
     * input is an integer.If this method returns false, the method that called it should throw a {@code
     * InvalidStudentException}.
     *
     * @param ageToVerify The age to verify
     * @return The result of whether the age falls between the specified bounds
     */
    private static boolean verifyAge(String ageToVerify) {
        if (ageToVerify == null) {
            return false;
        }
        if (!integer(ageToVerify)) {
            return false;
        } else {
            if (Integer.parseInt(ageToVerify) <= 22 && Integer.parseInt(ageToVerify) >= 16) {
                age = Integer.parseInt(ageToVerify);
                return true;
            } else {
                return false;
            }
        }
        //TODO verify that the age of the student is within the valid bounds
    }

    //My method
    private static boolean integer(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * The {@code verifyMajor()} method verifies whether the major is not a blank line. If the major is a blank line,
     * the method returns false. If the major is anything except a blank line, the method returns true. In addition,
     * the method should only allow letters. The only three characters allowed besides letters are a space, a '/'
     * character, and a '-' character. You should remove these in this method, and replace them with nothing. If this
     * method returns false, the method that called it should throw a {@code InvalidStudentException}.
     *
     * @param majorToVerify The major to verify
     * @return The result of whether the major is a valid major.
     */
    private static boolean verifyMajor(String majorToVerify) {
        if (majorToVerify == null) {
            return false;
        }
        if (majorToVerify.isEmpty()) {
            return false;
        } else {
            String result = "";
            for (int i = 0; i < majorToVerify.length(); i++) {
                if (majorToVerify.charAt(i) != ' ' && majorToVerify.charAt(i) != '/' &&
                        majorToVerify.charAt(i) != '-') {
                    result += majorToVerify.charAt(i);
                }
//                if (Character.isLetter(majorToVerify.charAt(i))) {
//                } else if (majorToVerify.charAt(i) == ' ') {
//                    majorToVerify = majorToVerify.replaceAll(" ", "");
//                } else if (majorToVerify.charAt(i) == '/') {
//                    majorToVerify = majorToVerify.replaceAll("/", "");
//                } else if (majorToVerify.charAt(i) == '-') {
//                    majorToVerify = majorToVerify.replaceAll("-", "");
//                } else {
//                    return false;
//                }
            }
            for (int i = 0; i < result.length(); i++) {
                if (!Character.isLetter(result.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        //TODO verify that the major the student entered is a valid input.
    }

    /**
     * The {@code verifyHousing()} method verifies that the housing statement matches either the on campus or
     * off campus housing constants in the {@code CollegeConstants} class.If this method returns false, the method
     * that called it should throw a {@code InvalidStudentException}.
     *
     * @param housingToVerify The housing status to verify
     * @return The result of whether the housing is valid.
     */
    private static boolean verifyHousing(String housingToVerify) {
        if (housingToVerify == null) {
            return false;
        }

        if (housingToVerify.equals(CollegeConstants.ON_CAMPUS)) {
            return true;
        } else if (housingToVerify.equals(CollegeConstants.OFF_CAMPUS)) {
            return true;
        } else {
            return false;
        }
        //TODO verify that the housing status of the student is a valid input.
    }

    /**
     * The {@code verifyCollege()} method verifies that the college is one of the given colleges supported by
     * the CollegeLogger project. If it is not one of the five colleges, the method returns false. If it is,
     * the method returns true. If this method returns false, the method that called it should throw a {@code
     * InvalidCollegeException}.
     *
     * @param str The college to check support for
     * @return The result of whether or not this college is supported by this program.
     */
    private static boolean verifyCollege(String str) {
        if (str == null) {
            return false;
        }
        if (str.equals(CollegeConstants.INDIANA_UNIVERSITY_NAME)) {
            return true;
        }

        if (str.equals(CollegeConstants.OHIO_STATE_UNIVERSITY_NAME)) {
            return true;
        }

        if (str.equals(CollegeConstants.PENN_STATE_UNIVERSITY_NAME)) {
            return true;
        }

        if (str.equals(CollegeConstants.PURDUE_UNIVERSITY_NAME)) {
            return true;
        }

        if (str.equals(CollegeConstants.UNIVERSITY_OF_MICHIGAN_NAME)) {
            return true;
        }

        return false;
        //TODO verify that the college the user entered is a valid college name.
    }

    /**
     * The {@code verifyName()} method will check whether the name is composed purely of letters. A name containing
     * anything other than letters is considered an invalid name. Similarly, an empty string is also considered an
     * invalid name.If this method returns false, the method that called it should throw a {@code
     * InvalidStudentException}.
     *
     * @param str The name to check validity of
     * @return Whether the name is a valid name for the program to use.
     */
    private static boolean verifyName(String str) {
        if (str == null) {
            return false;
        }

        if (str.isEmpty()) {
            return false;
        }

        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                counter++;
            } else {
                return false;
            }
        }

        if (counter == str.length()) {
            return true;
        } else {
            return false;
        }
        //TODO verify that the name the user entered is a valid name.
    }

    /**
     * The {@code writeStudentToFile()} method takes the information provided by the user to the program and
     * pretty prints it to the appropriate text file. If the file is not empty, meaning there are
     * already one or more entries, a row of 20 dashes separated by a single space should be printed to separate
     * the entries. This information should be put to the appropriate file based on the student's college name. Refer
     * to the handout in order to understand the order of the data to be printed. In addition, keep in mind that the
     * tuition should be printed without a dollars sign but have two decimal places.
     * <p>
     * The files will be named as follows:
     * <p>
     * For Purdue University, data is stored in a file called purdueUniversityStudents.txt
     * For Indiana University, data is stored in a file called indianaUniversityStudents.txt
     * For University of Michigan, data is stored in a file called universityOfMichiganStudents.txt
     * For Pennsylvania State University, data is stored in a file called pennsylvaniaStateUniversityStudents.txt
     * For The Ohio State University, data is stored in a file called theOhioStateUniversityStudents.txt
     *
     * @param studentToWrite The {@code CollegeStudent} to write data about.
     */
    private static void writeStudentToFile(CollegeStudent studentToWrite) {
        if (studentToWrite == null) {
            return;
        }

        try {
            File purdue = new File("purdueUniversityStudents.txt");
            File iu = new File("indianaUniversityStudents.txt");
            File um = new File("universityOfMichiganStudents.txt");
            File psu = new File("pennsylvaniaStateUniversityStudents.txt");
            File osu = new File("theOhioStateUniversityStudents.txt");

            if (studentToWrite instanceof PurdueStudent) {

                FileWriter fw = new FileWriter(purdue, true);
                BufferedWriter bw = new BufferedWriter(fw);

                if (purdue.length() != 0) {
                    bw.write("— — — — — — — — — — — — — — — — — — — —\n");
                    bw.flush();
                }

                bw.write(studentToWrite.getID() + "\n" + studentToWrite.getStudentLastName().toUpperCase() + ", " +
                        studentToWrite.getStudentFirstName().toUpperCase() + "\n" + studentToWrite.getStudentAge() +
                        "\n" + studentToWrite.getHousing() + "\n" + studentToWrite.getMajor() + "\n" +
                        studentToWrite.getStateOfResidence() + "\n" + studentToWrite.getTuition() + "\n");
                bw.flush();
                bw.close();
            } else if (studentToWrite instanceof IndianaStudent) {

                FileWriter fw = new FileWriter(iu, true);
                BufferedWriter bw = new BufferedWriter(fw);

                if (iu.length() != 0) {
                    bw.write("— — — — — — — — — — — — — — — — — — — —\n");
                    bw.flush();
                }

                bw.write(studentToWrite.getID() + "\n" + studentToWrite.getStudentLastName().toUpperCase() + ", " +
                        studentToWrite.getStudentLastName().toUpperCase() + "\n" + studentToWrite.getStudentAge() +
                        "\n" + studentToWrite.getHousing() + "\n" + studentToWrite.getMajor() + "\n" +
                        studentToWrite.getStateOfResidence() + "\n" + studentToWrite.getTuition() + "\n");
                bw.flush();
                bw.close();
            } else if (studentToWrite instanceof MichiganStudent) {

                FileWriter fw = new FileWriter(um, true);
                BufferedWriter bw = new BufferedWriter(fw);

                if (um.length() != 0) {
                    bw.write("— — — — — — — — — — — — — — — — — — — —\n");
                    bw.flush();
                }

                bw.write(studentToWrite.getID() + "\n" + studentToWrite.getStudentLastName().toUpperCase() + ", " +
                        studentToWrite.getStudentLastName().toUpperCase() + "\n" + studentToWrite.getStudentAge() +
                        "\n" + studentToWrite.getHousing() + "\n" + studentToWrite.getMajor() + "\n" +
                        studentToWrite.getStateOfResidence() + "\n" + studentToWrite.getTuition() + "\n");
                bw.flush();
                bw.close();
            } else if (studentToWrite instanceof PennStateStudent) {

                FileWriter fw = new FileWriter(psu, true);
                BufferedWriter bw = new BufferedWriter(fw);

                if (psu.length() != 0) {
                    bw.write("— — — — — — — — — — — — — — — — — — — —\n");
                    bw.flush();
                }

                bw.write(studentToWrite.getID() + "\n" + studentToWrite.getStudentLastName().toUpperCase() + ", " +
                        studentToWrite.getStudentLastName().toUpperCase() + "\n" + studentToWrite.getStudentAge() +
                        "\n" + studentToWrite.getHousing() + "\n" + studentToWrite.getMajor() + "\n" +
                        studentToWrite.getStateOfResidence() + "\n" + studentToWrite.getTuition() + "\n");
                bw.flush();
                bw.close();
            } else if (studentToWrite instanceof OhioStateStudent) {

                FileWriter fw = new FileWriter(osu, true);
                BufferedWriter bw = new BufferedWriter(fw);

                if (osu.length() != 0) {
                    bw.write("— — — — — — — — — — — — — — — — — — — —\n");
                    bw.flush();
                }

                bw.write(studentToWrite.getID() + "\n" + studentToWrite.getStudentLastName().toUpperCase() + ", " +
                        studentToWrite.getStudentLastName().toUpperCase() + "\n" + studentToWrite.getStudentAge() +
                        "\n" + studentToWrite.getHousing() + "\n" + studentToWrite.getMajor() + "\n" +
                        studentToWrite.getStateOfResidence() + "\n" + studentToWrite.getTuition() + "\n");
                bw.flush();
                bw.close();
            }
        } catch (Exception e) {

        }

        //TODO write data to the appropriate file, as described by the handout
    }

    private static boolean lookupID(CollegeStudent student) {
        if (student == null) {
            return false;
        }
        try {
            File purdue = new File("purdueUniversityStudents.txt");
            File iu = new File("indianaUniversityStudents.txt");
            File um = new File("universityOfMichiganStudents.txt");
            File psu = new File("pennsylvaniaStateUniversityStudents.txt");
            File osu = new File("theOhioStateUniversityStudents.txt");
            if (student instanceof PurdueStudent) {
                FileReader fr = new FileReader(purdue);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.equals(student.getID())) {
                        String fullName = br.readLine();
                        ageInput = br.readLine();
                        housing = br.readLine();
                        major = br.readLine();
                        state = br.readLine();
                        String[] names = fullName.split(",");
                        lastName = toNameCase(names[0]);
                        firstName = toNameCase(names[1].trim());
                        lookedupStudent = new PurdueStudent(age, firstName, lastName, state, major, housing);
                        return true;
                    }
                }
            } else if (student instanceof IndianaStudent) {
                FileReader fr = new FileReader(iu);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.equals(student.getID())) {
                        String fullName = br.readLine();
                        ageInput = br.readLine();
                        housing = br.readLine();
                        major = br.readLine();
                        state = br.readLine();
                        String[] names = fullName.split(",");
                        lastName = toNameCase(names[0]);
                        firstName = toNameCase(names[1].trim());
                        lookedupStudent = new IndianaStudent(age, firstName, lastName, state, major, housing);
                        return true;
                    }
                }
            } else if (student instanceof PennStateStudent) {
                FileReader fr = new FileReader(psu);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.equals(student.getID())) {
                        String fullName = br.readLine();
                        ageInput = br.readLine();
                        housing = br.readLine();
                        major = br.readLine();
                        state = br.readLine();
                        String[] names = fullName.split(",");
                        lastName = toNameCase(names[0]);
                        firstName = toNameCase(names[1].trim());
                        lookedupStudent = new PennStateStudent(age, firstName, lastName, state, major, housing);
                        return true;
                    }
                }
            } else if (student instanceof MichiganStudent) {
                FileReader fr = new FileReader(um);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.equals(student.getID())) {
                        String fullName = br.readLine();
                        ageInput = br.readLine();
                        housing = br.readLine();
                        major = br.readLine();
                        state = br.readLine();
                        String[] names = fullName.split(",");
                        lastName = toNameCase(names[0]);
                        firstName = toNameCase(names[1].trim());
                        lookedupStudent = new MichiganStudent(age, firstName, lastName, state, major, housing);
                        return true;
                    }
                }
            } else if (student instanceof OhioStateStudent) {
                FileReader fr = new FileReader(osu);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.equals(student.getID())) {
                        String fullName = br.readLine();
                        ageInput = br.readLine();
                        housing = br.readLine();
                        major = br.readLine();
                        state = br.readLine();
                        String[] names = fullName.split(",");
                        lastName = toNameCase(names[0]);
                        firstName = toNameCase(names[1].trim());
                        lookedupStudent = new OhioStateStudent(age, firstName, lastName, state, major, housing);
                        return true;
                    }
                }
            }
        } catch (Exception e) {

        }
        return false;
    }

    private static String toNameCase(String str) {
        String first = str.substring(0, 1).toUpperCase();
        str = str.substring(1).toLowerCase();
        return first + str;
    }
}
