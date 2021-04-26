/*  main.c  - main */

#include <xinu.h>

/*
pid32 x;
pid32 y;
pid32 z;

void callSend() {
	kprintf("call send\n");
	qsendb(x, 69);
	qsendb(x, 70);
	qsendb(x, 71);
	qsendb(x, 72);
	qsendb(x, 73);
	qsendb(x, 74);
	qsendb(x, 75);
}
*/
/*void callReceive() {
	kprintf("call receive\n");
	for (int i = 0; i < 5; i++) {
		umsg32 pp = qreceiveb();
		kprintf("1: %d\n", pp);
	}
}*/

process	main(void)
{
//	kprintf("\nhello\n");
	//resume(create(main, 8192, 50, "main", 0));
//	pid32 x = create((void*) abc, INITSTK, INITPRIO-2, "abc", 0, NULL);
//	pid32 y = create((void*) wrongturnIII, INITSTK, INITPRIO - 1, "wrong", 1, x);
//	wrongturnI(x);
//	resume(y);
//	x = create((void*) callReceive, 8192, INITPRIO-1, "receive", 0, NULL);
//	resume(x);
//	y = create((void*) callSend, 8192, INITPRIO+2, "send", 0, NULL);
//	resume(x);
/*	struct rocent* prptr = &proctab[x];
	for (int i = 0; i < 5; i++) {
		kprintf("%d\n", prptr->prrecvqueue[i]); 
	}
	for (int i = 0; i < 3; i++) {
		kprintf("%d\n", prptr->prsenderblkid[i]);
	}
	z = create((void*) callSend, 8192, INITPRIO-3, "send", 0, NULL);
	resume(z);
	resume(x); */
/*    	kprintf("\nHello World!\n");
    	kprintf("\nI'm the first XINU app and running function main() in system/main.c.\n");
    	kprintf("\nI was created by nulluser() in system/initialize.c using create().\n");
    	kprintf("\nMy creator will turn itself into the do-nothing null process.\n");
    	kprintf("\nI will create a second XINU app that runs shell() in shell/shell.c as an example.\n");
    	kprintf("\nYou can do something else, or do nothing; it's completely up to you.\n");
    	kprintf("\n...creating a shell\n");

	/* Run the Xinu shell */

//	recvclr();
//	resume(create(shell, 8192, 50, "shell", 1, CONSOLE));

	/* Wait for shell to exit and recreate it */

//	while (TRUE) {
//		receive();
//		sleepms(200);
// 		kprintf("\n\nMain process recreating shell\n\n");
//		resume(create(shell, 4096, 20, "shell", 1, CONSOLE));
//	}
	return OK;
    
}
