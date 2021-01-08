_loop:
	CMP r0, r1
	BEQ _exit
	SUBGT r0, r0, r1
	SUBLT r1, r1, r0
	b _loop
_exit:
	mov r7, #1
	swi 0


