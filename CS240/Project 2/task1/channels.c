//////DO NOT MODIFY////////
#include "../lib/imageio.h"
#include <string.h>
#include <stdlib.h>
//////DO NOT MODIFY////////
int main(int argc, char* argv[]) {
	int w;
	int h;
	uint32_t *original = LoadImage(argv[1], &w, &h);
	uint32_t* red = (uint32_t*) malloc((w * h) * sizeof(uint32_t));
	uint32_t* blue = (uint32_t*) malloc((w * h) * sizeof(uint32_t));
	uint32_t* green = (uint32_t*) malloc((w * h) * sizeof(uint32_t));
	char* ptr;
	for (int i = 0; i < (w * h); i++) {
		uint32_t pixel = original[i];
		ptr = (char*) &pixel;
		ptr[1] = 0;
		ptr[2] = 0;
		red[i] = *(uint32_t*) ptr;		
	}

	for (int i = 0; i < (w * h); i++) {
		uint32_t pixel = original[i];
		ptr = (char*) &pixel;
		ptr[0] = 0;
		ptr[2] = 0;
		green[i] = *(uint32_t*) ptr;
	}

	for (int i = 0; i < (w * h); i++) {
		uint32_t pixel = original[i];
		ptr = (char*) &pixel;
		ptr[0] = 0;
		ptr[1] = 0;
		blue[i] = *(uint32_t*) ptr;
	}
	char* token = strtok(argv[1], "/");
	token = strtok(NULL, "/");
	token = strtok(NULL, "/");

	char redFile[100];
	char redpng[] = "_red.png";
	// copy "imageName" into new char array
	strcpy(redFile, token);
	// concat "imageName" with "_red.png"
	strcpy(redFile + (strlen(token) - 4), redpng);
	SaveImage(redFile, red, w, h);

	char greenFile[100];
       	char greenpng[] = "_green.png";
	strcpy(greenFile, token);
	strcpy(greenFile + (strlen(token) - 4), greenpng);
	SaveImage(greenFile, green, w, h);

	char blueFile[100];
	char bluepng[] = "_blue.png";
	strcpy(blueFile, token);
	strcpy(blueFile + (strlen(token) - 4), bluepng);
	SaveImage(blueFile, blue, w, h);
	
	free(red);
	free(green);
	free(blue);

}
