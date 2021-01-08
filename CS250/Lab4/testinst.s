  .global  _start

_start:
  mov r0, #1
  mov r1, #2
  AND r1, r0, r1
  EOR r2, r0, r1
  ADD r3, r2, #50
  SUB r4, r3, r1
  RSB r2, r0, r1
  ORR r5, r2, r0
  CMP r4, r5
  movgt r0, r4

  mov r7, #1   @ set r7 to 1 - the syscall for exit
               @ calls listed in /usr/include/asm/unistd.h
  swi 0        @ invoke the syscall from linux