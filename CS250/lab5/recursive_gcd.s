.section .text
printformat:
.asciz "Result: %d\n"

.global main
.balign 4
main:
	push {lr}
	push {v1}
	push {v2}
	mov v1, r1
	ldr r0, [v1, #4]
	bl atoi
	mov v2, r0

	ldr r0, [v1, #8]
	bl atoi

	MOV r1, v2
	bl _gcd

	ldr r0, =printformat
	bl printf

	pop {v2}
	pop {v1}
	pop {pc}

_gcd:
	push {lr}
	CMP r0, r1
	SUBGT r0, r0, r1
	SUBLT r1, r1, r0
	blne _gcd
	pop {pc}
