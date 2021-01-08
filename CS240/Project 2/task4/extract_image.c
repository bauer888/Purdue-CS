//////DO NOT MODIFY////////
#include "../lib/imageio.h"
//////DO NOT MODIFY////////
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char* argv[]) {
	int newW = atoi(argv[4]);
	int newH = atoi(argv[5]);
	int x = atoi(argv[2]);
	int y = atoi(argv[3]);
	int w;
	int h;
	int counter = 0;
	int hcounter = 0;
	uint32_t* original = LoadImage(argv[1], &w, &h);
	uint32_t* crop = (uint32_t*) malloc((newW * newH) * sizeof(uint32_t));
	//start index = w * (y - 1) + x;
	char* ptr;
	for (int i = 0; i < (newW * newH); i++) {
		if (i % newW == 0 && i != 0) {
			hcounter++;
			counter = 0;
		}
		uint32_t pixel = original[counter + ((y) * w) + x + (w * hcounter)];
		ptr = (char*) &pixel;
		crop[i] = *(uint32_t*) ptr;
		counter++;
		/*if (i % newW == 0) {
			hcounter++;
			counter = 0;
		}
		if (wcounter == (w - x)) {
			wcounter = 1;
			hcounter++;
		} else {
			wcounter++;
		}*/
	}	

	char* token = strtok(argv[1], "/");
	token = strtok(NULL, "/");
	token = strtok(NULL, "/");

	char cropFile[100];
	char croppng[] = "_extracted.png";
	strcpy(cropFile, token);
	strcpy(cropFile + (strlen(token) - 4), croppng);
	SaveImage(cropFile, crop, newW, newH);

	free(crop);
}
