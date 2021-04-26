
#include <cstdio>
#include <unistd.h>
#include <signal.h>
#include <stdlib.h>
#include <stdio.h>
#include "shell.hh"
#include <sys/types.h>
#include <sys/wait.h>
#include <limits.h>

int yyparse(void);
int yyrestart(FILE* file);
extern "C" void tty_raw_mode(void);

void Shell::prompt() {
	if (getenv("PROMPT")) {
		printf(getenv("PROMPT"));
		printf(" ");
	} else {
		printf("myshell> ");
	}
	fflush(stdout);
}

extern "C" void ctrlC(int sig) {
	if (sig == SIGINT) {
		Shell::_currentCommand.clear();
		printf("\n");
		Shell::prompt();
	}
}

extern "C" void zombie(int sig) {
	//waitpid returns pid of child process if it is available.
	//pid_t pid = waitpid(-1, 0, 0);
	//MUST USE WNOHANG
	//Passing in -1 and WNOHANG will detect zombies
	//both if and while here are able to pass the test case, so tentative change there...
	//if (waitpid(-1, 0, WNOHANG) > 0) {
	//	printf("[%d] exited", pid);
	//}
	pid_t pid = wait3(0, 0, NULL);
	while (waitpid(-1, NULL, WNOHANG) > 0) {
		//printf("\n%d exited", pid);
	}
}

int main(int argc, char* argv[]) {
	//char buf[1000];
	//Shell::path = realpath(argv[0], buf);
	//ctarl
	struct sigaction csa;
	csa.sa_handler = ctrlC;
	sigemptyset(&csa.sa_mask);
	csa.sa_flags = 0;

	if (sigaction(SIGINT, &csa, NULL)) {
		perror("sigaction");
		exit(2);
	}
	//zambie
	struct sigaction zsa;
	zsa.sa_handler = zombie;
	sigemptyset(&zsa.sa_mask);
	zsa.sa_flags = 0;

	if (sigaction(SIGCHLD, &zsa, NULL)) {
		perror("sigaction");
		exit(2);
	}
	FILE* file = fopen(".shellrc", "r");
	if (file) {
		yyrestart(file);
		yyparse();
		yyrestart(stdin);
		fclose(file);
	} else {
		if (isatty(0)) {
//			tty_raw_mode();
 			Shell::prompt();
		}
	}
  yyparse();
}

Command Shell::_currentCommand;
int Shell::code;
int Shell::lastPID;
std::string Shell::last;
std::string Shell::path;
