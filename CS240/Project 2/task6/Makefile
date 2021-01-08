CC = gcc

CFLAGS = -Werror -Wall -std=c99 `pkg-config --cflags gtk+-2.0`

LIBS = -lm `pkg-config --libs gtk+-2.0`

all: ../lib/imageio.o addrows.c
	$(CC) $(CFLAGS) -o addrows ../lib/imageio.o addrows.c $(LIBS)

clean:
	rm -f addrows *.o
