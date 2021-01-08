/*
 * This is a convert program that takes a base and a POSITIVE number in that base
 * as arguments. It handles base 2, base 10, and base 16. It accepts the number as an 
 * argument and then converts it to  the other two bases. 
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "convert.h"
#include <stdbool.h>

char baseChars[25];
int baseTen = 10;
int baseTwo = 2; 
int baseSixteen = 16; 

int main(int argc, char** argv){
    int base, decimal_num;
    if (argc < 3) {
        printf("Usage: convert <base> <number>\n");
        exit(1);
    }
    
    base = atoi(argv[1]);
    int num = atoi(argv[2]); 

    /*
     * First we need to check whether the input number is valid
     * Say if base is 2 and input number is 35, which is not a binary
     */ 
    validateInput(base, argv[2]);



    if (base == 10) {
        convertToBase(num,baseTwo);
	convertToBase(num,baseSixteen); 
    }
    else if(base == 2) {
        decimal_num = convertToDecimal(base, argv[2]);
	printf("Converted to base 10: %d\n",decimal_num); 
        convertToBase(decimal_num,baseSixteen); 

    }
    else {
        decimal_num = convertToDecimal(base, argv[2]);
        printf("Converted to base 10: %d\n",decimal_num); 
        convertToBase(decimal_num,baseTwo); 
    }
    
	return 0;
}


/*
 * Check if the user inputs are valid
 */
void validateInput(int base, char num[]){
    printf("Number read in base %d: %s\n", base, num);

    if (base != 2 || base != 10 || base != 16) { 
	    printf("Invalid base!!\n");
	    exit(1);
    }

    if (num[0] == '-') {
	    printf("Invalid input number!!\n");
	    exit(1);
    }

    if (strlen(num) > 32) {
	printf("Invalid input number!!\n");
	exit(1);
    }	
    
    /*
	 *	TODO: Implement error checking to verify the following - 
          - The number is one of the three bases (2,10,16)
          - The number is positive. 
          - The number has less than 32 digits (Hint: Use strlen)
          - Use the following print statements for your output: 
               -printf("Invalid base!!\n");
               -printf("Invalid input number!!\n"); 
	 */
    
    // Initialize our base char array for later use
    setBaseChars();

    // Check if the input number matches the base
    int i = 0, j = 0;
    int isValid = 0;
    while (j < strlen(num)) {
        for (i = 0; i < base; i++){
          if  (toupper(num[j]) == baseChars[i]){
            isValid = 1;
          }
        }
        if (!isValid){
            printf("Wrong digit in number!!\n");
            exit(1);
        }
        isValid = 0;
        j++;
    }
    
}

/*
 *  Make baseChars filled with all the chars used in different bases
 */
void setBaseChars() {
    char m = '0';
    int i = 0;
    for (i = 0; i < 10; i++){
        baseChars[i] = m;
        m++;
    }
    
    m = 'A';
    for (i = 10; i < 25; i++){
        baseChars[i] = m;
        m++;
    }
}

/*
 * Convert number in given base to decimal
 */
int convertToDecimal(int base, char num[]){
	if (base == 2) {
		int place = strlen(num) - 1;
		int sum = 0;
		for (int i = 0; i < strlen(num); i++) {
			int j = num[i] - '0';
			j *= my_pow(2, place);
			place--;
			sum += j;			
		}
		return sum;
	} else if (base == 16) {
		int sum = 0;
		int place = strlen(num) - 1;
		for (int i = 0; i < strlen(num); i++) {
			if (num[i] == 'A') {
				int k = num[i] - 55;
				k *= my_pow(16, place);
				sum += k;
			} else if (num[i] == 'B') {
				int k = num[i] - 55;
				k *= my_pow(16, place);
                                sum += k;
			} else if (num[i] == 'C') {
				int k = num[i] - 55;
				k *= my_pow(16, place);
                                sum += k;
			} else if (num[i] == 'D') {
				int k = num[i] - 55;
				k *= my_pow(16, place);
                                sum += k;
			} else if (num[i] == 'E') {
				int k = num[i] - 55;
				k *= my_pow(16, place);
                                sum += k;
			} else if (num[i] == 'F') {
				int k = num[i] - 55;
				k *= my_pow(16, place);
                                sum += k;
			} else {
				int k = num[i] - '0';
				k *= my_pow(16, place);
				sum += k;
			}
			place--;
		}
		return sum;
	}
    /*
	 *	TODO: Write the code to convert input number to decimal(Hint: use given
	 *	function my_pow)
	 */

	return 0;
}

/*
 * Convert decimal back to the given base number
 */
void convertToBase(int num, int base){
	if (base == 2) {
		int mod = 0;
		bool a[1] = {true}; 
		while (a[1]) {
			if ((num / 2) == 0) {
				mod = num % 2;
				a[1] = false;
			}
		}
	}

	/*
	 *	TODO: Write the code to convert decimal back to the base. 
	 *
	 */    
}

/*
 * Return result of power calculation in math
 */
int my_pow(int base, int n){
    int s = 1;
    int i;
    for (i = 0; i < n; i++){
        s *= base;
    }
    return s;
}
