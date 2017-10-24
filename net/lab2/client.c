#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <stdio.h>

int main()
{
	int c;
	struct sockaddr_in server;

	c = socket(AF_INET, SOCK_STREAM, 0);

	if (c < 0)
	{
		printf("Error socket\n");
		return 1;
	}

	memset(&server, 0, sizeof(server));
	server.sin_port = htons(1235);
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = inet_addr("127.0.0.1");

	if (connect(c, (struct sockaddr *)&server, sizeof(server)) < 0)
	{
		printf("connection error\n");
		return 1;
	}

	int ip;
	recv(c, &ip, sizeof(int), 0);
	unsigned char bytes[4];
    bytes[0] = ip & 0xFF;
    bytes[1] = (ip >> 8) & 0xFF;
    bytes[2] = (ip >> 16) & 0xFF;
    bytes[3] = (ip >> 24) & 0xFF;   
    printf("%d.%d.%d.%d\n", bytes[3], bytes[2], bytes[1], bytes[0]); 
	return (0);
}
