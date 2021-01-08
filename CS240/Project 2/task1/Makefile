CC = gcc

CFLAGS = -Werror -Wall -std=c99 `pkg-config --cflags gtk+-2.0`

LIBS = -lm `pkg-config --libs gtk+-2.0`

all: ../lib/imageio.o channels.c
	$(CC) $(CFLAGS) -o channels ../lib/imageio.o channels.c $(LIBS)

clean:
	rm -f channels *.o
