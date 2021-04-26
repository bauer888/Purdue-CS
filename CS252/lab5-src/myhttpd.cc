#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <time.h>
#include <unistd.h>
#include <ctype.h>
#include <pthread.h>
#include <signal.h>
#include <sys/wait.h>

void httpRequest(int socket);
int endsWith(char* first, const char* second);
void sendHeader(int socket, char* contentType, int fd);
void send404(int socket, char* contentType);
void send405(int socket);
void send401(int socket);
void processRequestThread(int socket);
void poolSlave(int socket);

int queueLength = 5;
int masterSocket;

pthread_mutex_t mutex;

extern "C" void signalHandler(int sig) {
	if (sig == SIGCHLD) {
		while(waitpid(-1, NULL, WNOHANG) > 0);
	}
	if (sig == SIGINT) {
	//	close(masterSocket);
		exit(EXIT_SUCCESS);;
	}
}


int main( int argc, char **argv) {

	int port;
	int fBased = 0;
	int tBased = 0;
	int pBased = 0;

	struct sigaction sa;
	sa.sa_handler = signalHandler;
	sigemptyset(&sa.sa_mask);
	sa.sa_flags = SA_RESTART;

	if (sigaction(SIGCHLD, &sa, NULL)) {
		perror("sigactoin sigchld");
		exit(-1);
	}

	if (sigaction(SIGINT, &sa, NULL)) {
		perror("sigaction sigint");
		exit(-1);
	}

	//print usage: will define later
	if (argc < 2) {
//		fprintf(stderr, "%s", usage);
		port = 6969;
	} else if (argc == 2 && (argv[1] == "-f" || argv[1] == "-t" || argv[1] == "-p")) {
		port = 6969;
	} else if (argc == 2) {
		port = atoi(argv[1]);
	} else {
		port = atoi(argv[2]);
	}

	//check port

	for (int i = 1; i < argc; i++) {
		if (strcmp(argv[i], "-f") == 0) {
			fBased = 1;
			//port = atoi(argv[2]);
		} else if (strcmp(argv[i], "-t") == 0) {
			tBased = 1;
			//port = atoi(argv[2]);
		} else if (strcmp(argv[i], "-p") == 0) {
			pBased = 1;
			//port = atoi(argv[2]);
		}
		if (argc == 2 && (fBased == 1 || tBased == 1 || pBased == 1)) {
			port = 6969;
		}
	}
	//printf("%d", port);

	//get port
	if (port < 1024 || port > 65536) {
		//printf("%d", port);
		perror("Port Invalid");
		exit(-1);
	}

	//int port = atoi(argv[1]);

	//set ip and port
	struct sockaddr_in serverIP;
	memset(&serverIP, 0, sizeof(serverIP));
	serverIP.sin_family = AF_INET;
	serverIP.sin_addr.s_addr = INADDR_ANY;
	serverIP.sin_port = htons((u_short) port);

	masterSocket = socket(PF_INET, SOCK_STREAM, 0);
	if (masterSocket < 0) {
		perror("socket");
		exit(-1);
	}

	//set socket options to reuse port. otherwise have to wait before reusing port number
	int optval = 1;
	int err = setsockopt(masterSocket, SOL_SOCKET, SO_REUSEADDR, (char*) &optval, sizeof(int));

	//bind the socket to the ip and port
	int error = bind(masterSocket, (struct sockaddr*) &serverIP, sizeof(serverIP));
	if (error) {
		perror("bind");
		exit(-1);
	}

	//put socke tin listening mode and set the
	//size of the queue of unprocessed connections
	error = listen(masterSocket, queueLength);
	if (error) {
		perror("listen");
		exit(-1);
	}

	//accept incoming connections
	//check which flag, if any, was present in arguments
	if (fBased == 1) {
		while (1) {
			struct sockaddr_in clientIP;
			int length = sizeof(clientIP);
			int slaveSocket = accept(masterSocket, (struct sockaddr*) &clientIP, (socklen_t*) &length);
			pid_t slave = fork();
			if (slave == 0) {
				httpRequest(slaveSocket);
				close(slaveSocket);
				exit(EXIT_SUCCESS);
			}

			close(slaveSocket);
		}
		//not sure where to put zombie cleanup
		//maybe here?
	} else if (tBased == 1) {
		while (1) {
			struct sockaddr_in clientIP;
			int length = sizeof(clientIP);
			int slaveSocket = accept(masterSocket, (struct sockaddr*) &clientIP, (socklen_t*) &length);
			//initialize pthread attributes
			pthread_t tid;
			pthread_attr_t attr;
			pthread_attr_init(&attr);
			pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_DETACHED);
			pthread_create(&tid, &attr, (void* (*)(void*)) processRequestThread, (void*) slaveSocket);
		}
	} else if (pBased == 1) {
		//initialize mutex
		pthread_mutex_init(&mutex, NULL);
		pthread_t tid[5];
		pthread_attr_t attr;
		pthread_attr_init(&attr);
		//create thread
		for (int i = 0; i < 5; i++) {
			pthread_create(&tid[i], &attr, (void*(*)(void*)) poolSlave, (void*) masterSocket);
		}
		pthread_join(tid[0], NULL);
	} else {
		while (1) {
			struct sockaddr_in clientIP;
			int length = sizeof(clientIP);
			int slaveSocket = accept(masterSocket, (struct sockaddr *) &clientIP, (socklen_t*) &length);

			if (slaveSocket < 0) {
				perror("accept");
				exit(-1);
			}

			//process request
			httpRequest(slaveSocket);

			//close socket
			close(slaveSocket);
		}
	}
}

