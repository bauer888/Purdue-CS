#include <xinu.h>

int threeadd(int x, int y, int z) {
	kprintf("\n%d, %d, %d", x, y, z);
	return x + y + z;
}

