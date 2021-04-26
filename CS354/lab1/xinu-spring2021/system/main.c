/*  main.c  - main */

#include <xinu.h>

process	main(void)
{
    
	kprintf("I am a process running main().");
	int32 assembly = addtwo(4, 7);
	int32 threead = test38();
	kprintf("\n%d", assembly);
	kprintf("\n%d", threead); 
/*    	kprintf("\nHello World!\n");
    	kprintf("\nI'm the first XINU app and running function main() in system/main.c.\n");
    	kprintf("\nI was created by nulluser() in system/initialize.c using create().\n");
    	kprintf("\nMy creator will turn itself into the do-nothing null process.\n");
    	kprintf("\nI will create a second XINU app that runs shell() in shell/shell.c as an example.\n");
    	kprintf("\nYou can do something else, or do nothing; it's completely up to you.\n");
    	kprintf("\n...creating a shell\n");
*/
	/* Run the Xinu shell */

/*	recvclr();
	resume(create(shell, 8192, 50, "shell", 1, CONSOLE));
*/
	/* Wait for shell to exit and recreate it */

/*	while (TRUE) {
		receive();
		sleepms(200);
		kprintf("\n\nMain process recreating shell\n\n");
		resume(create(shell, 4096, 20, "shell", 1, CONSOLE));
	}
*/
	return OK;
    
}
