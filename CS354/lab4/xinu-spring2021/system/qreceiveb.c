#include <xinu.h>

umsg32 qreceiveb() {
	intmask mask;                   /* Saved interrupt mask         */
        struct  procent *prptr;         /* Ptr to process's table entry */
        umsg32  msg;                    /* Message to return            */
//	struct procent* pointer;
	pid32 temp;

        mask = disable();
        prptr = &proctab[currpid];
        if (prptr->prhasmsg == FALSE) {
//              prqrecvend = 0;
//              prqrecvbeg = 0;
                prptr->prstate = PR_RECV;
                resched();              /* Block until message arrives  */
        }
	if (prptr->prnumblockedsenders > 0) {
		prptr->prrecvqueue[prptr->prqrecvend++] = prptr->prsendermsg;
//		senderpid = prptr->prsenderblkid[prptr->prsndblkbeg];
		prptr->prsenderpid[prptr->prqrecvbeg++] = prptr->prsenderblkid[prptr->prsndblkbeg++];
		temp = senderpid;
		if (prptr->prqrecvbeg == RECVQSIZE) {
			prptr->prqrecvbeg = 0;
		}
		if (prptr->prsndblkbeg == NUMSENDERBLK) {
			prptr->prsndblkbeg = 0;
		}
		ready(prptr->prsenderpid[prptr->prqrecvbeg - 1]);
		resched();
		prptr->prnumblockedsenders--;
	}
	/* NEED TO CHECK FOR BLOCKED MESSAGES. Thats not what im doing below thats storing blockedpid */
/*	if (blockedpid != 0 && blockedpid != prptr->prsenderblkid[prsndblkend]) {
		prptr->prsenderblkid[prsndblkend] = blockedpid;
		prsndblkend++;
		prptr->prsendermsg = proctab[blockedpid]->prmsg;
	}
*/      msg = prptr->prrecvqueue[prptr->prqrecvbeg];
        prptr->prsenderpid[prptr->prqrecvbeg] = temp;
	senderpid = temp;
        prptr->prqrecvbeg++;
        if (prptr->prqrecvbeg == RECVQSIZE) {
                prptr->prqrecvbeg = 0;
        }
//        msg = prptr->prmsg;            /* Retrieve message             */
        if (prptr->prqrecvbeg == prptr->prqrecvend) {
                prptr->prhasmsg = FALSE;
        }
//      prptr->prhasmsg = FALSE;        /* Reset message flag           */
        restore(mask);
        return msg;
}
