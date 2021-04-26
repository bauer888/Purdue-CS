unsigned int xyield(void) {
	int sysno = 9;
	int retno;
	asm("movl $9, %%ebx\n\t"
	    "int $46\n\t"
	: "=a"(retno)	     //output
	: //"a"(sysno)
	: "%ebx"); //clobbered
	return retno;
}
