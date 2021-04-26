import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Debug {

    public static void main(String[] args) throws Exception {
        String path = "Debug.txt";
        int userChoice;

        File f = new File(path.trim());
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));

        //FileInputStream fs = new FileInputStream(f);
        FileWriter fWriter = new FileWriter(path, true);
        BufferedReader fileContents = new BufferedReader(new FileReader(path));

        do {
            Debug d = new Debug();
            System.out.println("Please choose one of the following: \n1.Print File Contents \n2.Add to file \n3.Quit\n");
            userChoice = Integer.parseInt(userInput.readLine());
            if(userChoice == 1) {
                String data = "";
                data = new String(Files.readAllBytes(Paths.get(path)));
                System.out.println(data);
                //d.printFileContents(fileContents);
            }
            else if (userChoice == 2) {
                d.writeToFile(fWriter,userInput);
            }

        } while(userChoice != 3);

    }

    public void printFileContents(BufferedReader fileContents) throws Exception {
        String currentLine = "";
        while ((currentLine = fileContents.readLine()) != null) {
            System.out.println(currentLine);
        }
    }

    public void writeToFile(FileWriter fWriter, BufferedReader userInput) {
        System.out.println("Enter the line you would like to add: ");
        try {
            String user = userInput.readLine();
            fWriter.write(user);
            fWriter.write("\n");
            fWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
