#include <xinu.h>

uint32 totcputicks(pid32 pid) {
	if (isbadpid(pid)) {
		return SYSERR;
	}
	return proctab[pid].prcputicks;
}
