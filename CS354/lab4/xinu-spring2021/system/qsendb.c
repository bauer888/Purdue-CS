#include <xinu.h>

syscall qsendb(pid32 pid, umsg32 msg) {

        intmask mask;                   /* Saved interrupt mask         */
        struct  procent *prptr;         /* Ptr to process's table entry */

        mask = disable();
        if (isbadpid(pid)) {
                restore(mask);
                return SYSERR;
        }
//	senderpid = pid;
        prptr = &proctab[pid];
        if (prptr->prhasmsg && prptr->prqrecvend == prptr->prqrecvbeg) {
		if (prptr->prnumblockedsenders > NUMSENDERBLK) {
			restore(mask);
			return SYSERR;
		}
                prptr->prstate = PR_SENDERBLK;
		prptr->prsenderblkid[prptr->prsndblkend] = pid;
//		kprintf("pid: %d", prptr->prsenderblkid[prptr->prsndblkend]);
		prptr->prsndblkend++;
		if (prptr->prsndblkend == NUMSENDERBLK) {
			prptr->prsndblkend = 0;
		}
		prptr->prsendermsg = msg;
//		blockedpid = pid;
		prptr->prnumblockedsenders++;
		resched();
		//restore(mask);
                //return SYSERR;
        } else {

//      prqrecvend = prqrecvbeg + 1;
//      if (prqrecvend = RECVQSIZE) {
//              prqrecvend = 0;
//      }
        	prptr->prrecvqueue[prptr->prqrecvend] = msg;             /* Deliver message              */
        	prptr->prqrecvend++;
//      if (prqrecvbeg = RECVQSIZE) {
//              prqrecvbeg = 0;
//      }
//      prqrecvend++;
        	if (prptr->prqrecvend == RECVQSIZE) {
        	        prptr->prqrecvend = 0;
        	}
        	senderpid = pid;
//      prptr->prsenderpid[prrecvbeg] = senderpid;
        	prptr->prhasmsg = TRUE;         /* Indicate message is waiting  */

        /* If recipient waiting or in timed-wait make it ready */

        	if (prptr->prstate == PR_RECV) {
        	        ready(pid);
        	} else if (prptr->prstate == PR_RECTIM) {
        	        unsleep(pid);
        	        ready(pid);
        	}
// 	      	restore(mask);          /* Restore interrupts */
//        	return OK;
	}
	restore(mask);
	return OK;
}

