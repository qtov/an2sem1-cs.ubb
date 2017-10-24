#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/ip.h>

typedef struct sockaddr_in sock;

int main()
{
	sock server;
	int c;

	c = socket(AF_INET, SOCK_STREAM, 0);
	//if

	memset(&server, 0, sizeof(server));
	server.sin_port = htons(1234);
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = inet_addr("127.0.0.1");

	if (connect(c, &server, sizeof(server)) < 0)
	{
		printf("qwe");
		return 1;
	}

	printf("worked!!\n");
	return 0;

}
