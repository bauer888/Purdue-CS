//////DO NOT MODIFY////////
#include "../lib/imageio.h"
//////DO NOT MODIFY////////
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char* argv[]) {
	int w;
	int h;
	int realH = atoi(argv[2]);
	uint32_t* original = LoadImage(argv[1], &w, &h);
	uint32_t* newRows = (uint32_t*) malloc((w * (h + (2 * realH))) * sizeof(uint32_t));
	char* ptr;
	for (int i = 0; i < (w * (h + (2 * realH))); i++) {
		if (i < w * realH) {
			uint32_t pixel = original[0];
			ptr = (char*) &pixel;
			ptr[0] = 0;
			ptr[1] = 0;
			ptr[2] = 0;
			newRows[i] = *(uint32_t*) ptr;
		} else if (i >= w * realH && i < w * (h + realH)) {
			uint32_t pixel = original[i - (w * realH)];
			ptr = (char*) &pixel;
			newRows[i] = *(uint32_t*) ptr;
		} else if (i >= w * (h + realH)) {
			uint32_t pixel = original[0];
			ptr = (char*) &pixel;
			ptr[0] = 0;
			ptr[1] = 0;
			ptr[2] = 0;
			newRows[i] = *(uint32_t*) ptr;
		}
	}

	char* token = strtok(argv[1], "/");
	token = strtok(NULL, "/");
	token = strtok(NULL, "/");

	char rowFile[100];
	char rowpng[] = "_addrows.png";
	strcpy(rowFile, token);
	strcpy(rowFile + (strlen(token) - 4), rowpng);
	SaveImage(rowFile, newRows, w, (h + (2 * realH)));

	free(newRows);
}
