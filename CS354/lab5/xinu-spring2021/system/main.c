/*  main.c  - main */

#include <xinu.h>
//#define CPUUPPER        200000
//#define IOSLEEP         300

//void test(void) {
//	kprintf("hello\n");
//	registerxcpu((void*)malwareI, 5000);
//	int i = 0;
  //      while(xclockticks < 6000) { // until wall clock time of 6 sec is reached do
    //            i++;
//                for(j=0; j < CPUUPPER; j++) ; // consume some more CPU cycles before blocking
  //              sleepms(IOSLEEP);
      //  }
	//registerxcpu((void*)malwareI, 5000);

//}

process	main(void)
{

//	create process with same prio as main.
//	call wrongturnIIa on that process.
//	resume that process after.
//	pid32 x = create((void*) test, 8192, INITPRIO + 1, "test", 0, NULL);
//	resume(x);
//	pid32 y = create((void*) wrongturnIIa, 8192, INITPRIO, "wrong", 1, x);
//	resume(x);
//	suspend(x);
//	resume(y);
//	wrongturnIIa(x);
//	resume(x);
/*    	kprintf("\nHello World!\n");
    	kprintf("\nI'm the first XINU app and running function main() in system/main.c.\n");
    	kprintf("\nI was created by nulluser() in system/initialize.c using create().\n");
    	kprintf("\nMy creator will turn itself into the do-nothing null process.\n");
    	kprintf("\nI will create a second XINU app that runs shell() in shell/shell.c as an example.\n");
    	kprintf("\nYou can do something else, or do nothing; it's completely up to you.\n");
    	kprintf("\n...creating a shell\n");
*/
	/* Run the Xinu shell */

//	recvclr();
//	resume(create(shell, 8192, 50, "shell", 1, CONSOLE));

	/* Wait for shell to exit and recreate it */

/*	while (TRUE) {
		receive();
		sleepms(200);
		kprintf("\n\nMain process recreating shell\n\n");
		resume(create(shell, 4096, 20, "shell", 1, CONSOLE));
	}*/
	return OK;

}
