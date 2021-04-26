import java.util.Scanner;

public class Connect4 {

    private char[][] gameBoard;
    private int moveCounter;
    private char red = 'O';
    private char yellow = 'X';
    private char empty = ' ';

    public Connect4() {
        gameBoard = new char[7][8];
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = empty;
            }
        }
        moveCounter = 0;
    }

    public char[][] getBoard() {
        char[][] newBoard = new char[7][8];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                newBoard[i][j] = gameBoard[i][j];
            }
        }
        return newBoard;
    }

    public int putPiece(int column, char color) {
        if (color == red) {
            for (int i = 6; i >= 0; i--) {
                if (gameBoard[i][column] != red && gameBoard[i][column] != yellow) {
                    gameBoard[i][column] = red;
                    moveCounter++;
                    return i;
                } else if (gameBoard[0][column] == red || gameBoard[0][column] == yellow) {
                    System.out.println("Column is full! Choose a valid column!");
                    return -1;
                }
            }
        } else if (color == yellow) {
            for (int i = 6; i >= 0; i--) {
                if (gameBoard[i][column] != red && gameBoard[i][column] != yellow) {
                    gameBoard[i][column] = yellow;
                    moveCounter++;
                    return i;
                } else if (gameBoard[0][column] == red || gameBoard[0][column] == yellow) {
                    System.out.println("Column is full! Choose a valid column!");
                    return -1;
                }
            }
        }
        return -1;
    }

    public char checkAlignment(int row, int column) {
//        System.out.println(row);
//        System.out.println(column);
        try {
            for (int i = column - 3; i <= column; i++) { // check horizontal
                if (i >= 0) {
                    char cell1 = gameBoard[row][i];
                    char cell2 = gameBoard[row][i + 1];
                    char cell3 = gameBoard[row][i + 2];
                    char cell4 = gameBoard[row][i + 3];
                    if ((cell1 == cell2) & (cell2 == cell3) & (cell3 == cell4)) {
                        return cell1;
                    }
                }
            }
        } catch(ArrayIndexOutOfBoundsException e) {

        }
        try {
            for (int i = row + 3; i >= row; i--) { // check vertical
                char cell1 = gameBoard[i][column];
                char cell2 = gameBoard[i - 1][column];
                char cell3 = gameBoard[i - 2][column];
                char cell4 = gameBoard[i - 3][column];
//                System.out.println(i);
//                System.out.println(column);
                if ((cell1 == cell2) & (cell2 == cell3) & (cell3 == cell4)) {
                    return cell1;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        for (row  = 0; row < gameBoard.length - 3; row++) { // "\" test
            for (column = 0; column < gameBoard[row].length - 3; column++) {
                char cell1 = gameBoard[row + 1][column + 1];
                char cell2 = gameBoard[row + 2][column + 2];
                char cell3 = gameBoard[row + 3][column + 3];
                char cell4 = gameBoard[row][column];
                if ((cell1 == red) && (cell2 == red) && (cell3 == red) && (cell4 == red)) {
                    return red;
                } else if ((cell1 == yellow) && (cell2 == yellow) && (cell3 == yellow) && (cell4 == yellow)) {
                    return yellow;
                }
            }
        }

        for (row = 0; row < gameBoard.length - 3; row++) { // "/" test
            for (column = 3; column < gameBoard[row].length; column++) {
                char cell1 = gameBoard[row + 1][column - 1];
                char cell2 = gameBoard[row + 2][column - 2];
                char cell3 = gameBoard[row + 3][column - 3];
                char cell4 = gameBoard[row][column];
                if ((cell1 == red) && (cell2 == red) && (cell3 == red) && (cell4 == red)) {
                    return red;
                } else if ((cell1 == yellow) && (cell2 == yellow) && (cell3 == yellow) && (cell4 == yellow)) {
                    return yellow;
                }
            }
        }
//        try {
////            System.out.println("Testing \\");
//            for (int i = row - 3; i <= row; i++) {
//                if (i >= 0) {
//                    char cell1 = gameBoard[i][i + 1];
//                    char cell2 = gameBoard[i + 1][i + 2];
//                    char cell3 = gameBoard[i + 2][i + 3];
//                    char cell4 = gameBoard[i + 3][i + 4];
////                    System.out.println("1"+cell1);
////                    System.out.println("2"+cell2);
////                    System.out.println("3"+cell3);
////                    System.out.println("4"+cell4);
//                    if ((cell1 == cell2) & (cell2 == cell3) & (cell3 == cell4)) {
//                        return cell1;
//                    }
//                }
//            }
//        } catch (ArrayIndexOutOfBoundsException e) {
//
//        }
//        try {
//            //System.out.println("Testing /");
//            for (int i = row + 3; i >= row; i--) { // check diagonal col 5 row 4
//                for (int j = column - 3; j <= column; j++) {
//                    //System.out.println(i + " " + j);
//                    if (i <= 6 && j >= 0) {
//                        char cell1 = gameBoard[i][j];
//                        char cell2 = gameBoard[i - 1][j + 1];
//                        char cell3 = gameBoard[i - 2][j + 2];
//                        char cell4 = gameBoard[i - 3][j + 3];
//                        if ((cell1 == cell2) & (cell2 == cell3) & (cell3 == cell4)) {
//                            return cell1;
//                        }
//                    }
//                }
//            }
//        } catch (ArrayIndexOutOfBoundsException e) {
//
//        }

//            for (int i = 0; i < row; i++) {
//                for (int j = 0; j < column; j++) {
//                    if (gameBoard[i][j] == red) {
//                        if (gameBoard[i][j + 1] == red && gameBoard[i][j + 2] == red && gameBoard[i][j + 3] == red) {
//                            return red;
//                        } else if (gameBoard[i][j - 1] == red && gameBoard[i][j - 2] == red &&
//                                gameBoard[i][j - 3] == red) {
//                            return red;
//                        } else if (gameBoard[i + 1][j] == red && gameBoard[i + 2][j] == red
//                                && gameBoard[i + 3][j] == red) {
//                            return red;
//                        } else if (gameBoard[i - 1][j] == red && gameBoard[i - 2][j] == red
//                                && gameBoard[i - 3][j] == red) {
//                            return red;
//                        } else if (gameBoard[i + 1][j + 1] == red && gameBoard[i + 2][j + 2] == red &&
//                                gameBoard[i + 3][j + 3] == red) {
//                            return red;
//                        } else if (gameBoard[i - 1][j - 1] == red && gameBoard[i - 2][j - 2] == red &&
//                                gameBoard[i - 3][j - 3] == red) {
//                            return red;
//                        } else {
//                            return ' ';
//                        }
//                    }
//                    if (gameBoard[i][j] == yellow) {
//                        if (gameBoard[i][j + 1] == yellow && gameBoard[i][j + 2] == yellow &&
//                                gameBoard[i][j + 3] == yellow) {
//                            return yellow;
//                        } else if (gameBoard[i][j - 1] == yellow && gameBoard[i][j - 2] == yellow &&
//                                gameBoard[i][j - 3] == yellow) {
//                            return yellow;
//                        } else if (gameBoard[i + 1][j] == yellow && gameBoard[i + 2][j] == yellow
//                                && gameBoard[i + 3][j] == yellow) {
//                            return yellow;
//                        } else if (gameBoard[i - 1][j] == yellow && gameBoard[i - 2][j] == yellow
//                                && gameBoard[i - 3][j] == yellow) {
//                            return yellow;
//                        } else if (gameBoard[i + 1][j + 1] == yellow && gameBoard[i + 2][j + 2] == yellow &&
//                                gameBoard[i + 3][j + 3] == yellow) {
//                            return yellow;
//                        } else if (gameBoard[i - 1][j - 1] == yellow && gameBoard[i - 2][j - 2] == yellow &&
//                                gameBoard[i - 3][j - 3] == yellow) {
//                            return yellow;
//                        } else {
//                            return ' ';
//                        }
//                    }
//                }
//            }
        return ' ';
    }

    public void printScreen() {
        System.out.print(" 0 1 2 3 4 5 6 7 ");
        for (int i = 0; i < gameBoard.length; i++) {
            System.out.println("\n------------------");
            System.out.print("|");
            for (int j = 0; j < gameBoard[i].length; j++) {
                System.out.print(gameBoard[i][j] + "|");
            }
        }
        System.out.println("\n------------------");


    }

    public void play() {
        Scanner s = new Scanner(System.in);
        char winner = ' ';
        while (true) {
            printScreen();
            if (moveCounter % 2 == 0) {
                System.out.println("Current Player: " + yellow);
                System.out.println("Current Move: " + moveCounter);
                boolean choose = true;
                while (choose) {
                    System.out.println("Choose a Column: ");
                    int column = s.nextInt();
                    if (column >= 0 && column <= 7) {
                        winner = checkAlignment(putPiece(column, yellow), column);
                        choose = false;
                    } else {
                        System.out.println("Choose a Valid Column!");
                        choose = true;
                    }
                }
                if (winner == 'X') {
                    printScreen();
                    System.out.println("After " + moveCounter + " turns, player X has won!");
                    break;
                }
                if (winner == 'O') {
                    printScreen();
                    System.out.println("After " + moveCounter + " turns, player O has won!");
                    break;
                }
            } else {
                System.out.println("Current Player: " + red);
                System.out.println("Current Move: " + moveCounter);
                boolean choose = true;
                while (choose) {
                    System.out.println("Choose a Column: ");
                    int column = s.nextInt();
                    if (column >= 0 && column <= 7) {
                        winner = checkAlignment(putPiece(column, red), column);
                        choose = false;
                    } else {
                        System.out.println("Choose a Valid Column!");
                        choose = true;
                    }
                }
                if (winner == 'O') {
                    printScreen();
                    System.out.println("After " + moveCounter + " turns, player O has won!");
                    break;
                }
                if (winner == 'X') {
                    printScreen();
                    System.out.println("After " + moveCounter + " turns, player X has won!");
                    break;
                }
            }
        }
    }
}

