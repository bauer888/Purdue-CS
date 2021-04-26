import java.util.Scanner;

/**
 * CS180 HW 2
 *
 * This program will generate a username based on the information the user enters
 *
 * @author Jack Bauer
 *
 * @version 6/24/2019
 *
 */

public class UsernameGen {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("What is your full name?");
        String name = s.nextLine(); //The user's full name
        String user = name.substring(0, 1).toLowerCase(); //The first letter of the user's name
        int start = name.indexOf(" "); //The index of the space between the first and last name
        name = user + name.substring(start + 1).toLowerCase(); //The username format without the age


        System.out.println("What is your birth year?");
        int year = s.nextInt(); //The user's birth year
        int age = 2019 - year; //The user's age



        System.out.println("Your username is " + name + age); //Full username
    }
}
