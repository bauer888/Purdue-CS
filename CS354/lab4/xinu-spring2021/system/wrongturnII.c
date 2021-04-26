#include <xinu.h>

void wrongturnII(pid32 z) {
	if (isbadpid(z)) {
		return;
	}
	struct procent* prptr;
	intmask mask;
	mask = disable();
	prptr = &proctab[z];
	long* sp = (long*) prptr->prstkptr;
	long* temp;
	if (prptr->prstate == PR_READY) {
		sp += 9;
		temp = *sp;
		temp++;
		*temp = (long) malwareI;
//		restore(mask);
	}
	restore(mask);
	return;
}
