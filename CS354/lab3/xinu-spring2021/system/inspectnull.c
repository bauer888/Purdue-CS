#include <xinu.h>

void inspectnull(void) {
	long* stack;
	long* registers;
	kprintf("Testing inspectnull: \n");
	asm("pushl %%ebp\n\t"
		:"=g"(stack)
		:
		:
	);
	registers = stack;
	stack = registers + 9;

	kprintf("CS: %X\n eip: %X\n", *(stack + 1), *stack);
	kprintf("eflags: %X\n", *(stack + 2));

	stack = registers + 7;
	kprintf("All registers: \n");
	kprintf("eax: %08X (%u)\n", *stack, *stack);
	stack--;
	kprintf("ecx: %08X (%u)\n", *stack, *stack);
	stack--;
	kprintf("edx: %08X (%u)\n", *stack, *stack);
	stack--;
	kprintf("ebx: %08X (%u)\n", *stack, *stack);
	stack--;
	kprintf("esp: %08X (%u)\n", *stack, *stack);
	stack--;
	kprintf("ebp: %08X (%u)\n", *stack, *stack);
	stack--;
	kprintf("esi: %08X (%u)\n", *stack, *stack);
	stack--;
	kprintf("edi: %08X (%u)\n", *stack, *stack);
	stack--;
}
