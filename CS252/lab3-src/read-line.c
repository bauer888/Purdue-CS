/*
 * CS252: Systems Programming
 * Purdue University
 * Example that shows how to read one line with simple editing
 * using raw terminal.
 */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <termios.h>
#include <stdbool.h>
#define MAX_BUFFER_LINE 2048

extern void tty_raw_mode(void);

// Buffer where line is stored
int line_length;
char line_buffer[MAX_BUFFER_LINE];

// Simple history array
// This history does not change. 
// Yours have to be updated.
int history_index = 0;
char* history[16];
/*char * history [] = {
  "ls -al | grep x", 
  "ps -e",
  "cat read-line-example.c",
  "vi hello.c",
  "make",
  "ls -al | grep xxx | grep yyy"
};*/
int history_length = 0;
int historyIndex = 0;
int capacity = 2;
char after[MAX_BUFFER_LINE];
char afterLength;
bool full = false;
int notHistoryIndex;
/*void addHistory(const char* line) {
	char* temp = strdup(line);
	//history_length may be larger then capacity
	//resize
	if (history_length == capacity + 1) {
		capacity *= 2;
		history = (char**) realloc(history, capacity * sizeof(char*));
	}
	//int size = strlen(temp);
	//temp[strlen(temp) - 1] = '\0';
	history[history_length] = line;
	history_length++;
	historyIndex++;
}
*/
void read_line_print_usage()
{
  char * usage = "\n"
    " ctrl-?       Print usage\n"
    " Backspace    Deletes last character\n"
    " up arrow     See last command in the history\n";

  write(1, usage, strlen(usage));
}

void backspace(int n) {
  char ch = 8;
  for (int i = 0; i < n; i++) {
    write(1, &ch, 1);
  }
}
/*
void initializeHistory() {
	history = (char**) malloc(capacity * sizeof(char*));
	//history has to have a starting line
	const char* temp = "";
	addHistory(temp);
}
*/
/*
 * Input a line with some basic editing.
 */
