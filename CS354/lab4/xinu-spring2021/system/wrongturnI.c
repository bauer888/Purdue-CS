#include <xinu.h>

void wrongturnI(pid32 z) {
	struct procent* prptr;
	intmask mask;
	mask = disable();
	long* sp;
	long* reg;
//	prptr = &proctab[z];
//	kprintf("stack pointer: %s", &prptr->prstkptr);
	if (isbadpid(z)) {
		return;
	}

	prptr = &proctab[z];
/*	sp = (long*) prptr->prstkptr;
	reg = sp;
	sp += 7;
	long* eax = sp;
	sp--;
	long* ecx = sp;
	sp--;
	long* edx = sp;
	sp--;
	long* ebx = sp;
	sp--;
	long* esp = sp;
	sp--;
	sp--;
	long* esi = sp;
	sp--;
	long* edi = sp;
	sp--;
*/
	long* temp;
	temp = (long*) prptr->prstkptr + 11;
	if (*temp == (long) userret) {
		temp = (long*) prptr->prstkptr + 10;
		*temp = (long) malwareI;
	}
//	kprintf("eax %08X (%u)\n", *sp, *sp);
//	if (prptr->prstate == PR_SUSP) {
//		if (*eax == 0 && *ecx == 0 && *edx == 0 && *ebx == 0 && *esi == 0 && *edi == 0 && *esp == 0) {
//			temp = (long*) prptr->prstkptr + 10;
//			*temp = (long) malwareI;
//			restore(mask);
//		}
//	}
	restore(mask);
	return;
}
