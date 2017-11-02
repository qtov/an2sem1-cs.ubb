#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <string.h>
 
int main() {
	int c;
	struct sockaddr_in server;
	int a, b, suma, i;

	c = socket(AF_INET, SOCK_DGRAM, 0);
	if (c < 0) {
		printf("Eroare la crearea socketului client\n");
		return 1;
	}

	memset(&server, 0, sizeof(server));
	server.sin_port = htons(1234);
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = inet_addr("127.0.0.1");

	int k = 1;
	k = htons(k);

	sendto(c, &k, sizeof(k), 0, (struct sockaddr *) &server, sizeof(server));

	while (k != 0)
	{
		int recv;

		scanf("%d", &k);
		k = htons(k);
		sendto(c, &k, sizeof(k), 0, (struct sockaddr *) &server, sizeof(server));
		
		recvfrom(c, &recv, sizeof(recv), MSG_WAITALL,  &server, sizeof(server));
		recv = ntohs(recv);
		printf("%d - I GOT THIS\n", recv);
		if (recv == 0)
		{
			printf("Done, gg wp\n");
			break;
		}
		k = ntohs(k);
	}

	close(c);
}