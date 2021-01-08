//////DO NOT MODIFY////////
#include "../lib/imageio.h"
//////DO NOT MODIFY////////
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char* argv[]) {
	int w;
	int h;
	int realC = atoi(argv[2]);
	uint32_t* original = LoadImage(argv[1], &w, &h);
	uint32_t* newCols = (uint32_t*) malloc((h * (w + (2 * realC))) * sizeof(uint32_t));
	char* ptr;
	int wcounter = 0;
	int bcounter = 0;
	int hcounter = 0;
	for (int i = 0; i < (w * (h + (2 * realC))); i++) {
		if (i < realC) {
			uint32_t pixel = original[0];
			ptr = (char*) &pixel;
			ptr[0] = 0;
			ptr[1] = 0;
			ptr[2] = 0;
			newCols[i] = *(uint32_t*) ptr;
		} else if (wcounter < w) {
			uint32_t pixel = original[i - ((hcounter * realC * 2) + realC)];
			ptr = (char*) &pixel;
			newCols[i] = *(uint32_t*) ptr;
			wcounter++;
		} else if (wcounter == w) {
			uint32_t pixel = original[0];
			ptr = (char*) &pixel;
			ptr[0] = 0;
			ptr[1] = 0;
			ptr[2] = 0;
			newCols[i] = *(uint32_t*) ptr;
			bcounter++;
		}
		if (bcounter == (realC * 2)) {
			wcounter = 0;
			bcounter = 0;
			hcounter++;
		}
	}

	char* token = strtok(argv[1], "/");
	token = strtok(NULL, "/");
	token = strtok(NULL, "/");

	char colFile[100];
	char colpng[] = "_addcols.png";
	strcpy(colFile, token);
	strcpy(colFile + (strlen(token) - 4), colpng);
	SaveImage(colFile, newCols, (w + (2 * realC)), h);

	free(newCols);
}
