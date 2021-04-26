#ifndef shell_hh
#define shell_hh

#include "command.hh"

struct Shell {

  static void prompt();

  static Command _currentCommand;

  static int code;
  static int lastPID;
  static std::string last;
  static std::string path;
};

#endif
