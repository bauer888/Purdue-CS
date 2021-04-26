 	.text
	.globl detourguide
	.extern callback
	.extern saved

detourguide:
		pushl	%ebp
		movl	%esp, %ebp
		pushfl
		pushal

		call	*callback
		movl	saved,	%ecx
		movl	%ecx, 	4(%ebp)

		popal
		popfl

		movl (%esp), %ebp
	        add $4, %esp

		ret
