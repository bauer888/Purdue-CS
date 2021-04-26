/*
 * CS252: Shell project
 *
 * Template file.
 * You will need to add more code here to execute the command table.
 *
 * NOTE: You are responsible for fixing any bugs this code may have!
 *
 * DO NOT PUT THIS PROJECT IN A PUBLIC REPOSITORY LIKE GIT. IF YOU WANT 
 * TO MAKE IT PUBLICALLY AVAILABLE YOU NEED TO REMOVE ANY SKELETON CODE 
 * AND REWRITE YOUR PROJECT SO IT IMPLEMENTS FUNCTIONALITY DIFFERENT THAN
 * WHAT IS SPECIFIED IN THE HANDOUT. WE OFTEN REUSE PART OF THE PROJECTS FROM  
 * SEMESTER TO SEMESTER AND PUTTING YOUR CODE IN A PUBLIC REPOSITORY
 * MAY FACILITATE ACADEMIC DISHONESTY.
 * Hello my name is Jack
 */

#include <cstdio>
#include <cstdlib>

#include <iostream>
#include <functional>
#include "command.hh"
#include "shell.hh"
#include <vector>
#include <iterator>
#include <algorithm>
#include <fstream>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <pwd.h>
#include <regex.h>

Command::Command() {
    // Initialize a new vector of Simple Commands
    _simpleCommands = std::vector<SimpleCommand *>();
    _lastBack = 0;
    _outFile = NULL;
    _inFile = NULL;
    _errFile = NULL;
    _background = false;
    _append = false;
    _counter = 0;
}
/*
int match(const char* arg) {
	const char* regex = "\\${.*}";
	regex_t preg;
	regmatch_t match;
	if (regcomp(&preg, regex, 0) != 0) {
		perror("regex");
		exit(1);
	}
	if (!regexec(&preg, arg, 1, &match, 0)) {
		return 1;
	}
	return 0;
}*/

void Command::insertSimpleCommand( SimpleCommand * simpleCommand ) {
/*	std::string result;
	//not sure if c++ string will work here
	for (int i = 0; i < (int) simpleCommand->_arguments.size(); i++) {
		if (match(simpleCommand->_arguments[i]->c_str()) == 1) {
			char* arg = (char*) simpleCommand->_arguments[i]->c_str();
			char* need = arg + 2;
			int size = strlen(need);
			need[size - 1] = '\0';
			if (strcmp(need, "$") == 0) {
				sprintf((char*)result.c_str(), "%d", getpid());
			} else if (strcmp(need, "?")) {

			} else if (strcmp(need, "!")) {
			//	if (_lastBack == 0) {
			//		result = "";
			//	} else {
			//		//saved pid at the end of execute() below
			//		sprintf((char*) result.c_str(), "%d", _lastBack);
			//	}
			} else if (strcmp(need, "_")) {

			} else if (strcmp(need, "SHELL")) {

			} else {
				result = getenv(need);
			}
			simpleCommand->_arguments.push_back(&result);
		}
	}
*/	// add the simple command to the vector
	_simpleCommands.push_back(simpleCommand);
}

void Command::clear() {
    // deallocate all the simple commands in the command vector
    for (auto simpleCommand : _simpleCommands) {
        delete simpleCommand;
    }

    // remove all references to the simple commands we've deallocated
    // (basically just sets the size to 0)
    _simpleCommands.clear();

    if ( _outFile ) {
        delete _outFile;
    }
    _outFile = NULL;

    if ( _inFile ) {
        delete _inFile;
    }
    _inFile = NULL;

    if ( _errFile ) {
        delete _errFile;
    }
    _errFile = NULL;

    _background = false;
    _append = false;
    _counter = 0;
}

void Command::print() {
    printf("\n\n");
    printf("              COMMAND TABLE                \n");
    printf("\n");
    printf("  #   Simple Commands\n");
    printf("  --- ----------------------------------------------------------\n");

    int i = 0;
    // iterate over the simple commands and print them nicely
    for ( auto & simpleCommand : _simpleCommands ) {
        printf("  %-3d ", i++ );
        simpleCommand->print();
    }

    printf( "\n\n" );
    printf( "  Output       Input        Error        Background\n" );
    printf( "  ------------ ------------ ------------ ------------\n" );
    printf( "  %-12s %-12s %-12s %-12s\n",
            _outFile?_outFile->c_str():"default",
            _inFile?_inFile->c_str():"default",
            _errFile?_errFile->c_str():"default",
            _background?"YES":"NO");
    printf( "\n\n" );
}

