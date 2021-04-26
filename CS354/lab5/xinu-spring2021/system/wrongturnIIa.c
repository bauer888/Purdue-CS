#include <xinu.h>

void* origretaddr;

void wrongturnIIa(pid32 z) {
	intmask mask;
	mask = disable();
	if (isbadpid(z)) {
                restore(mask);
		return;
        }
        struct procent* prptr;
        //intmask mask;
        //mask = disable();
        prptr = &proctab[z];
        long* sp = (long*) prptr->prstkptr;
        long* temp;
	temp = (long*) prptr->prstkptr + 11;
        if (prptr->prstate == PR_SUSP && *temp != (long) userret) {
                sp += 10;
                //temp = *sp;
                //temp++;
//		temp++; for part 3.2
		origretaddr = *sp;
                *sp = (long) malwareIa;
//		*temp = (long) malwareI; for part 3.2. Comment out above statement when testing
//              restore(mask);
        } else if (*temp == (long) userret) {
//problem 3.2
		long* original;
		original = sp;
		sp--;
//		kprintf("show up");
		for (int i = 0; i < 10; i++) {
			*(sp + i) = *(sp + i + 1);
			//sp++;
		}
		*(sp + 10) = (long) malwareI;
//		sp++;
		prptr->prstkptr = (long*) original - 1;
//		kprintf("show up");
//		kprintf("(%08X)\n", malwareI);
//		for (int i = 11; i >= 0; i--) {
//			kprintf("(%08X)\n", (prptr->prstkptr[i]));
			//kprintf("DATA (%08X) %08X (%d)\n", sp, *sp, *sp);

//		}
	}
        restore(mask);
        return;
}
