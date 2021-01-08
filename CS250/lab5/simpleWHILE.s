.text

.global _start
_start:
	MOV R1, #8
	MOV R2, #10
	MOV R3, #0 
_loop:
	CMP R2,#0  
	BEQ _exit 
	ADD R3, R3,R1 
        SUB R2, R2, #1
	B  _loop
_exit:
@export result
	mov r0, r3
	mov r7, #1
	swi 0