//lets learn some c++ cuz why not seems cool
//not quite sure how to do the special ones yet, come back to those
void Command::environment(std::string* argument) {
	while (1) {
		auto first = std::find(argument->begin(), argument->end(), '$');
		auto last = std::find(first, argument->end(), '}');
		auto check = argument->end();
		if (first == check || last == check) {
			break;
		}
		auto env = getenv(std::string(first + 2, last).c_str());
		std::string s;
		if (env) {
			s = env;
		} else {
			s = "";
		}
		argument->replace(first, last + 1, s);
	}
}
/*
void Command::specialStuff(std::string* argument) {
	while (1) {
		auto first = std::find(argument->begin(), argument->end(), '$');
		auto last = std::find(first, argument->end(), '}');
		auto check = argument->end();
		if (first == check || last == check) {
			break;
		}
		auto special = std::string(first + 2, last).c_str();
		if (strcmp(special, "$") == 0) {
			fprintf(stdout, "%d\n", getpid());
		}
	}
}*/

void Command::tillday(std::string* argument) {
	while (1) {
		auto till = std::find(argument->begin(), argument->end(), '~');
		if (till == argument->end()) {
			break;
		}
		//can be by itself or in front of '/'
		if (strlen(argument->c_str()) == 1 || argument->c_str()[1] == '/') {
			struct passwd* user = getpwnam(getenv("USER"));
			if (user) {
				argument->replace(till, till + 1, user->pw_dir);
			} else {
				break;
			}
		} else {
			auto day = std::find(till, argument->end(), '/');
			std::string name = std::string(till + 1, day);
			struct passwd* user = getpwnam(name.c_str());
			if (user) {
				argument->replace(till, day, user->pw_dir);
			} else {
				break;
			}
		}
	}
}

void Command::expansion() {
	for (auto command: _simpleCommands) {
		for (auto argument: command->_arguments) {
			if (argument) {
				//environment(argument);
				//specialStuff(argument);
				tillday(argument);
			}
		}
	}
}

