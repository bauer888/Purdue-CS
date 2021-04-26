#include <xinu.h>

void hackalert(void) {
	kprintf("Stack Smashing Detected");
	exit();
}
