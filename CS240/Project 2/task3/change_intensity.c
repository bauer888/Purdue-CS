//////DO NOT MODIFY////////
#include "../lib/imageio.h"
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
//////DO NOT MODIFY///////

int main(int argc, char* argv[]) {
	double d = atof(argv[2]);
	float f = (float) d;
	float temp0 = 0.0;
	float temp1 = 0.0;
	float temp2 = 0.0;
	int w;
	int h;
	uint32_t* original = LoadImage(argv[1], &w, &h);
	uint32_t* intense = (uint32_t*) malloc((w * h) * sizeof(uint32_t));
	unsigned char* ptr;
	for (int i = 0; i < (w * h); i++) {
		uint32_t pixel = original[i];
		ptr = (unsigned char*) &pixel;
		if (argv[2] >= 0) {
			temp0 = ((float) ptr[0]) * (1.0f + ((f) / 100.0f));
			temp1 = ((float) ptr[1]) * (1.0f + ((f) / 100.0f));
			temp2 = ((float) ptr[2]) * (1.0f + ((f) / 100.0f));
			if (temp0 > 255) {
				temp0 = 255;
			} 
			if (temp1 > 255) {
				temp1 = 255;
			} 
			if (temp2 > 255) {
				temp2 = 255;
			}
			ptr[0] = (int) temp0;
			ptr[1] = (int) temp1;
			ptr[2] = (int) temp2;

			intense[i] = *(uint32_t*) ptr;
		} else if (argv[2] < 0) {
			temp0 = ((float) ptr[0]) * (1.0f + ((f) / 100.0f));
			temp1 = ((float) ptr[1]) * (1.0f + ((f) / 100.0f));
			temp2 = ((float) ptr[2]) * (1.0f + ((f) / 100.0f));
			if (temp0 < 0) {
				temp0 = 0;
			} 
			if (temp1 < 0) {
				temp1 = 0;
			} 
			if (temp2 < 0) {
				temp2 = 0;
			}
			ptr[0] = (int) temp0;
			ptr[1] = (int) temp1;
			ptr[2] = (int) temp2;

			intense[i] = *(uint32_t*) ptr;
		}
	}
	
	char* token = strtok(argv[1], "/");
	token = strtok(NULL, "/");
	token = strtok(NULL, "/");

	char intenseFile[100];
	char intensepng[] = "_intensity.png";
	strcpy(intenseFile, token);
	strcpy(intenseFile + (strlen(token) - 4), intensepng);
	SaveImage(intenseFile, intense, w, h);
	free(intense);
}	
