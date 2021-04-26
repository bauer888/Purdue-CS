#include <xinu.h>

syscall qsend(pid32 pid, umsg32 msg) {
//	kprintf("in qsend");

	intmask mask;                   /* Saved interrupt mask         */
        struct  procent *prptr;         /* Ptr to process's table entry */

        mask = disable();
        if (isbadpid(pid)) {
                restore(mask);
                return SYSERR;
        }

        prptr = &proctab[pid];
	if (prptr->prhasmsg && prptr->prqrecvend == prptr->prqrecvbeg) {
                restore(mask);
                return SYSERR;
        }

//	prqrecvend = prqrecvbeg + 1;
//	if (prqrecvend = RECVQSIZE) {
//		prqrecvend = 0;
//	}
        prptr->prrecvqueue[prptr->prqrecvend] = msg;             /* Deliver message              */
	prptr->prqrecvend++;
//	if (prqrecvbeg = RECVQSIZE) {
//		prqrecvbeg = 0;
//	}
//	prqrecvend++;
	if (prptr->prqrecvend == RECVQSIZE) {
		prptr->prqrecvend = 0;
	}
	senderpid = pid;
//	prptr->prsenderpid[prrecvbeg] = senderpid;
        prptr->prhasmsg = TRUE;         /* Indicate message is waiting  */

        /* If recipient waiting or in timed-wait make it ready */

        if (prptr->prstate == PR_RECV) {
                ready(pid);
        } else if (prptr->prstate == PR_RECTIM) {
                unsleep(pid);
                ready(pid);
        }
        restore(mask);          /* Restore interrupts */
        return OK;

}
