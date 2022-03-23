#include <stdio.h>
#include <stdlib.h>

void problem2() {
    char* buffer = malloc(20);
    for (int i = 0; i < 20; i++) {
        buffer[i] = 's';
    }
    buffer[-1] = 's';
    buffer[-10] = 's';
    buffer[-10000] = 's';  //comment out this line to avoid a segmentation fault
    buffer[-30] = 's';
    buffer[-40] = 's';
    buffer[-100000] = 's'; //comment out this line to avoid a segmentation fault
    printf("0: %p\n", &buffer[0]);
    printf("0: %c\n", buffer[0]);
    printf("-1: %p\n", &buffer[-1]);
    printf("-1: %c\n", buffer[-1]);
    printf("-10: %p\n", &buffer[-10]);
    printf("-10: %c\n", buffer[-10]);
    printf("-10000: %p\n", &buffer[-10000]); //comment out this line to avoid a segmentation fault
    printf("-10000: %c\n", buffer[-10000]); //comment out this line to avoid a segmentation fault
    printf("-100000: %p\n", &buffer[-100000]); //comment out this line to avoid a segmentation fault
    printf("-100000: %c\n", buffer[-100000]); //comment out this line to avoid a segmentation fault
    printf("-30: %p\n", &buffer[-30]);
    printf("-30: %c\n", buffer[-30]);
    printf("-40: %p\n", &buffer[-40]);
    printf("-40: %c\n", buffer[-40]);
    //originally had "free(buffer)" here, but that resulted in a double free or corruption error due to accessing out-of-bounds array index
    
}

int main(void) {
    problem2();
}