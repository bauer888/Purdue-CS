#include <xinu.h>

void wrongturnIII(pid32 z) {
	if (isbadpid(z)) {
		return;
	}
	struct procent* prptr;
	intmask mask;
	mask = disable();
	prptr = &proctab[z];
	long* sp = (long*) prptr->prstkptr;
	for (int i = 0; i < 11; i++) {
//		kprintf("for loop: %d\n", *sp);
		if (i != 8) {
			*sp= (long) malwareI;
		}
		sp++;
	}
	restore(mask);
	return;
}
