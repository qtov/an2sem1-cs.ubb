#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/ip.h>

typedef struct sockaddr_in sock;

int main()
{
	int c;
	sock server;

	c = socket(AF_INET, SOCK_STREAM, 0);
	if (c < 0)
	{
		printf("Fucking shit socket crap");
		return 1;
	}

	memset(&server, 0, sizeof(server));
	server.sin_port = htons(1234);
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = inet_addr("127.0.0.1");

	if (connect(c, &server, sizeof(server)) < 0)
	{
		printf("asda");
		return 1;
	}
	
	int x = 1;
	while (x != 0)
	{
		scanf("%d", &x);
		x = htons(x);
		send(c, &x, sizeof(x), 0);
	}

	int suma;
	recv(c, &suma, sizeof(suma), 0);
	suma = ntohs(suma);
	printf("%d -- fuka\n", suma);

	close(c);
	return (0);
}