void httpRequest(int socket) {

	int maxRequest = 10*1024;
	char request[maxRequest + 1];
	int length = 0;
	int n = 0;
	int goGet = 0;
	int authorize = 0;

	char newChar;

	char lastChar[3];
	lastChar[0] = 0;
	lastChar[1] = 0;
	lastChar[2] = 0;

	//getting correct request
	while (queueLength < maxRequest && (n = read(socket, &newChar, sizeof(newChar))) > 0) {

		if (lastChar[2] == '\015' && lastChar[1] == '\012' && lastChar[0] == '\015' && newChar == '\012') {
			length--;
			break;
		}
		request[length] = newChar;
		length++;
		//lastChar = newChar;
		lastChar[2] = lastChar[1];
		lastChar[1] = lastChar[0];
		lastChar[0] = newChar;
	}

	request[length] = '\0';

	char* cwd = (char*) malloc(256 * sizeof(char));
	cwd = getcwd(cwd, 256);

	//check to see if they have correct username:password
	if (strstr(request, "Authorization: Basic ZmF0bWFuOjExNjkwMA==") == NULL) {
		strcat(cwd, "/http-root-dir/htdocs/index.html");
		int fd = open(cwd, O_RDONLY);
		//sendHeader(socket, "text/html", fd);
		send401(socket);
		return;
	}

	printf("request=%s\n", request);

	//isolate docpath
	int whitespace1 = 0;
	int whitespace2 = 0;
	for (int i = 0; i < length; i++) {
		if (request[i] == ' ') {
			if (whitespace1 != 0) {
				whitespace2 = i;
				break;
			}
			whitespace1 = i;
		}
	}

	char docpath[10000];
	//reset docpath otherwise requests have weird file names
	for (int i = 0; i < 10000; i++) {
		docpath[i] = '\0';
	}
	strncpy(docpath, request + 4, whitespace2 - whitespace1 - 1);
	printf("DOCPATH: %s", docpath);
	char* path = (char*) malloc(1025 * sizeof(char));
	strcpy(path, cwd);

	//complete the file path
	if ((strcmp(docpath, "/") == 0) && (strlen(docpath) == 1)) {
		strcat(path, "/http-root-dir/htdocs/index.html");
	} else if (strncmp(docpath, "/icons", strlen("/icons")) == 0) {
		strcat(path, "/http-root-dir");
		strcat(path, docpath);
	} else if (strncmp(docpath, "/htdocs", strlen("/htdocs")) == 0) {
		strcat(path, "/http-root-dir");
		strcat(path, docpath);
	} else {
		strcat(path, "/http-root-dir/htdocs");
		strcat(path, docpath);
	}

	//check if it requests a file above /http-root-dir
	if (strlen(path) < strlen(cwd) + strlen("/http-root-dir")) {
		//send error: not right rn
		//perror("..");
		send405(socket);
		return;
	}

	char contentType[1025];
	//check ending of file path
	if (endsWith(path, ".html") || endsWith(path, ".html/")) {
		strcpy(contentType, "text/html");
	} else if (endsWith(path, ".gif") || endsWith(path, ".gif/")) {
		strcpy(contentType, "image/gif");
	} else {
		strcpy(contentType, "text/plain");
	}

	int fd;
	//see if file exists
	fd = open(path, O_RDONLY);
	if (fd < 0) {
		//send error
		send404(socket, contentType);
	} else {
		sendHeader(socket, contentType, fd);
	}
	//sendHeader(socket, contentType, fd);

	//const char* htmldocsample = "<H1> Testing Web Server cs252</H1>";

	//const char* newline = "\n";

	//write(socket, header1, strlen(header1));
	//write(socket, htmldocsample, strlen(htmldocsample));
	//close file descriptors and sockets
	close(fd);
	close(socket);
	//free all malloc calls
	free(cwd);
	free(path);
}

