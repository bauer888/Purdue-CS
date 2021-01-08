#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdint.h>

void encrypt(uint32_t charCount, uint32_t key) {
	
	char enc[charCount];

	printf("Enter the text: \n");
	scanf("%s", enc);

	int byteNum = 0;
	int byteReal = 0;

	for (int i = 0; i < charCount; i++) {
		if (byteNum == 0) {
			byteReal = (key >> (8 * byteNum)) & 0xff;
			byteNum = 1;
			enc[i] = enc[i] ^ byteReal;
		} else if (byteNum == 1) {
			byteReal = (key >> (8 * byteNum)) & 0xff;
			byteNum = 2;
			enc[i] = enc[i] ^ byteReal;
		} else if (byteNum == 2) {
			byteReal = (key >> (8 * byteNum)) & 0xff;
			byteNum = 3;
			enc[i] = enc[i] ^ byteReal;
		} else if (byteNum == 3) {
			byteReal = (key >> (8 * byteNum)) & 0xff;
			byteNum = 0;
			enc[i] = enc[i] ^ byteReal;
		}
	}
	printf("The encrypted text is: %s", enc);
}

void decrypt(uint32_t charCount, uint32_t key) {
	
	char binary[charCount];

	printf("Enter the encrypted text: \n");
	scanf("%s", binary);

	uint32_t value = 0;
	int indexCounter = 0;
	char final[1000];
	int finalCounter = 0;
	int byteNum = 0;
	int byteReal = 0;
	for (int i = 0; i < charCount; i++) {
		if (((i + 1) % 8) == 0) {
			value = 0;
			indexCounter = 0;
			for(int j = i; j >= (i - 7); j--) {
				if (binary[j] == '1') {
					value += pow(2, indexCounter);
				}
				indexCounter++;
			}
			if (byteNum == 0) {
				byteReal = (key >> (8 * byteNum)) & 0xff;
				byteNum = 1;
				final[finalCounter] = (char) (value ^ byteReal);
			} else if (byteNum == 1) {
				byteReal = (key >> (8 * byteNum)) & 0xff;
				byteNum = 2;
				final[finalCounter] = (char) (value ^ byteReal);
			} else if (byteNum == 2) {
				byteReal = (key >> (8 * byteNum)) & 0xff;
				byteNum = 3; 
				final[finalCounter] = (char) (value ^ byteReal);
			} else if (byteNum == 3) {
				byteReal = (key >> (8 * byteNum)) & 0xff;
				byteNum = 0;
				final[finalCounter] = (char) (value ^ byteReal);
			}
			finalCounter++;	
		}	
	}
	printf("The decrypted text is: ");
	for (int i = 0; i < strlen(final); i++) {
		if (final[i] == '\0') {
			break;
		} 
		printf("%s", final[i]);
	}	
	
}

uint32_t findKey(unsigned char keyE[], unsigned char keyD[]) {
	int value = 0;
	int indexCounter = 0;
	char eChar[4];
	int keyCounter = 0;
	for (int i = 0; i < strlen(keyE); i++) {
		if (((i + 1) % 8) == 0) {
			value = 0;
			indexCounter = 0;
			for (int j = i; j >= (i - 7); j--) {
				if (keyE[j] == '1') {
					value += pow(2, indexCounter);
				}	
				indexCounter++;
			}
			if (value == 1) {
				eChar[keyCounter] = (char) (value + '0');
			} else {
				eChar[keyCounter] = (char) value;
			}
			keyCounter++;
		}
	}
	uint32_t array[4];
	for (int i = 0; i < 4; i++) {
		array[i] = eChar[i] ^ keyD[i];
	}
	uint32_t final = array[3] + ((array[2] * 256)) + ((array[1] * 65536)) + ((array[0] * 
		1677721));
	return final;	
}

int main() {
	char choice = 'A';
	int charCount = 0;
	int key = 0;
	char binary[32];
	char unencrypted[4];
	while (1) {
		printf("Welcome to Cipher\n");
		printf("-----------------\n");
		printf("A-Encrypt Text\n");
		printf("B-Decrypt Text\n");
		printf("C-Find Key\n");
		printf("D-Exit\n");
		scanf("%s", &choice);
		if (choice == 'A') {
			printf("Enter the number of characters to encrypt: \n");
			scanf("%d", &charCount);
			printf("Enter the key: \n");
			scanf("%d", &key);
			encrypt(charCount, key);	
		} else if (choice == 'B') {	
			printf("Enter the number of characters to decrypt: \n");
			scanf("%d", &charCount);
			printf("Enter the key: \n");
			scanf("%d", &key);
			decrypt(charCount, key);
		} else if (choice == 'C') {
			printf("Enter 32-bit binary string encrypted characters: \n");
		       	scanf("%s", binary);
			printf("Enter 4 characters of unencrypted text: \n");
			scanf("%s", unencrypted);
			findKey(binary, unencrypted);
		} else if (choice == 'D') {
			return;
		} else {
			printf("Invalid Input\n");
		}	
	}
}

