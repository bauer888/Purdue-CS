import java.util.Scanner;

/**
 * CS180 HW 1
 *
 * This program will create a business card for the user after the user inputs the prompted information
 *
 * @author Jack Bauer
 *
 * @version 6/24/2019
 *
 */

public class BusinessCard {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = s.nextLine(); //The user's name

        System.out.print("Enter your Year of Birth: ");
        int year = s.nextInt(); //The user's birth year
        int age = 2019 - year; //The user's age

        System.out.print("Enter your GPA: ");
        double gpa = s.nextDouble(); //The user's GPA

        System.out.print("Enter your Major: ");
        s.nextLine();
        String major = s.nextLine(); //The user's major

        System.out.print("Enter your Email: ");
        String email = s.nextLine(); //The user's email
        System.out.println();

        //Printing out the information
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.printf("GPA: %.2f%n", gpa);
        System.out.println("Major: " + major);
        System.out.println("Email: " + email);

    }
}
