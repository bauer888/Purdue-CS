#include <xinu.h>

void cpubound(void) {
	int i = 0, j;
	while(xclockticks < 6000) // loop until wall clock time of 6 sec is reached
   		i++;
	j = getpid();
	kprintf("cpubound: %d %d %d %d\n", j, totcputicks(j), proctab[j].prnumdepl, proctab[j].prnumvol);
}