//function to check the end of the file path
int endsWith(char* first, const char* second) {
	int position = strlen(first) - strlen(second);
	for (int i = 0; i < strlen(second); i++) {
		char one = first[position + i];
		char two = second[i];
		if (one != two) {
			return 0;
		}
	}
	return 1;
}

//send header and write contents of file sent
void sendHeader(int socket, char* contentType, int fd) {
	const char* header =
		"HTTP/1.1 200 Document follows\r\n"
		"Server: CS252\r\n"
		"Content-type: ";

	write(socket, header, strlen(header));
	write(socket, contentType, strlen(contentType));
	write(socket, "\r\n", 2);
	write(socket, "\r\n", 2);

	char c;
	int temp;
	while ((temp = read(fd, &c, 1)) > 0) {
		//printf("%c", c);
		write(socket, &c, 1);
	}
}

//file not found error header
void send404(int socket, char* contentType) {
	char* message = "File Not Found";
	char* header =
		"HTTP/1.1 404FileNotFound\r\n"
		"Server: CS252\r\n"
		"Content-type: ";
	write(socket, header, strlen(header));
	write(socket, contentType, strlen(contentType));
	write(socket, "\r\n", 2);
	write(socket, "\r\n", 2);
	write(socket, message, strlen(message));
}

//illegal access error header
void send405(int socket) {
	char* message = "Illegal Access";
	char* header =
		"HTTP/1.1 405IllegalAccess\r\n"
		"Server: CS252\r\n\r\n";
	write(socket, header, strlen(header));
	write(socket, message, strlen(message));
}

//unathorized error header
void send401(int socket) {
	char* header =
		"HTTP/1.1 401 Unauthorized\r\n"
		"WWW-Authenticate: Basic realm=\"myhttp-cs252\"\r\n\r\n";
		//"realm=\"myhttp-cs252\"\r\n\r\n";
	write(socket, header, strlen(header));
}

//function to process requests to use as argument in pthread_create
void processRequestThread(int socket) {
	httpRequest(socket);
	close(socket);
}

//function to process requests to use as argument in pthread_create
void poolSlave(int socket) {
	while(1) {
		pthread_mutex_lock(&mutex);
		struct sockaddr_in clientIP;
		int length = sizeof(clientIP);
		int slaveSocket = accept(socket, (struct sockaddr*) &clientIP, (socklen_t*) &length);
		pthread_mutex_unlock(&mutex);
		if (slaveSocket < 0) {
			perror("accept");
			exit(-1);
		}

		httpRequest(slaveSocket);
		close(slaveSocket);
	}
}
