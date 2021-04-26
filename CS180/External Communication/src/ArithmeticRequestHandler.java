import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * A request handler for a server that performs simple arithmetic operations.
 *
 * Purdue University -- CS18000 -- Summer 2019 -- Network I/O -- Homework</p>
 *
 * @author Jack Bauer
 *
 * @version 7/22/2019
 */
public final class ArithmeticRequestHandler implements Runnable {
    /**
     * The client socket of this request handler.
     */
    private Socket clientSocket;

    /*
     * Error message constants.
     */

    private static final String ILLEGAL_NUM_ARGUMENTS_ERROR_MESSAGE;

    private static final String ILLEGAL_OPERATION_NAME_ERROR_MESSAGE;

    private static final String ILLEGAL_FIRST_OPERAND_ERROR_MESSAGE;

    private static final String ILLEGAL_SECOND_OPERAND_ERROR_MESSAGE;

    static {
        ILLEGAL_NUM_ARGUMENTS_ERROR_MESSAGE = String.format("%s: requests must include an operation name, and " +
                "two operands all separated by spaces\n", ArithmeticProtocol.ILLEGAL_REQUEST);

        ILLEGAL_OPERATION_NAME_ERROR_MESSAGE = String.format("%s: the operation name must be part of the protocol\n",
                ArithmeticProtocol.ILLEGAL_REQUEST);

        ILLEGAL_FIRST_OPERAND_ERROR_MESSAGE = String.format("%s: the first operand must be a valid integer\n",
                ArithmeticProtocol.ILLEGAL_REQUEST);

        ILLEGAL_SECOND_OPERAND_ERROR_MESSAGE = String.format("%s: the second operand must be a valid integer\n",
                ArithmeticProtocol.ILLEGAL_REQUEST);
    } //static

    /**
     * Constructs a newly allocated {@code ArithmeticRequestHandler} object with the specified client socket.
     *
     * @param clientSocket the client socket of this request handler
     * @throws IllegalArgumentException if the {@code clientSocket} argument is {@code null}
     */
    public ArithmeticRequestHandler(Socket clientSocket) throws IllegalArgumentException {
        if (clientSocket == null) {
            throw new IllegalArgumentException("clientSocket argument is null");
        } else {
            this.clientSocket = clientSocket;
        } //end if
    } //ArithmeticRequestHandler


    public boolean integer(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isNotOp(String string) {
        return !string.equals(ArithmeticProtocol.ADD.toString()) &&
                !string.equals(ArithmeticProtocol.MULTIPLY.toString()) &&
                !string.equals(ArithmeticProtocol.DIVIDE.toString()) &&
                !string.equals(ArithmeticProtocol.SUBTRACT.toString());
    }

    /**
     * Communicates with the client, and processes their requests until they disconnect.
     */
    @Override
    public void run() {
        BufferedReader br;
        BufferedWriter bw;
        String operation;
        String error;
        try {
            br = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(this.clientSocket.getOutputStream()));
            operation = br.readLine();

            while (!operation.isEmpty()) {
                String result = "";
                String[] operations = operation.split(" ");
                if (operations.length != 3) {
                    error = ILLEGAL_NUM_ARGUMENTS_ERROR_MESSAGE;
                    bw.write(error);
                    bw.flush();
                } else if (isNotOp(operations[0])) {
                    error = ILLEGAL_OPERATION_NAME_ERROR_MESSAGE;
                    bw.write(error);
                    bw.flush();
                } else if (!integer(operations[1])) {
                    error = ILLEGAL_FIRST_OPERAND_ERROR_MESSAGE;
                    bw.write(error);
                    bw.flush();
                } else if (!integer(operations[2])) {
                    error = ILLEGAL_SECOND_OPERAND_ERROR_MESSAGE;
                    bw.write(error);
                    bw.flush();
                } else {
                    int first = Integer.parseInt(operations[1]);
                    int second = Integer.parseInt(operations[2]);
                    if (operations[0].equals(ArithmeticProtocol.ADD.toString())) {
                        //result = (first + second) + "\n";
                        bw.write(first + second + "\n");
                        bw.flush();
                    } else if (operations[0].equals(ArithmeticProtocol.MULTIPLY.toString())) {
                        //result = (first * second) + "\n";
                        bw.write(first * second + "\n");
                        bw.flush();
                    } else if (operations[0].equals(ArithmeticProtocol.DIVIDE.toString())) {
                        if (second == 0) {
                            //error = "NaN";
                            bw.write("NaN\n");
                            bw.flush();
                        } else {
                            result = (first / second) + "\n";
                            bw.write(first / second + "\n");
                            bw.flush();
                        }
                    } else if (operations[0].equals(ArithmeticProtocol.SUBTRACT.toString())) {
                        //result = (first - second) + "\n";
                        bw.write(first - second + "\n");
                        bw.flush();
                    }
                }
                operation = br.readLine();
            }
            br.close();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } //run
}