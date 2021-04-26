#include <xinu.h>

uint32 xcpuutil(void) {
	struct procent* prptr = &proctab[currpid];
	uint32 result;

	result = ((xclockticks - prptr->prcputicks) / xclockticks) * 100;

	return result;
}
