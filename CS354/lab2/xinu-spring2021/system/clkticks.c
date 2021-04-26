#include <xinu.h>

uint32 clkticks(void) {
	intmask mask = disable();
	restore(mask);
	return xclockticks;
}
