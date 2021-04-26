#include <xinu.h>

pri16 xresume(pid32 pid) {
	return trapresume(pid);
}
