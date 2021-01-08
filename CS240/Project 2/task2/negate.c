//////DO NOT MODIFY////////
#include "../lib/imageio.h"
#include <string.h>
#include <stdlib.h>
//////DO NOT MODIFY///////

int main(int argc, char* argv[]) {
	int w;
	int h;
	uint32_t *original = LoadImage(argv[1], &w, &h);
	uint32_t* inverted = (uint32_t*) malloc((w * h) * sizeof(uint32_t));
	char* ptr;
	for (int i = 0; i < (w * h); i++) {
		uint32_t pixel = original[i];
		ptr = (char*) &pixel;
		ptr[0] = 255 - ptr[0];
		ptr[1] = 255 - ptr[1];
		ptr[2] = 255 - ptr[2];
		inverted[i] = *(uint32_t*) ptr;
	}
	char* token = strtok(argv[1], "/");
	token = strtok(NULL, "/");
	token = strtok(NULL, "/");

	char invertFile[100];
	char invertpng[] = "_negate.png";
	strcpy(invertFile, token);
	strcpy(invertFile + (strlen(token) - 4), invertpng);
	SaveImage(invertFile, inverted, w, h);
	
	free(inverted);
}
