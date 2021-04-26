import java.io.*;


public class FileIO {


    public static void main(String[] args) throws Exception{


        //TODO: Open file output.txt and write to it

        //Then call printer

        File f = new File("output.txt");


        FileOutputStream fos = new FileOutputStream(f);

        OutputStreamWriter osw = new OutputStreamWriter(fos);

        BufferedWriter bWriter = new BufferedWriter(osw);


        bWriter.write("Hello World!\n");

        bWriter.write("CS180 is awesome!");

        bWriter.close();


        printer(f);

    }


    public static void printer(File f) throws Exception {


        //TODO: Read from file and print out to console

        FileInputStream fis = new FileInputStream(f);

        BufferedReader d = new BufferedReader(new InputStreamReader(fis));


        String read;

        while((read = d.readLine()) != null) {

            System.out.println(read);

        }

        d.close();

    }

}
