import java.io.*;
import java.util.Scanner;

public class Database {
    public static void main(String[] args) {
        File names = new File("Names.txt");
        File phones = new File("Phones.txt");
        File data = new File("Database.txt");

        try {
            if (!data.exists()) {
                data.createNewFile();
            }
            Scanner s = new Scanner(names);
            Scanner n = new Scanner(phones);
            Scanner d = new Scanner(data);
            Scanner input = new Scanner(System.in);
            PrintWriter pw = new PrintWriter(data);
//            FileReader fr1 = new FileReader(names);
//            FileReader fr2 = new FileReader(phones);
//            FileWriter fw = new FileWriter(data);
//            BufferedReader br1 = new BufferedReader(fr1);
//            BufferedReader br2 = new BufferedReader(fr2);
//            BufferedWriter bw = new BufferedWriter(fw);
            while (s.hasNextLine() && n.hasNextLine()) {
                String name = s.nextLine();
                String phone = n.nextLine();
                pw.append("NAME: ").append(name).append("\nPHONE: ").append(phone).append("\n\n");
                pw.flush();
            }
            System.out.println("Welcome to the DMV Phone Book.");
            System.out.print("Enter name of DMV: ");
            String dmv = input.nextLine();
            while (d.hasNextLine()) {
                String line = d.nextLine();
                if (!d.hasNextLine()) {
                    System.out.println("The name entered does not exist in the database.");
                    break;
                } else if (line.contains(dmv)) {
                    String number = d.nextLine();
                    System.out.println("The phone number for " + dmv + " is "+ number.substring(7));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
