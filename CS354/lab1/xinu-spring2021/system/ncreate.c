#include <xinu.h>

pid32 ncreate(void* funcaddr, pid32 reqpid, uint32 ssize, pri16 priority, char* name, uint32 nargs, ...) {

	uint32 mask;
	pid32 pid;
	struct procent* prptr;
	uint32* saddr;

	mask = disable();
	if (ssize < MINSTK) {
		ssize = MINSTK;
	}
	ssize = (uint32) roundmb(ssize);

	if ( (priority < 1) || (reqpid == SYSERR) || ((saddr = (uint32*) getstk(ssize)) == (uint32*) SYSERR)
		|| reqpid < 0 || reqpid > NPROC - 1 || proctab[reqpid].prstate != PR_FREE) {
	//	if (reqpid < 0 || reqpid > NPROC - 1 || proctab[reqpid].prstate != PR_FREE) {
	//		restore(mask);
	//		return SYSERR;
	//	}
		restore(mask);
		return SYSERR;
	}

	prptr = &proctab[reqpid];

	//initialize process table entry
	prptr->prstate = PR_SUSP;
	prptr->prprio = priority;
	prptr->prstkbase = (char*) saddr;
	prptr->prname[PNMLEN-1] = NULLCH;
	for (int i = 0; i < PNMLEN-1 && (prptr->prname[i] = name[i]) != NULLCH; i++) {
		;
	}
	prptr->prsem = -1;
	prptr->prparent = (pid32) getpid();
	prptr->prhasmsg = FALSE;

	return reqpid;

}
