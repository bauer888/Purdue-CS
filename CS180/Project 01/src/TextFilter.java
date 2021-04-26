import java.util.Scanner;

/**
 * TODO: DESCRIBE YOUR PROJECT HERE
 *
 * @author Your Name, youremail@purdue.edu
 * @version MM/DD/YY
 */
public class TextFilter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Print hello message
        System.out.println("Welcome to TextFilter!");

        // Value to keep track of if the user wants to keep filtering text
        boolean keepFiltering;

        do {
            // Print options for the user to select
            System.out.println("Please select one of the following filtering options: \n");
            System.out.println("1. Filter Word\n" +
                    "2. Find-And-Replace\n" +
                    "3. Censor Information");

            // Save their choice
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {

                // PART 1 - Censoring Words

                String passage = "";  // The text to be filtered
                System.out.println("Please enter the passage you would like filtered: ");
                passage = scanner.nextLine();

                // TODO: PART 1 - Ask the user for a passage to censor


                String word;  // The word to be censored from the text phrase
                System.out.println("Please enter the word you would like to censor: ");
                word = scanner.nextLine();

                // TODO: PART 1 - Ask the user for a word to censor


                System.out.println("Uncensored: ");
                System.out.println(passage);

                // TODO: PART 1 - Implement your parsing here
                String change = word;
                int space = passage.indexOf(" ");
                for (int i = 0; i < change.length(); i++) {
                    change = change.replace(change.charAt(i), 'X');

//                    if ((passage.indexOf(word.substring(0, 1)) - 1) < 0) {
//                        if (passage.indexOf(word.substring(word.length() - 1)) + 1 > passage.length() - 1) {
//                            change = change.replace(change.charAt(i), 'X');
//                        } else if (passage.charAt(passage.indexOf(word.substring(word.length() - 1)) + 1) == ' ') {
//                            change = change.replace(change.charAt(i), 'X');
//                        } else if (passage.charAt(passage.indexOf(word.substring(word.length() - 1)) + 1) == ',') {
//                            change = change.replace(change.charAt(i), 'X');
//                        } else if (passage.charAt(passage.indexOf(word.substring(word.length() - 1)) + 1) == '.') {
//                            change = change.replace(change.charAt(i), 'X');
//                        } else if (passage.charAt(passage.indexOf(word.substring(word.length() - 1)) + 1) == '?') {
//                            change = change.replace(change.charAt(i), 'X');
//                        } else if (passage.charAt(passage.indexOf(word.substring(word.length() - 1)) + 1) == '!') {
//                            change = change.replace(change.charAt(i), 'X');
//                        }
//                    } else if (passage.indexOf(word.substring(word.length() - 1)) + 1 > passage.length() - 1) {
//                        if (passage.indexOf(word.substring(0, 1)) - 1 == ' ') {
//                            change = change.replace(change.charAt(i), 'X');
//                        }
//                    } else {
//                        if (passage.charAt(passage.indexOf(word.substring(word.length() - 1)) + 1) == ' ') {
//                            change = change.replace(change.charAt(i), 'X');
//                        } else if (passage.charAt(passage.indexOf(word.substring(word.length() - 1)) + 1) == ',') {
//                            change = change.replace(change.charAt(i), 'X');
//                        } else if (passage.charAt(passage.indexOf(word.substring(word.length() - 1)) + 1) == '.') {
//                            change = change.replace(change.charAt(i), 'X');
//                        } else if (passage.charAt(passage.indexOf(word.substring(word.length() - 1)) + 1) == '?') {
//                            change = change.replace(change.charAt(i), 'X');
//                        } else if (passage.charAt(passage.indexOf(word.substring(word.length() - 1)) + 1) == '!') {
//                            change = change.replace(change.charAt(i), 'X');
//                        } else if (passage.indexOf(word.substring(0, 1)) - 1 == ' ') {
//                            change = change.replace(change.charAt(i), 'X');
//                        }
//                    }
//                    if (change.charAt(i + 1) == ' ') {
//                        change = change.replace(change.charAt(i), 'X');
//                    } else if (change.charAt(i + 1) == '.') {
//                        change = change.replace(change.charAt(i), 'X');
//                    } else if (change.charAt(i + 1) == '!') {
//                        change = change.replace(change.charAt(i), 'X');
//                    } else if (change.charAt(i + 1) == '?') {
//                        change = change.replace(change.charAt(i), 'X');
//                    } else if (change.charAt(i + 1) == ',') {
//                        change = change.replace(change.charAt(i), 'X');
//                    }
                   // if (change.charAt(change.length()) != ' ')
                }

                String[] split = passage.split(" ");
                for (int i = 0; i < split.length; i++) {
                    if (split[i].equalsIgnoreCase(word)) {
                        split[i] = change;
                    } else if (split[i].length() == word.length() + 1) {
                        if (split[i].substring(0, word.length()).equalsIgnoreCase(word) &&
                                split[i].substring(word.length()).equals(",")) {
                            split[i] = change + split[i].substring(word.length());
                        } else if (split[i].substring(0, word.length()).equalsIgnoreCase(word) &&
                                split[i].substring(word.length()).equals(".")) {
                            split[i] = change + split[i].substring(word.length());
                        } else if (split[i].substring(0, word.length()).equalsIgnoreCase(word) &&
                                split[i].substring(word.length()).equals("!")) {
                            split[i] = change + split[i].substring(word.length());
                        } else if (split[i].substring(0, word.length()).equalsIgnoreCase(word) &&
                                split[i].substring(word.length()).equals("?")) {
                            split[i] = change + split[i].substring(word.length());
                        } else {
                            split[i] = split[i];
                        }
                    }
                }
                passage = "";
                for (int i = 0; i < split.length; i++) {
                    passage += split[i] + " ";
                }

                System.out.println("Censored: ");
                System.out.println(passage);


            } else if (choice == 2) {

                // PART 2 - Replacing Words


                String passage = "";  // The text to be filtered
                System.out.println("Please enter the passage you would like filtered: ");
                passage = scanner.nextLine();


                // TODO: PART 2 - Ask the user for a passage to filter


                String replace;  // The word to be filtered from the text phrase
                System.out.println("Please enter the word you would like to replace: ");
                replace = scanner.nextLine();

                //TODO: PART 2 - Ask the user for a word to replace


                String insert;  // The word to be inserted in the text phrase
                System.out.println("Please enter word you would like to insert: ");
                insert = scanner.nextLine();

                //TODO: PART 2 - Ask the user for a word to insert


                System.out.println("Uncensored: ");
                System.out.println(passage);


                // TODO: PART 2 - Implement your parsing here

                passage = passage.replaceAll(replace, insert);
                System.out.println("Censored: ");
                System.out.println(passage);



            } else if (choice == 3) {

                // PART 3 - Censoring Personal Information


                /*
                 * DO NOT ALTER THIS CODE! This formatting is imperative to the completion of this task.
                 *
                 * Keep asking for input until the user enters an empty line
                 * If they enter an empty line and the phrase is empty, keep waiting for input
                 */
                String passage = "";  // String for holding text to be filtered

                System.out.println("Please enter the phrase you would like to censor information from: ");

                while (true) {

                    // Obtain a line from the user
                    String temp = scanner.nextLine();

                    if (!passage.isEmpty() && temp.isEmpty()) {
                        break;
                    } else if (passage.isEmpty() && temp.isEmpty()) {
                        continue;
                    }


                    // Add the contents of temp into the phrase
                    passage += temp;


                    // Append a newline character to each line for parsing
                    // This will separate each line the user enters
                    // To understand how input is formatted in Part 3, please refer to the handout.
                    passage += '\n';
                }


                // Print the uncensored passage
                System.out.println("Uncensored: ");
                System.out.println(passage);

                // TODO: PART 3 - Implement your parsing here

                String[] pass = passage.split("\n");
                passage = "";
                for (int i = 0; i < pass.length; i++) {
                    if (pass[i].substring(0, 6).equalsIgnoreCase("Name: ")) {
                        pass[i] = pass[i].substring(6);
                        char[] names = pass[i].toCharArray();
                        pass[i] = "";
                        for (int j = 1; j < names.length - 1; j++) {
                            if (Character.isLetter(names[j])) {
                                names[j] = '*';
                            }
                            pass[i] += names[j];
                        }
                        pass[i] = "Name: " +  names[0] + pass[i] + names[names.length - 1];
                        passage += pass[i] + "\n";
                    } else if (pass[i].substring(0, 7).equalsIgnoreCase("Phone: ")) {
                        pass[i] = pass[i].substring(7);
                        char[] phones = pass[i].toCharArray();
                        pass[i] = "";
                        for (int j = 0; j < phones.length - 4; j++) {
                            if (Character.isDigit(phones[j])) {
                                phones[j] = '*';
                            }
                            pass[i] += phones[j];
                        }
                        for (int j = 8; j < phones.length; j++) {
                            pass[i] += phones[j];
                        }
                        pass[i] = "Phone: " + pass[i];
                        passage += pass[i] + "\n";
                    } else if (pass[i].substring(0, 7).equalsIgnoreCase("Email: ")) {
                        pass[i] = pass[i].substring(7);
                        char[] emails = pass[i].toCharArray();
                        pass[i] = "";
                        for (int j = 1; j < emails.length - 4; j++) {
                            if (emails[j - 1] == '@') {
                                emails[j] = emails[j];
                            } else if (emails[j] == '@') {
                                emails[j] = emails[j];
                            } else if (Character.isLetter(emails[j])) {
                                emails[j] = '*';
                            } else if (Character.isDigit(emails[j])) {
                                emails[j] = '*';
                            } else if (emails[j] == '.') {
                                emails[j] = '.';
                            } else {
                                emails[j] = '*';
                            }

                            pass[i] += emails[j];
                        }
                        pass[i] = "Email: " + emails[0] + pass[i] + emails[emails.length - 4] +
                                emails[emails.length - 3] + emails[emails.length - 2] + emails[emails.length - 1];
                        //pass[i] = pass[i].replace(pass[i].substring(8, a), "*");
                        //pass[i] = pass[i].replace(pass[i].substring(a + 2, pass[i].length() - 4), "*");
                        passage += pass[i] + "\n";
                    } else {
                        passage += pass[i] + "\n";
                    }
                }
                // Print the censored passage
                System.out.println("Censored: ");
                System.out.println(passage);

            } else {

                // They entered a number that was not 1, 2 or 3
                System.out.println("The option you chose was invalid!");

            }


            System.out.println("Would you like to keep filtering? Yes/No");

            // TODO: PART 4 - Update the value of keepGoing accordingly
            // TODO: PART 4 - Replace the line below with your code
            String yesNo = scanner.next();
            keepFiltering = yesNo.equalsIgnoreCase("Yes");


        } while (keepFiltering);

        System.out.println("Thank you for using TextFilter!");

    }

}