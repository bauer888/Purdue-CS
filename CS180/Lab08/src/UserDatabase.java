import java.io.*;
import java.util.Scanner;

public class UserDatabase {

    public static void main(String[] args) throws Exception {
        String path = "Database.txt";

        FileWriter fw = new FileWriter(path, true);
//        FileOutputStream fos = new FileOutputStream(f);
//        OutputStreamWriter osw = new OutputStreamWriter(fos);
//        BufferedWriter bWriter = new BufferedWriter(fw);
        BufferedReader reader = new BufferedReader(new FileReader(path));

        boolean name = true;
        Scanner s = new Scanner(System.in);
        while (name) {
            System.out.println("Would you like to search the database for a name? (Yes/No)");
            String yesNo = s.nextLine();
            if (yesNo.equalsIgnoreCase("Yes")) {
                System.out.println("Please enter a name: ");
                String enter = s.nextLine();
                String line = "";
                name = false;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(enter)) {
                        System.out.println("The name is already in the database. Thank you!");
                        name = true;
                        break;
                    }
                }
                if (!name) {
                    System.out.println("Adding the name to the database...");
                    //Files.write(Paths.get("Database.txt"), (enter + "\n").getBytes(), StandardOpenOption.APPEND);

                    fw.write(enter + "\n");
                    fw.flush();
                    System.out.println("Thank you! " + enter + " is now in the database.");
                    name = true;
                }
            } else if (yesNo.equalsIgnoreCase("No")) {
                name = false;
            } else {
                System.out.println("Input invalid. Please enter Yes or No");
                name = true;
            }
        }
        System.out.println("Thank you for using the Database.");
    }
}