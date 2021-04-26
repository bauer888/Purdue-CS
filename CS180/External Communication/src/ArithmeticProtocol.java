/**
 * A protocol for a server that performs simple arithmetic operations.
 *
 * Purdue University -- CS18000 -- Summer 2019 -- Network I/O -- Homework</p>
 *
 * @author Jack Bauer
 *
 * @version 7/22/2019
 */
public enum ArithmeticProtocol {
    /**
     * The constant representing the addition operation.
     */
    ADD,

    /**
     * The constant representing the subtraction operation.
     */
    SUBTRACT,

    /**
     * The constant representing the multiplication operation.
     */
    MULTIPLY,

    /**
     * The constant representing the division operation.
     */
    DIVIDE,

    /**
     * The constant representing an illegal request.
     */
    ILLEGAL_REQUEST
}