void Command::execute() {
    // Don't do anything if there are no simple commands
    if ( _simpleCommands.size() == 0 ) {
        Shell::prompt();
        return;
    }
    if (_counter > 1) {
	printf("Ambiguous output redirect.\n");
	clear();
	Shell::prompt();
	return;
    }

	expansion();


    // Print contents of Command data structure
    //print();

    // Add execution here
    // For every simple command fork a new process
    // Setup i/o redirection
    // and call exec
		if (strcmp(_simpleCommands[0]->_arguments[0]->c_str(), "exit") == 0) {
			printf("Bye!\n");
			_exit(1);
		}


	//set default file descriptors
	int defaultIn = dup(0);
	int defaultOut = dup(1);
	int defaultErr = dup(2);
	//"final" file descriptors
	int fIn;
	int fErr;
	int fOut;
	//process for fork
	int process;
	//if there is anything in the string, evaluates to TRUE
	//FALSE otherwise
	if (_inFile) {
		fIn = open(_inFile->c_str(), O_RDONLY, 0765);
	} else {
		fIn = dup(defaultIn);
	}
	//dup2(fIn, 0);
	//close(fIn);
	//Same condition as inFile above
	if (_errFile) {
		if (_append) {
			fErr = open(_errFile->c_str(), O_WRONLY | O_CREAT | O_APPEND, 0765);
		} else {
			fErr = open(_errFile->c_str(), O_WRONLY | O_CREAT | O_TRUNC, 0765);
		}
	} else {
		fErr = dup(defaultErr);
	}
	dup2(fErr, 2);
	close(fErr);
	//iterate through all arguments
	for (size_t i = 0; i < _simpleCommands.size(); i++) {
		//This will exit the shell no matter where exit is typed in the list of arguments
		/*for (int j = 0; j < (int)_simpleCommands[i]->_arguments.size(); j++) {
			if (strcmp(_simpleCommands[i]->_arguments[j]->c_str(), "exit") == 0) {
				printf("Bye!\n");
				exit(1);
			}
		}*/

		//setenv
		if (strcmp(_simpleCommands[i]->_arguments[0]->c_str(), "setenv") == 0) {
			setenv(_simpleCommands[i]->_arguments[1]->c_str(), _simpleCommands[i]->_arguments[2]->c_str(), 1);
			clear();
			Shell::prompt();
			return;
		}
		//unsetenv
		if (strcmp(_simpleCommands[i]->_arguments[0]->c_str(), "unsetenv") == 0) {
			unsetenv(_simpleCommands[i]->_arguments[1]->c_str());
			clear();
			Shell::prompt();
			return;
		}
		//source
		if (strcmp(_simpleCommands[i]->_arguments[0]->c_str(), "source") == 0) {
			int in;
			int out;
			int pipeIn[2];
			int pipeOut[2];
			int process;
			char c;
			int j = 0;
			//c++?
			//shell hangs when i use c, try c++
			//char* text = new char[6969];
			//FILE* f = fopen(_simpleCommands[i]->_arguments[1]->c_str(), "r");
			std::string text;
			std::ifstream stream;
			stream.open(_simpleCommands[i]->_arguments[1]->c_str());
			getline(stream, text);
			//fgets(text, strlen(text), f);

			in = dup(0);
			out = dup(1);

			//write to 
			pipe(pipeIn);
			write(pipeIn[1], text.c_str(), text.size());
			write(pipeIn[1], "\n", 1);
			close(pipeIn[1]);

			pipe(pipeOut);
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

			//reset defaults
			dup2(in, 0);
			dup2(out, 1);
			close(in);
			close(out);

			//same idea as in subshell
			//(did subshell first cuz its worth more)
			char* buffer = new char[6969];
			while (read(pipeOut[0], &c, 1)) {
				if (c != '\n') {
					buffer[j] = c;
				}
				j++;
			}
			buffer[j] = '\0';
			printf("%s\n", buffer);

			clear();
			Shell::prompt();
			return;
		}
		//cd
		if (strcmp(_simpleCommands[i]->_arguments[0]->c_str(), "cd") == 0) {
			int size = _simpleCommands[i]->_arguments.size();
			int temp;
			if (size > 1) {
				temp = chdir(_simpleCommands[i]->_arguments[1]->c_str());
			} else {
				temp = chdir(getenv("HOME"));
				//perror("cd");
			}
			if (temp != 0) {
				char result[1000] = "cd: can't cd to ";
				const char* file = _simpleCommands[i]->_arguments[1]->c_str();
				strcat(result, file);
				perror(result);
			}
			clear();
			Shell::prompt();
			return;
		}
		dup2(fIn, 0);
		close(fIn);
		if (i == _simpleCommands.size() - 1) {
			//Again, same condition as above
			if (_outFile) {
				if (_append) {
					fOut = open(_outFile->c_str(), O_WRONLY | O_CREAT | O_APPEND, 0765);
				} else {
					fOut = open(_outFile->c_str(), O_WRONLY | O_CREAT | O_TRUNC, 0765);
				}
			} else {
				fOut = dup(defaultOut);
			}
			Shell::last = *(_simpleCommands[i]->_arguments[_simpleCommands[i]->_arguments.size() - 1]);
		} else {
			int newPipe[2];
			pipe(newPipe);
			fIn = newPipe[0];
			fOut = newPipe[1];
			//close(newPipe[1]);
			//close(newPipe[0]);
		}
		dup2(fOut, 1);
		close(fOut);
		process = fork();
		if (process < 0) {
			perror("fork");
			return;
		}
		//use vector?
		//need to change simpleCommands[i]->arguments to a char* const* so we can call execvp;
		//std::vector<char*> pointerVector(_simpleCommands[i]->_arguments.size() + 1);
		char** result = new char*[_simpleCommands[i]->_arguments.size() + 1];
		for (size_t j = 0; j < _simpleCommands[i]->_arguments.size(); j++) {
			//for some reason it gives an error w/o strdup, so use it.
			result[j] = strdup(_simpleCommands[i]->_arguments[j]->c_str());
			//pointerVector[j] = _simpleCommands[i]->_arguments[j]->data();
			if (j == _simpleCommands[i]->_arguments.size() - 1) {
				result[j + 1] = NULL;
			}
		}
		if (process == 0) {
			//printenv
			if (!strcmp(_simpleCommands[i]->_arguments[0]->c_str(), "printenv")) {
				char** p = environ;
				while (*p != NULL) {
					printf("%s\n", *p);
					p++;
				}
				exit(0);
			}
			execvp(_simpleCommands[i]->_arguments[0]->c_str(), result);
			perror("execvp");
			_exit(1);
		}
	}
	dup2(defaultIn, 0);
	dup2(defaultOut, 1);
	dup2(defaultErr, 2);
	close(defaultIn);
	close(defaultOut);
	close(defaultErr);
	close(fIn);
	int use;
	if (_background == 0) {
		//_lastBack = getpid();
		waitpid(process, &use, 0);
		if (WIFEXITED(use)) {
			Shell::code = WEXITSTATUS(use);
		} else {
			Shell::lastPID = process;
		}
	}
    // Clear to prepare for next command
    clear();

    // Print new prompt
	Shell::prompt();
}

SimpleCommand * Command::_currentSimpleCommand;
