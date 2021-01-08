CC = gcc

CFLAGS = -Werror -Wall -std=c99 `pkg-config --cflags gtk+-2.0`

LIBS = -lm `pkg-config --libs gtk+-2.0`

all: ../lib/imageio.o change_intensity.c
	$(CC) $(CFLAGS) -o change_intensity ../lib/imageio.o change_intensity.c $(LIBS)

clean:
	rm -f change_intensity *.o
