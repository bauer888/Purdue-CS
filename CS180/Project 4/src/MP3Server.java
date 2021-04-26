import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Objects;


/**
 * A MP3 Server for sending mp3 files over a socket connection.
 */

/**
 * CS 180 - Project 04
 *
 * MP3Server: Code for running the Server
 *
 * @author Sithu Aung
 * @author Jack Bauer
 *
 * @version 04/10/2019
 *
 */

public class MP3Server {

    public static void main(String[] args) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(7500);
            System.out.printf("Socket open, waiting for connections on %s\n", serverSocket);
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread client = new Thread(clientHandler);
                client.start();
                //System.out.println("Client has connected!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/**
 * Class - ClientHandler
 *
 * This class implements Runnable, and will contain the logic for handling responses and requests to
 * and from a given client. The threads you create in MP3Server will be constructed using instances
 * of this class.
 */

/**
 * CS 180 - Project 04
 *
 * ClientHandler: Creates an Object Input Stream and an Object Output Stream
 *
 * @author Sithu Aung
 * @author Jack Bauer
 *
 * @version 04/10/2019
 *
 */

final class ClientHandler implements Runnable {

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public ClientHandler(Socket clientSocket) {
        try {
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is the start of execution for the thread. See the handout for more details on what
     * to do here.
     */
    public void run() {

        SongRequest request;
        try {
            request = (SongRequest) inputStream.readObject();
            while (request != null) {
                System.out.println("------------------------------------------------------------"); //60 -'s
                if (request.isDownloadRequest()) {
                    System.out.println("The Server has received a download request!");
                    String fileName = request.getArtistName() + " - " + request.getSongName() + ".mp3";

                    //Is a download request, but could not be found in record.txt
                    if (!fileInRecord(fileName)) {
                        SongHeaderMessage songHeaderMessage = new
                                SongHeaderMessage(true, "", "", -1);
                        outputStream.writeObject(songHeaderMessage);
                        outputStream.flush();
                        System.out.println("The song requested by the Client could not be found!");
                    }

                    //is a download request and was found in record.txt
                    if (fileInRecord(fileName)) {

                        int fileSize = Objects.requireNonNull(readSongData(fileName)).length;
                        System.out.println(fileSize);
                        SongHeaderMessage songHeaderMessage = new SongHeaderMessage(true,
                                request.getSongName(), request.getArtistName(), fileSize);
                        outputStream.writeObject(songHeaderMessage);
                        outputStream.flush();

                        System.out.println("The download has been sent to the Client!");

                        byte[] bytes = readSongData(fileName);
                        assert bytes != null;
                        sendByteArray(bytes);

                    }
                }

                //Not a download request, simply sends back song names
                if (!request.isDownloadRequest()) {
                    System.out.println("The Server has received a song list request!");
                    SongHeaderMessage songHeaderMessage = new SongHeaderMessage(false);
                    outputStream.writeObject(songHeaderMessage);
                    outputStream.flush();
                    sendRecordData();
                    System.out.println("The song list has been sent to the Client!");
                }

                request = (SongRequest) inputStream.readObject();

            } // end while
        } catch (Exception e) {
            //System.out.println("Client has disconnected!");
        }


    }

    /**
     * Searches the record file for the given filename.
     *
     * @param fileName the fileName to search for in the record file
     * @return true if the fileName is present in the record file, false if the fileName is not
     */
    private static boolean fileInRecord(String fileName) throws IOException {
        File f = new File("record.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);

            String recordLine = bfr.readLine();
            while (true) {
                if (fileName.equals(recordLine)) {
                    return true;
                }
                if (recordLine == null) {
                    return false;
                }
                recordLine = bfr.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Read the bytes of a file with the given name into a byte array.
     *
     * @param fileName the name of the file to read
     * @return the byte array containing all bytes of the file, or null if an error occurred
     */
    private static byte[] readSongData(String fileName) throws FileNotFoundException {

        byte[] byteArray = {};
        try {
            File f = new File("songDatabase/" + fileName);
            byteArray = new byte[(int) f.length()];
            InputStream is = new FileInputStream(f);
            is.read(byteArray);
            is.close();
        } catch (Exception e) {
            System.out.println("There was an error while reading the file data!");
        }
        return byteArray;


//        File f = new File("songDatabase/" + fileName);
//        FileInputStream fis = new FileInputStream(f);
//        byte[] byteArray;
//
//        try {
//            byteArray = fis.readAllBytes();
//        } catch (Exception e) {
//            return null;
//        }
//        return byteArray;

    }



    /**
     * Split the given byte array into smaller arrays of size 1000, and send the smaller arrays
     * to the client using SongDataMessages.
     *
     * @param songData the byte array to send to the client
     */
    private void sendByteArray(byte[] songData) {
        byte[] temp;
        for (int i = 0; i < songData.length; i += 1000) {
            if (i + 999 > songData.length) {
                temp = Arrays.copyOfRange(songData, i, songData.length);
            } else {
                temp = Arrays.copyOfRange(songData, i, i + 1000);
            }
            try {
                outputStream.writeObject(new SongDataMessage(temp));
                outputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Read ''record.txt'' line by line again, this time formatting each line in a readable
     * format, and sending it to the client. Send a ''null'' value to the client when done, to
     * signal to the client that you've finished sending the record data.
     */
    private void sendRecordData() {
        File f = new File("record.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);

            while (true) {
                String fileName = bfr.readLine();

                if (fileName == null) {
                    outputStream.writeObject(fileName);
                    return;
                }

                String artistName = fileName.substring(0, fileName.indexOf("-") - 1);
                String songName = fileName.substring(fileName.indexOf("-") + 2, fileName.indexOf(".mp3"));
                String songFormat = "“" + songName + "”" + " by: " + artistName;
                outputStream.writeObject(songFormat);
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
