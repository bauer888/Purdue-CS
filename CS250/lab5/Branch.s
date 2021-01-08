.text

.global _start
_start:
	MOV R1, #8
	MOV R3, #0
	CMP R2, #10
	BEQ _exit
_not:
	ADD R3, R3, R1
_exit:
	mov r0, r3
	mov r7, #1
	swi 0
