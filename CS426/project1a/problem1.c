#include <stdio.h>

void problem1() {
    char buffer[20];
    for (int i = 0; i < 20; i++) {
        buffer[i] = 's';
    }
    buffer[-1] = 's';
    buffer[-10] = 's';
    buffer[-10000] = 's';
    buffer[-30] = 's';
    buffer[-40] = 's';
    buffer[-100000] = 's';
    printf("0: %p\n", &buffer[0]);
    printf("0: %c\n", buffer[0]);
    printf("-1: %p\n", &buffer[-1]);
    printf("-1: %c\n", buffer[-1]);
    printf("-10: %p\n", &buffer[-10]);
    printf("-10: %c\n", buffer[-10]);
    printf("-10000: %p\n", &buffer[-10000]);
    printf("-10000: %c\n", buffer[-10000]);
    printf("-100000: %p\n", &buffer[-100000]);
    printf("-100000: %c\n", buffer[-100000]);
    printf("-30: %p\n", &buffer[-30]);
    printf("-30: %c\n", buffer[-30]);
    printf("-40: %p\n", &buffer[-40]);
    printf("-40: %c\n", buffer[-40]);
}

int main(void) {
    problem1();
}