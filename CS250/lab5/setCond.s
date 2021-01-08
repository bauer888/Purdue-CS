.text

.global _start
_start:
	MOV R1, #8
	MOV R3, #0 
	MOVs R2, #10
	
	ADDne R3, R3,R1 
@export result
	mov r0, r3
	mov r7, #1
	swi 0
