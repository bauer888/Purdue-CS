CC = gcc

CFLAGS = -Werror -Wall -std=c99 `pkg-config --cflags gtk+-2.0`

LIBS = -lm `pkg-config --libs gtk+-2.0`

all: ../lib/imageio.o extract_image.c
	$(CC) $(CFLAGS) -o extract_image ../lib/imageio.o extract_image.c $(LIBS)

clean:
	rm -f extract_image *.o
