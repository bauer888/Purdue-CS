CXX = g++ -fPIC -g -pthread
NETLIBS= -lnsl

all: daytime-server daytime-client use-dlopen hello.so myhttpd git-commit

daytime-client : daytime-client.o
	$(CXX) -o $@ $@.o $(NETLIBS)

daytime-server : daytime-server.o
	$(CXX) -o $@ $@.o $(NETLIBS)

myhttpd : myhttpd.o
	$(CXX) -o $@ $@.o $(NETLIBS)

use-dlopen: use-dlopen.o
	$(CXX) -o $@ $@.o $(NETLIBS) -ldl

hello.so: hello.o
	ld -G -o hello.so hello.o

%.o: %.cc
	@echo 'Building $@ from $<'
	$(CXX) -o $@ -c -I. $<

git-commit:
	git checkout master >> .local.git.out || echo
	git add *.cc *.hh *.l *.y Makefile >> .local.git.out  || echo
	git commit -a -m  "Web Server" >> .local.git.out || echo
	git push origin master

clean:
	rm -f *.o use-dlopen hello.so
	rm -f *.o daytime-server daytime-client myhttpd

