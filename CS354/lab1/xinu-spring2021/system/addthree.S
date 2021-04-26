/* Jack Bauer, bauer88, 02/10/2021 */

        .text
        .globl addthree

addthree:
        pushl   %ebp           	//Pushes  the register ebp to the stack.

        movl    %esp,%ebp      	//Sets the register ebp equal to a pointer to esp.

        pushfl                 	//Pushes the flags register to the top of the stack.

        pushl   %ebx           	//Pushes a pointer to the ebx register to the stack.

        movl    8(%ebp), %eax  	//Grabs the memory location of the first argument of the function and stores it in the register eax.
			       	//An offset of 8 is used because ebp and ebx, both 4 bytes, were both pushed to the stack.

        movl    12(%ebp), %ebx 	//Grabs the memory location of the second argument of the function and stores it in ebx. An offset of 12 is used
			       	//because ebp and ebx were both already pushed to the stack and eax now contains the memory value of ebp at the given offset.

	movl    16(%ebp), %edi 	//Grabs the memory locationi of the third argument of the function and stores it in the ebi register. An offset
				//of 16 is used because, on top of ebp and esp already being pushed to the stack, eax and ebx now contain the memory
				//value of ebp at the given offset.

        addl    %ebx,%eax      	//Adds the values first two function arguments together which is then stored in eax.

	addl    %edi,%eax	//Adds the values of eax and the third function argument which is then stored in eax.

        popl    %ebx           	//The top 4 bytes of the stack are removed and stored in ebx.

        popfl                  	//The long from the top of the stack is popped and stores that value in the flags register.

        movl    (%esp),%ebp    	//ebp is set equal to the memory location of esp.

        add     $4,%esp        	//Adds 4 to esp and the resulting value is stored in esp.

        ret                    	//Return
