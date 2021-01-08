.section .data
number1: .skip 4
number2: .skip 4

.section .text
inputformat: .asciz "Please enter n \n"
readformat: .asciz "%d"
printformat: .asciz "F(%d): %d\n"

.global main
.balign 4
main:
	push {lr}
	@ldr r1, =number1
	ldr r0, =inputformat
	bl printf
	ldr r0, =readformat
	ldr r1, =number1
	bl scanf
	ldr r1, =number1
	ldr r1, [r1]
	mov r2, r1
	mov r3, #1
	mov r4, #0
	bl _fib
	mov r2, r4
	ldr r0, =printformat
	bl printf
	pop {pc}
_fib:
	push {lr}
	cmp r2, #0
	addgt r3, r3, r4
	subgt r4, r3, r4
	subgt r2, r2, #1
	blne _fib
	pop {pc}
