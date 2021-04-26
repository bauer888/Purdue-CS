#ifndef command_hh
#define command_hh

#include "simpleCommand.hh"

// Command Data Structure

struct Command {
  std::vector<SimpleCommand *> _simpleCommands;
  int _lastBack;
  std::string * _outFile;
  std::string * _inFile;
  std::string * _errFile;
  bool _background;
  bool _append;
  int _counter;

  Command();
  void insertSimpleCommand( SimpleCommand * simpleCommand );

  void clear();
  void print();
  void environment(std::string* argument);
  //void specialStuff(std::string* argument);
  void expansion();
  void tillday(std::string* argument);
  void execute();

  static SimpleCommand *_currentSimpleCommand;
};

#endif
