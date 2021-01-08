CC = gcc

CFLAGS = -Werror -Wall -std=c99 `pkg-config --cflags gtk+-2.0`

LIBS = -lm `pkg-config --libs gtk+-2.0`

all: ../lib/imageio.o negate.c
	$(CC) $(CFLAGS) -o negate ../lib/imageio.o negate.c $(LIBS)

clean:
	rm -f negate *.o
