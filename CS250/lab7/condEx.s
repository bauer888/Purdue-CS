.section .text
.global _start
_start:
        mov r0, #0
        mov r1, #0
        mov r2, #1000
        mul r3, r2, r2
        mul r10, r3, r2
loop:
        add r0, r0, #1
        cmp r1, #0
	addlt r2, r2, #2
        addge r2, r2, #1
	add r2, r2, #0
	cmp r0, r10
	blt loop
	MOV r7, #1
	swi 0
