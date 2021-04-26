/* userret.c - userret */

#include <xinu.h>

/*------------------------------------------------------------------------
 *  userret  -  Called when a process returns from the top-level function
 *------------------------------------------------------------------------
 */
void	userret(void)
{
	pid32 pid = getpid();
	intmask mask;
	struct procent* prptr;
	int32 i;

	mask = disable();
	if (isbadpid(pid) || (pid == NULLPROC)) {
		restore(mask);
		return SYSERR;
	}

	prptr = &proctab[pid];
	send(prptr->prparent, pid);
        for (i=0; i<3; i++) {
                close(prptr->prdesc[i]);
        }
        freestk(prptr->prstkbase, prptr->prstklen);
	prptr->prstate = PR_FREE;
	resched();
//	kill(getpid());			/* Force process to exit */
}
