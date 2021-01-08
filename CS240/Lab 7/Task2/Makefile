flags= -std=gnu99 -g -Wall -Werror
CC=  gcc
src= test_memcpy.c memcpy.c
Target=memcpy

all:$(src)
	$(CC) -o $(Target) $(src)

clean:
	rm $(Target) *.out total.txt
