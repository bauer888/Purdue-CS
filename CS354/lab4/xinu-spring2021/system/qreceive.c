#include <xinu.h>

umsg32 qreceive() {
//	kprintf("in qreceive");
	intmask mask;                   /* Saved interrupt mask         */
        struct  procent *prptr;         /* Ptr to process's table entry */
        umsg32  msg;                    /* Message to return            */

        mask = disable();
        prptr = &proctab[currpid];
        if (prptr->prhasmsg == FALSE) {
//		prqrecvend = 0;
//		prqrecvbeg = 0;
                prptr->prstate = PR_RECV;
                resched();              /* Block until message arrives  */
        }
	msg = prptr->prrecvqueue[prptr->prqrecvbeg];
	prptr->prsenderpid[prptr->prqrecvbeg] = senderpid;
	prptr->prqrecvbeg++;
	if (prptr->prqrecvbeg == RECVQSIZE) {
		prptr->prqrecvbeg = 0;
	}
//        msg = prptr->prmsg;             /* Retrieve message             */
        if (prptr->prqrecvbeg == prptr->prqrecvend) {
		prptr->prhasmsg = FALSE;
	}
//	prptr->prhasmsg = FALSE;        /* Reset message flag           */
        restore(mask);
        return msg;

}
