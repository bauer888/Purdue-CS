#include <xinu.h>

#define CPUUPPER	200000
#define IOSLEEP		300

void iobound(void) {
	int i = 0, j;
	while(xclockticks < 6000) { // until wall clock time of 6 sec is reached do
   		i++;
   		for(j=0; j < CPUUPPER; j++) ; // consume some more CPU cycles before blocking
   		sleepms(IOSLEEP);
	}
	j = getpid();
	kprintf("iobound: %d %d %d %d\n", j, totcputicks(j), proctab[j].prnumdepl, proctab[j].prnumvol);
}
