	.text
	.globl trapresume

trapresume:
	movl	4(%esp), %ecx
	pushl	%ebp
	movl	%esp, %ebp
	pushl	%ebx
	movl 	$3, %ebx
	pushfl
//	pushl	%edx
	pushl	%ecx
	int $46
	add	$4, %esp
//	pop	%edx
	popl	%ebx
	popfl
	movl	(%esp), %ebp
	add	$4, %esp
	ret
