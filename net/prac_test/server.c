#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <stdio.h>

typedef struct sockaddr_in sock;

int main()
{
	int s;
	sock server, client;
	int l, c;

	
	s = socket(AF_INET, SOCK_STREAM, 0);
	if (s < 0)
	{
		printf("sock eror\n");
		return 1;
	}

	memset(&server, 0, sizeof(server));
	server.sin_port = htons(1234);
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = INADDR_ANY;

	if (bind(s, &server, sizeof(server)) < 0)
	{
		printf("bind\n");
		return 1;
	}

	listen(s, 5);
	
	l = sizeof(client);
	memset(&client, 0, sizeof(client));

	while (1)
	{
		c = accept(s, &client, &l);
		printf("bitch online\n");
		int x = 1;
		int sum = 0;
		while (x != 0)
		{
			recv(c, &x, sizeof(x), MSG_WAITALL);
			x = ntohs(x);
			sum += x;
		}
		printf("%d sum\n", sum);
		close(c);
	}

	return (0);
}
