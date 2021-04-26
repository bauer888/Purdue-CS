	.text
	.globl test38

test38:
	pushl %ebp
	movl  %esp, %ebp
	pushfl
	pushl %ebx
	pushl $4
	pushl $3
	pushl $2
	call threeadd
	add $12, %esp
	popl %ebx
	//pushl %eax
	popfl
	movl (%esp), %ebp
	add $4, %esp
	//movl %eax store
	//pop %eax
	ret
