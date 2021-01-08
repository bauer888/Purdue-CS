flags= -std=c99 -g -Wall -D_SVID_SOURCE
CC=  gcc
src= word_count.c
Target=word_count

all:$(src)
	$(CC) $(flags) -o $(Target) $(src) -lm

clean:
	rm $(Target) *.out total.txt