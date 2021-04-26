import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * An MP3 Client to request .mp3 files from a server and receive them over the socket connection.
 */

/**
 * CS 180 - Project 04
 *
 * MP3Client: Code for running the Client
 *
 * @author Sithu Aung
 * @author Jack Bauer
 *
 * @version 04/10/2019
 *
 */

public class MP3Client {

    public static void main(String[] args) throws Exception {

        while (true) {
            Socket socket = new Socket("128.211.185.213", 7500);
            //Socket socket = new Socket("66.70.189.118", 9478); //Bonus Challenge Socket
            //Socket socket = new Socket(InetAddress.getLocalHost(), 7500);
            Scanner clientScanner = new Scanner(System.in);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            String optionChooser;
            do {
                System.out.println("------------------------------------------------------------"); //60 -'s
                System.out.println("What would you like to do?");
                System.out.println("Enter '1' to see a list of the available songs");
                System.out.println("Enter '2' to download a song");
                System.out.println("Enter 'exit' to exit the program");
                optionChooser = clientScanner.nextLine();
                System.out.println("------------------------------------------------------------"); //60 -'s
                if (optionChooser.equals("exit")) {
                    System.out.println("Thank you for connecting to our Server. Goodbye!");
                    return;
                }
            } while (!optionChooser.equals("1") && !optionChooser.equals("2"));

            if (optionChooser.equals("1")) {
                SongRequest sr = new SongRequest(false);
                oos.writeObject(sr);
                oos.flush();
                System.out.println("You have sent a request for a song list to the Server!");
                System.out.println("------------------------------------------------------------"); //60 -'s
            }

            if (optionChooser.equals("2")) {

                System.out.print("Please enter the song name: ");
                String songName = clientScanner.nextLine();
                if (songName.equals("exit")) {
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Thank you for connecting to our Server. Goodbye!");
                    return;
                }

                System.out.print("Please enter the artist name: ");
                String artistName = clientScanner.nextLine();
                if (artistName.equals("exit")) {
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Thank you for connecting to our Server. Goodbye!");
                    return;
                }

                SongRequest sr = new SongRequest(true, songName, artistName);
                oos.writeObject(sr);
                oos.flush();
                System.out.println("You have sent a request for a download to the Server!");
                System.out.println("------------------------------------------------------------"); //60 -'s

            }  //if 2

            ResponseListener responseListener = new ResponseListener(socket);
            Thread t = new Thread(responseListener);
            t.start();
            t.join();
            socket.close();

        } //while true
    } //main method
} //class


/**
 * This class implements Runnable, and will contain the logic for listening for
 * server responses. The threads you create in MP3Server will be constructed using
 * instances of this class.
 */

/**
 * CS 180 - Project 04
 *
 * ResponseListener: Creates in Object Input Stream
 *
 * @author Sithu Aung
 * @author Jack Bauer
 *
 * @version 04/10/2019
 *
 */

final class ResponseListener implements Runnable {

    private ObjectInputStream ois;

    public ResponseListener(Socket clientSocket) {
        try {
            ois = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Listens for a response from the server.
     * <p>
     * Continuously tries to read a SongHeaderMessage. Gets the artist name, song name, and file size from that header,
     * and if the file size is not -1, that means the file exists. If the file does exist, the method then subsequently
     * waits for a series of SongDataMessages, takes the byte data from those data messages and writes it into a
     * properly named file.
     */


    public void run() {
        Object request;
        try {
            request = ois.readObject();
            if (request instanceof SongHeaderMessage) {
                String fileName = ((SongHeaderMessage) request).getArtistName() + " - " +
                        ((SongHeaderMessage) request).getSongName() + ".mp3";

                if (((SongHeaderMessage) request).isSongHeader() && ((SongHeaderMessage) request).getFileSize() != -1) {

                    String artistName = fileName.substring(0, fileName.indexOf("-") - 1);
                    String songName = fileName.substring(fileName.indexOf("-") + 2, fileName.indexOf(".mp3"));
                    String songFormat = "“" + songName + "”" + " by: " + artistName;


                    File f = new File("savedSongs/" + fileName);
                    FileOutputStream fos = new FileOutputStream(f);

                    int counter = 0;
                    Object nextByteArr = ois.readObject();
                    while (true) {
                        fos.write(((SongDataMessage) nextByteArr).getData());
                        fos.flush();
                        counter += ((SongDataMessage) nextByteArr).getData().length;
                        //System.out.print("Your download is in progress...\r");
                        System.out.print("Your download for " + songFormat + " is in progress...\r");
                        if (((SongHeaderMessage) request).getFileSize() == counter) {
                            break;
                        }
                        nextByteArr = ois.readObject();
                    }
                    //System.out.println("Your download is complete!");
                    System.out.println("Your download for " + songFormat + " is complete");
                }





                if (((SongHeaderMessage) request).isSongHeader() && ((SongHeaderMessage) request).getFileSize() == -1) {
                    System.out.println("The song could not be found!");
                }

                if (!((SongHeaderMessage) request).isSongHeader() && ((SongHeaderMessage) request).getFileSize()
                        != -1) {
                    System.out.println("Here's a list of the songs:");
                    try {
                        String s = (String) ois.readObject();
                        while (s != null) {
                            System.out.println(s);
                            s = (String) ois.readObject();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } // end if not download
            } //end if download
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Writes the given array of bytes to a file whose name is given by the fileName argument.
     *
     * @param songBytes the byte array to be written
     * @param fileName  the name of the file to which the bytes will be written
     */
    private void writeByteArrayToFile(byte[] songBytes, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(songBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}