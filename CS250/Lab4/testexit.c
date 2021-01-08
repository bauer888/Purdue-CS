#include <stdlib.h>
int main(int argc, char** argv) {
	int r0, r1, r2, r3, r4, r5, r7;
	r0 = atoi(argv[1]);
	r1 = atoi(argv[2]);
	r1 = r0 & r1;
	r2 = r0 ^ r1;
	r3 = r2 + 50;
	r4 = r3 - r1;
	r2 = r1 - r0;
	r5 = r2 | r0;
	if (r4 >= r5) {
		r0 = r4;
	}
	r7 = 1;
	exit(r0);
}
