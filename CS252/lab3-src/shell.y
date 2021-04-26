
/*
 * CS-252
 * shell.y: parser for shell
 *
 * This parser compiles the following grammar:
 *
 *	cmd [arg]* [> filename]
 *
 * you must extend it to understand the complete shell grammar
 *
 */

%code requires 
{
#include <string>
#include <string.h>
#if __cplusplus > 199711L
#define register      // Deprecated in C++11 so remove the keyword
#endif
}

%union
{
  char        *string_val;
  // Example of using a c++ type in yacc
  std::string *cpp_string;
}

%token <cpp_string> WORD
%token NOTOKEN GREAT NEWLINE LESS GREATGREAT PIPE AMPERSAND GREATAMPERSAND GREATGREATAMPERSAND TWOGREAT

%{
//#define yylex yylex
#include <cstdio>
#include <regex.h>
#include <dirent.h>
#include <sys/types.h>
#include <assert.h>
#include <vector>
#include <algorithm>
#include "shell.hh"

//void expandWildCardsIfNecessary(const char* arg);
void expand(char* pre, char* arg);
int compare(char* first, char* second);
void yyerror(const char * s);
int yylex();

static std::vector<char*> sorted = std::vector<char*>();

%}


%% 
goal: command_list;

command_list:
	command_line |
	command_list command_line
	; /*command loop*/


command_line:
	pipe_list io_modifier_list background_optional NEWLINE {
		Shell::_currentCommand.execute();
	}
	| NEWLINE {
		Shell::prompt();
	}
	| error NEWLINE {yyerrok;}
	;		/*error recovery*/

pipe_list:
	pipe_list PIPE cmd_and_args
	| cmd_and_args
	;

cmd_and_args:
	cmd_word arg_list {
		Shell::_currentCommand.insertSimpleCommand(Command::_currentSimpleCommand);
	}
	;

cmd_word:
	WORD {
		Command::_currentSimpleCommand = new SimpleCommand();
		Command::_currentSimpleCommand->insertArgument($1);
	}
	;
	
arg_list:
	arg_list arg
	|
	;
arg:
	WORD {
		char* firstPre = strdup("");
		expand(firstPre, (char*) $1->c_str());
		std::sort(sorted.begin(), sorted.end(), compare);
		if (sorted.size() == 0) {
			Command::_currentSimpleCommand->insertArgument($1);
		}
		for (auto arg: sorted) {
			std::string* newArg  = new std::string(arg);
			Command::_currentSimpleCommand->insertArgument(newArg);
			free(arg);
		}
		sorted.clear(); 
//		if (strcmp(Command::_currentSimpleCommand->_arguments[0]->c_str(), "echo") == 0 && strchr($1->c_str(), '?')) {
//			Command::_currentSimpleCommand->insertArgument($1);
//		} else {
//			expandWildCardsIfNecessary($1->c_str());
//		}		
	}
	;

io_modifier:
  GREAT WORD {
//    printf("   Yacc: insert output \"%s\"\n", $2->c_str());
    Shell::_currentCommand._outFile = $2;
    Shell::_currentCommand._counter++;
  } 
  | LESS WORD {
    Shell::_currentCommand._inFile = $2;
    Shell::_currentCommand._counter++;
  }
  | GREATGREAT WORD {
    Shell::_currentCommand._outFile = $2;
    Shell::_currentCommand._counter++;
    Shell::_currentCommand._append = 1;
  }
  | GREATAMPERSAND WORD {
    Shell::_currentCommand._outFile = $2;
    Shell::_currentCommand._counter++;
    Shell::_currentCommand._errFile = $2;
  }
  | GREATGREATAMPERSAND WORD {
    Shell::_currentCommand._outFile = $2;
    Shell::_currentCommand._errFile = $2;
    Shell::_currentCommand._counter++;
    Shell::_currentCommand._append = 1;
  }
  | TWOGREAT WORD {
    Shell::_currentCommand._errFile = $2;
    Shell::_currentCommand._counter++;
  }
  ;

io_modifier_list:
	io_modifier_list io_modifier
		|
		;

background_optional:
	AMPERSAND {
		Shell::_currentCommand._background = true;
	}
	| /* can be empty */
	;

%%

//char** array;
//int entries = 0;
//int maxEntries = 20;
 
int compare(char* first, char* second) {
	return strcmp(first, second) < 0;
}
/* 
void expandWildCardsIfNecessary(const char* arg) {
	int size = strlen(arg);
	std::string argument(arg);
	entries = 0;
	maxEntries = 20;
	array = (char**) malloc(maxEntries * sizeof(char*));
	if (!strchr(arg, '*') && !strchr(arg, '?')) {
		expand(NULL, arg);
		qsort(array, entries, sizeof(char*), compare);
		//this doesn't work for some reason, arg isn't right type
		//Command::_currentSimpleCommand->insertArgument(&argument);
		for (int i = 0; i < entries; i++) {
			//int length = strlen(array[i]);
			std::string newStuff(array[i]);
			Command::_currentSimpleCommand->insertArgument(&newStuff);
		}
	} else {
		Command::_currentSimpleCommand->insertArgument(&argument);
	}
	return;
}
*/

