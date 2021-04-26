#include <xinu.h>

void abc(void) {
	kprintf("PID: %d\n", getpid());
	kprintf("Normal execution\n");
}
