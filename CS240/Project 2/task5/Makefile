CC = gcc

CFLAGS = -Werror -Wall -std=c99 `pkg-config --cflags gtk+-2.0`

LIBS = -lm `pkg-config --libs gtk+-2.0`

all: ../lib/imageio.o addcols.c
	$(CC) $(CFLAGS) -o addcols ../lib/imageio.o addcols.c $(LIBS)

clean:
	rm -f addcols *.o
