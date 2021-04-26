
/*
 *
 * CS-252 
 * shell.l: lexical analyzer for shell
 * You have to extend it.
 *
 */

%{

#include <cstring>
#include "y.tab.hh"
#include <unistd.h>
#include "shell.hh"
////////////  Start added code ///////////

extern "C" char * read_line();


int mygetc(FILE * f) {
 static char *p;
 char ch;

 if (!isatty(0)) {
 // stdin is not a tty. Call real getc
   return getc(f);
 }

 // stdin is a tty. Call our read_line.
 if (p==NULL || *p == 0) {
   char * s = read_line();
   p = s;
 }

 ch = *p;
 p++;

 return ch;
}

#undef getc
#define getc(f) mygetc(f)

/////////// End added code ///////////

static  void yyunput (int c,char *buf_ptr  );

void myunputc(int c) {
  unput(c);
}

%}

%option noyywrap

%%

\n {
  return NEWLINE;
}

[ \t] {
  /* Discard spaces and tabs */
}

">" {
  return GREAT;
}

"<" {
  return LESS;
}

"|" {
  return PIPE;
}

">>" {
  return GREATGREAT;
}

"&" {
  return AMPERSAND;
}

">>&" {
  return GREATGREATAMPERSAND;
}

">&" {
  return GREATAMPERSAND;
}

"2>" {
  return TWOGREAT;
}

[^ \t\n]*\$\{[^ \}\t\n][^ \}\t\n]*\}[^ \t\n]* {
	std::string current = std::string(yytext);
	std::string result = "";
	std::string need;
	std::string variable;
	int first = 0;
	int second = 0;
	//npos means until the end of the string
	while ((first = current.find("${", second)) != std::string::npos) {
		result += current.substr(second, first - second);
		second = current.find("}", first);
		need = current.substr(first + 2, second - first - 2);
		if (strcmp(need.c_str(), "$") == 0) {
			variable = std::to_string(getpid());
		} else if (strcmp(need.c_str(), "?") == 0) {
			variable = std::to_string(Shell::code);
		} else if (strcmp(need.c_str(), "!") == 0) {
			variable = std::to_string(Shell::lastPID);
		} else if (strcmp(need.c_str(), "_") == 0) {
			variable = Shell::last;
		} else if (strcmp(need.c_str(), "SHELL") == 0) {
			//this one makes me seg fault, not sure why
			variable = Shell::path;
		} else if (getenv(need.c_str())) {
			variable = getenv(need.c_str());
		} else {
			variable = need;
		}
		result += variable;
		second++;
	}
	result += current.substr(second);
	yylval.cpp_string = new std::string(result);
	return WORD;
}

\$\([^\n\$]*\) {
	int in;
	int out;
	int process;
	int i = 0;
	char c;
	char* text;
	int pipeIn[2];
	int pipeOut[2];
	//c initially didn't work so tried c++
	//turns out c did work and the error was somewhere else so went back
	//and changed implementation to c again
//	std::string* text = new std::string(yytext + 2);
//	text->pop_back();
		
//	if (text[0] == '$') {
		text = yytext + 2;
		text[yyleng - 3] = '\0';
//	} else {
//		text = text;
//	}
	//if (text[yyleng - 2] == '') {
	//}
	//set defaults and pipe
	in = dup(0);
	out = dup(1);


	pipe(pipeIn);
	pipe(pipeOut);

	//write text to file
	write(pipeIn[1], text, strlen(text));
	write(pipeIn[1], "\n", 1);
	write(pipeIn[1], "exit", 4);
	write(pipeIn[1], "\n", 1);
	close (pipeIn[1]);

	dup2(pipeIn[0], 0);
	dup2(pipeOut[1], 1);
	close(pipeIn[0]);
	close(pipeOut[1]);

	//silverware
	process = fork();
	if (process < 0) {
		perror("fork");
		exit(1);
	}

	if (process == 0) {
		execvp("/proc/self/exe", NULL);
		_exit(1);
	} 
	dup2(in, 0);
	dup2(out, 1);
	close(in);
	close(out);
	
	//read output of subshell and store it in buffer
	char* buffer = new char[6969];
	while (read(pipeOut[0], &c, 1)) {
		if (c == '\n') {
			buffer[i] = ' ';
			//i++;
			//continue;
		} else {
			buffer[i] = c;
		}
		i++;
	}	
	buffer[i] = '\0';
	//use unput for parent to put buffer back into lex
	for (i = strlen(buffer) - 1; i >= 0; i--) {
		myunputc(buffer[i]);
	}
	close(pipeOut[0]);
}

["][^\n\"]*["] {
	//quotes
	char* text = yytext + 1;
	if (text[yyleng - 2] == '\"') {
		text[yyleng - 2] = '\0';
	}
	yylval.cpp_string = new std::string(text);
	return WORD;
}

[^ \t\n|><&]*\\[^ \t\n]* {
	//escape character
	char* text = yytext;
	char* result = new char[1000];
	int i = 0;
	while (*text) {
		if (*text == '\\') {
			*text++;
			result[i] = *text;
		}
		result[i] = *text;
		*text++;
		i++;
	}
	result[i] = '\0';
	yylval.cpp_string = new std::string(result);
	return WORD;
}

[^ \t\n|><][^ \t\n|><]*  {
  /* Assume that file names have only alpha chars */
  yylval.cpp_string = new std::string(yytext);
  return WORD;
}

