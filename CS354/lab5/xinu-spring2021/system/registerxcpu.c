#include <xinu.h>

syscall registerxcpu(void (* fp)(void), uint32 cpulimit) {
	intmask mask;
	mask = disable();
	struct procent* prptr;
	prptr = &proctab[getpid()];
	if (cpulimit <= 0 || prptr->beenCalled == 1) {
//		kprintf("big balls");
		restore(mask);
		return SYSERR;
	}
	prptr->cpulimit = cpulimit;
	prptr->prxcpufp = fp;
	prptr->beenCalled = 1;
	prptr->prxcpureg = 1;
	restore(mask);
	return 0;
}