void expand(char* pre, char* arg) {
	//redoing this cuz fuck it
	//cant get the other way to work
	char part[1024];
	char newerPre[1024];
	char* newArg;
	char* directory;
	
	//if arg is null only push back pre
	if (arg[0] == '\0') {
		if (strchr(pre, '*') == NULL && strchr(pre, '?') == NULL) {
			sorted.push_back(strdup(pre));
		}
		//sorted.push_back(pre);
		return;
	}
	
	char newPre[1024];	
	if (pre[0] == '\0') {
		if (arg[0] == '/') {
			sprintf(newPre, "/");
			arg += 1;
		} else {
			strcpy(newPre, pre);
		}
	} else {
		sprintf(newPre, "%s/", pre);
	}

	newArg = strchr(arg, '/');
	if (newArg == NULL) {
		strcpy(part, arg);
		arg = arg + strlen(arg);
	} else {
		strncpy(part, arg, newArg - arg);
		part[newArg - arg] = 0;
		arg = newArg + 1;
	}
	if (strchr(part, '*') == NULL && strchr(part, '?') == NULL) {
		if (newPre[0] == '\0') {
			strcpy(newerPre, part);
		} else {
			sprintf(newerPre, "%s%s", newPre, part);
		}
		expand(newerPre, arg);
		return;
	}
	char* reg = (char*) malloc(2 * strlen(part) + 10);
	char* r = reg;
	*r = '^'; r++;
	int a = 0;
	while (part[a]) {
		if (part[a] == '*') { *r = '.'; r++; *r = '*'; r++;}
		else if (part[a] == '?') { *r = '.'; r++;}
		else if (part[a] == '.') { *r = '\\'; r++; *r = '.'; r++; }
		else { *r = part[a]; r++;}
		a++;
	}
	*r = '$'; r++; *r = '\0';
	regex_t re;
	int expbuf = regcomp(&re, reg, REG_EXTENDED|REG_NOSUB);
	if (expbuf != 0) {
		perror("compile");
		return;
	}
	
	if (newPre[0] == '\0') {
		directory = (char*) ".";
	} else if (strlen(newPre) > 1) {
		directory = pre;
	} else {
		directory = (char*) "/";
	}

	DIR* dir;
	if (opendir(directory) != NULL) {
		dir = opendir(directory);
		//perror("opendir");
		//return;
	} else {
		return;
	}
	bool s = false;
	struct dirent* ent;
	while ((ent = readdir(dir)) != NULL) {
		if (regexec(&re, ent->d_name, 1, NULL, 0) == 0) {
			s = true;
			if (newPre[0] == '\0') {
				strcpy(newerPre, ent->d_name);
			} else {
				sprintf(newerPre, "%s/%s", pre, ent->d_name); 
			}
			if (reg[1] != '.') {
				expand(newerPre, arg);
			} else {
				if (ent->d_name[0] != '.') {
					expand(newerPre, arg);
				}	
			}
			//s = 1;
		}
	}
	if (!s) {
		if (newPre[0] == '\0') {
			strcpy(newerPre, part);
		} else {
			sprintf(newerPre, "%s/%s", pre, part);
		}
		expand(newerPre, arg);
	}
	closedir(dir);
	regfree(&re);
	free(reg); 

/*	char* temp = strdup(arg);
	int size = strlen(arg);
	std::string argument(arg);
	char* tempDir = (char*) malloc(size + 10);
	char* directory = tempDir;
	regmatch_t regmatch;
	if (temp[0] == '/') {
		*tempDir = *temp;
		tempDir++;
		temp++;
	} 
	while (*temp != 0 && *temp != '/') {
		*tempDir = *temp;
		tempDir++;
		temp++;
	}
	*tempDir = '\0';
	if (strchr(directory, '*') || strchr(directory, '?')) {
		if (arg[0] == '/' && pre == 0) {
			directory++;
			pre = strdup("/");
		}

		const char* a = arg;
		//if (expbuf == NULL) {
		//	perror("compile");
		//	return;
		//}
		if (pre == 0) {
			pre = strdup(".");
		}
		char* newDir = pre;
				if (*temp == 0) {
					if (entries == maxEntries) {
						maxEntries *= 2;
						array = (char**) realloc(array, maxEntries * sizeof(char*));
						//assert(array != NULL);
					}
					char* empty = (char*) malloc(200);
					empty[0] = '\0';
					if (pre != 0) {
						sprintf(empty, "%s/%s", pre, ent->d_name);
					}
					if (ent->d_name[0] == '.') {
						if (arg[0] == '.') {
							if (empty[0] != '\0') {
								array[entries] = strdup(empty);
							} else {
								array[entries] = strdup(ent->d_name);
							}
							entries++;
						}
					} else {
						if (empty[0] != '\0') {
							array[entries] = strdup(empty);
						} else {
							array[entries] = strdup(ent->d_name);
						}
						entries++;
					}	
				} else {
					if (ent->d_type == DT_DIR) {
						char* newPre;
						if (!strcmp(newDir, ".")) {
							newPre = ent->d_name;
						} else if (!strcmp(newDir, "/")) {
							sprintf(newPre, "%s%s", newDir, ent->d_name);
						} else {
							sprintf(newPre, "%s/%s", newDir, ent->d_name); 
						}
						if (*temp == '/') {
							temp++;
							expand(newPre, temp); 
						} else {
							expand(newPre, temp);
						}
					}
				}
			}	
		} 
		closedir(dir);
		//qsort(array, entries, sizeof(char*), compare);
		//free(array);
	} else {
		char* newerPre = (char*) malloc(128);
		if (pre == 0) {
			newerPre = directory;
		} else {
			sprintf(newerPre, "%s/%s", pre, directory); 
			
		}
		if (*temp != 0) {
			temp++;
			expand(newerPre, temp);
		}
	}*/
}
 
void
yyerror(const char * s)
{
  fprintf(stderr,"%s", s);
}

#if 0
main()
{
  yyparse();
}
#endif
