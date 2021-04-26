/*  main.c  - main */

#include <xinu.h>

process	main(void)
{
	kprintf("Main: \n");
//	chprio(currpid, 12);
//    	kprintf("\nHello World!\n");
//  	kprintf("\nI'm the first XINU app and running function main() in system/main.c.\n");
//    	kprintf("\nI was created by nulluser() in system/initialize.c using create().\n");
//    	kprintf("\nMy creator will turn itself into the do-nothing null process.\n");
//    	kprintf("\nI will create a second XINU app that runs shell() in shell/shell.c as an example.\n");
//    	kprintf("\nYou can do something else, or do nothing; it's completely up to you.\n");
//    	kprintf("\n...creating a shell\n");

	/* Run the Xinu shell */

//	recvclr();
//	resume(create((void*) cpubound, INITSTK, INITPRIO, "inspectnull\n", 0, NULL));
//	resume(create((void*) cpubound, INITSTK, INITPRIO, "inspectnull\n", 0, NULL));
//	sleepms(3);
//	resume(create((void*) cpubound, INITSTK, INITPRIO, "inspectnull\n", 0, NULL));
//	resume(create((void*) cpubound, INITSTK, INITPRIO, "inspectnull\n", 0, NULL));
	xcpuutil();
	kprintf("");
	kprintf("");
	kprintf("");
	kprintf("");
	kprintf("");
	kprintf("");
	kprintf("");
	kprintf("");
	kprintf("");
	kprintf("");
	kprintf("");
	kprintf("");
		kprintf("");
	kprintf("");
	kprintf("");
	kprintf("");
	xcpuutil();
	/* Wait for shell to exit and recreate it */

//	while (TRUE) {
//		receive();
//		sleepms(200);
//		kprintf("\n\nMain process recreating shell\n\n");
//		resume(create(shell, 4096, 20, "shell", 1, CONSOLE));
//	}
	return OK; 
}
