/* clkhandler.c - clkhandler */

#include <xinu.h>

/*------------------------------------------------------------------------
 * clkhandler - high level clock interrupt handler
 *------------------------------------------------------------------------
 */

void* saved;
void* callback;

void	clkhandler()
{
	struct procent* prptr;
	prptr = &proctab[getpid()];
	static	uint32	count1000 = 1000;	/* Count to 1000 ms	*/

	/* Decrement the ms counter, and see if a second has passed */

	if((--count1000) <= 0) {

		/* One second has passed, so increment seconds count */

		clktime++;

		/* Reset the local ms counter for the next second */

		count1000 = 1000;
	}
	xclockticks++;

	if (prptr->prxcpureg && xclockticks > prptr->cpulimit) {
		long detour = (long) detourguide;
		asm("movl 40(%%ebp), %0\n\t"
		    "movl %1, 40(%%ebp)\n\t"
		    : "=r" (saved)
		    : "r" (detour)
		    :);
		callback = prptr->prxcpufp;
		prptr->prxcpureg = 0;
		//long* sp = prptr->prstkptr;
		//sp += 10;
		//saved = *sp;
	}
	/* Handle sleeping processes if any exist */

	if(!isempty(sleepq)) {

		/* Decrement the delay for the first process on the	*/
		/*   sleep queue, and awaken if the count reaches zero	*/

		if((--queuetab[firstid(sleepq)].qkey) <= 0) {
			wakeup();
		}
	}

	/* Decrement the preemption counter, and reschedule when the */
	/*   remaining time reaches zero			     */

	if((--preempt) <= 0) {
		preempt = QUANTUM;
		resched();
	}
}