char * read_line() {
  struct termios original;
  tcgetattr(0, &original);
  afterLength = 0;
  line_length = 0;

  // Set terminal in raw mode
  tty_raw_mode();

  // Read one line until enter is typed
  while (1) {
    //initialize history if its null

    // Read one character in raw mode.
    char ch;
    read(0, &ch, 1);
    char s;
    if (ch>=32 && ch < 127) {
      // It is a printable character.
      //if (qposition == line_length) {
	// Do echo
	write(1,&ch,1);
	// If max number of character reached return.
	if (line_length==MAX_BUFFER_LINE-2) break; 
	// add char to buffer.
	line_buffer[line_length]=ch;
	line_length++;
	//position++;
      //} else {
	//char s;
	if (afterLength != 0) {
		for (int i = afterLength - 1; i >= 0; i--) {
			s = after[i];
			write(1, &s, 1);
		}
	}
	backspace(afterLength);
	/*char not_buffer[MAX_BUFFER_LINE];
	for (int i = 0; i < MAX_BUFFER_LINE; i++) {
		if (line_buffer[position + i] == '\0') {
			break;
		}
		not_buffer[i] = line_buffer[position + i];
	}
	write(1, &ch, 1);
	if (line_length==MAX_BUFFER_LINE-2) break;
	line_buffer[position]=ch;
	line_length++;
	position++;
	int after = 0;
	for (int i = 0; i < MAX_BUFFER_LINE; i++) {
		if (line_buffer[position + i] == '\0') {
			break;
		}
		write(1, &not_buffer[i], 1);
		after++;
	}
	backspace(after);*/
      //}
    }
    else if (ch==10) {
      // <Enter> was typed. Return line
      if (afterLength) {
	for (int i = afterLength - 1; i >= 0; i--) {
		s = after[i];
		line_buffer[line_length] = s;
		line_length++;
	}
      }
      if (line_length != 0) {
	if (history[historyIndex] == NULL) {
		history[historyIndex] = (char*) malloc(MAX_BUFFER_LINE);
	}
	strcpy(history[historyIndex], line_buffer);
	notHistoryIndex = historyIndex++;
	if (historyIndex >= history_length) {
		historyIndex = 0;
		full = true;
	}
      }
      afterLength = 0;
      // Print newline
      write(1,&ch,1);

      break;
    }
    else if (ch == 31) {
      // ctrl-?
      read_line_print_usage();
      line_buffer[0]=0;
      break;
    } else if (ch == 4) {
	//delete key
	if (line_length == 0) {
		continue;
	}
	for (int i = afterLength - 2; i >= 0; i--) {
		s = after[i];
		write(1, &s, 1);
	}
	char space = ' ';
	write(1, &space, 1);
	backspace(afterLength);
	afterLength--;
    } else if (ch == 1) {
	int tempLength = line_length;
	for (int i = 0; i < tempLength; i++) {
		s = 8;
		write(1, &s, 1);
		after[afterLength++] = line_buffer[line_length - 1];
		line_length--;
	}
    } else if (ch == 5) {
	for (int i = afterLength - 1; i >= 0; i--) {
		write(1, "\033[1C", 5);
		line_buffer[line_length++] = after[afterLength - 1];
		afterLength--;
	}
    }
    else if (ch == 127) {
      // <backspace> was typed. Remove previous character read.
      if (line_length == 0) {
	continue;
      }
      // Go back one character
      backspace(1);
      for (int i = afterLength - 1; i >= 0; i--) {
	s = after[i];
	write(1, &s, 1);
      }

      // Write a space to erase the last character read
      ch = ' ';
      write(1,&ch,1);

      // Go back one character
      backspace(afterLength + 1);

      // Remove one character from buffer
      line_length--;
      //line_buffer[position] = '\0';
      //position--;
    }
    else if (ch==27) {
      // Escape sequence. Read two chars more
      //
      // HINT: Use the program "keyboard-example" to
      // see the ascii code for the different chars typed.
      //
      char ch1;
      char ch2;
      read(0, &ch1, 1);
      read(0, &ch2, 1);
      //char ch3;
      //read(0, &ch3, 1);
      if (ch1==91 && (ch2 == 66 || ch2 == 65)) {
	// Up or down arrow. Print next line in history.
	printf("HELLO");
	// Erase old line
	// Print backspaces
	int i = 0;
	for (i =0; i < line_length; i++) {
	  ch = 8;
	  write(1,&ch,1);
	}

	// Print spaces on top
	for (i =0; i < line_length + afterLength; i++) {
	  ch = ' ';
	  write(1,&ch,1);
	}

	// Print backspaces
	backspace(line_length + afterLength);
	afterLength = 0;
	strcpy(line_buffer, history[notHistoryIndex]);
	line_length = strlen(line_buffer);
	int peepee;
	if (full) {
		peepee = history_length;
	} else {
		peepee = historyIndex;
	}
	if (notHistoryIndex == -1) {
		notHistoryIndex = peepee - 1;
	}
	int direction;
	if (ch2 == 65) {
		direction = -1;
	} else {
		direction = 1;
	}
	notHistoryIndex = (notHistoryIndex + direction) % peepee;
	if (notHistoryIndex == -1) {
		notHistoryIndex = peepee - 1;
	}

	// Copy line from history
	//gotta do some history stuff here not what they gave us
	//get most recent command (which is at historyIndex - 1)
	//historyIndex--;
/*	if (historyIndex < 0) {
		historyIndex = history_length - 1;
	}
	if (history[historyIndex] == NULL) {
		char* temp = "";
		strcpy(line_buffer, temp);
	} else {
		strcpy(line_buffer, history[history_index]);
	}
	line_length = strlen(line_buffer);
//	history_index=(history_index+1)%history_length;

*/	// echo line
	write(1, line_buffer, line_length);
      } else if (ch1 == 91 && ch2 == 68) {
	if (line_length == 0) {
		continue;
	}
	backspace(1);
	after[afterLength] = line_buffer[line_length - 1];
	afterLength++;
	line_length--;
	//down arrow
	//erase old line and print spaces in the meantime
	/*backspace(line_length);
	for (int i = 0; i < line_length; i++) {
		char c = ' ';
		write(1, &c, 1);
	}
	backspace(line_length);
	//fix index
	if (historyIndex > 0) {
		historyIndex++;
		if (historyIndex >= history_length) {
			historyIndex = 0;
		}
	} else {
		historyIndex = 0;
	}
	//then do the same as before at the end of up arrow
	if (history[historyIndex] == NULL) {
		char* temp = "";
		strcpy(line_buffer, temp);
	} else {
		strcpy(line_buffer, history[historyIndex]);
	}
	line_length = strlen(line_buffer);
	write(1, line_buffer, line_length);*/
      } else if (ch1 == 91 && ch2 == 67) {
	if (afterLength == 0) {
		continue;
	}
	write(1, "\033[1C", 5);
	line_buffer[line_length++] = after[afterLength - 1];
	afterLength--;
      }
    }
  }

  // Add eol and null char at the end of string
  line_buffer[line_length]=10;
  line_length++;
  line_buffer[line_length]=0;
  tcsetattr(0, TCSANOW, &original);
  return line_buffer;
}

