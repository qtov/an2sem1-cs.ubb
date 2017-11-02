#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <string.h>

int main() {
	int s;
	struct sockaddr_in server, client, client2;
	int c, l, i;
	int k = 3, k2;

	s = socket(AF_INET, SOCK_DGRAM, 0);
	if (s < 0) {
		printf("Eroare la crearea socketului server\n");
		return 1;
	}

	memset(&server, 0, sizeof(server));
	server.sin_port = htons(1234);
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = INADDR_ANY;

	if (bind(s, (struct sockaddr *) &server, sizeof(server)) < 0) {
		printf("Eroare la bind\n");
		return 1;
	}

	l = sizeof(client);
	memset(&client, 0, sizeof(client));
	int l2 = sizeof(client2);
	memset(&client2, 0, sizeof(client2));

	recvfrom(s, &k, sizeof(k), MSG_WAITALL, (struct sockaddr *) &client, &l);
	recvfrom(s, &k, sizeof(k), MSG_WAITALL, (struct sockaddr *) &client2, &l2);

	while (1)
	{
		recvfrom(s, &k, sizeof(k), MSG_WAITALL, (struct sockaddr *) &client, &l);
		recvfrom(s, &k2, sizeof(k2), MSG_WAITALL, &client2, &l2); //need
		sendto(s, &k, sizeof(k), 0, (struct sockaddr *) &client2, sizeof(client2));

		// recvfrom(s, &k2, sizeof(k2), MSG_WAITALL, &client2, &l2);
		sendto(s, &k2, sizeof(k2), 0, (struct sockaddr *) &client, sizeof(client));
		if (ntohs(k) == 0 || ntohs(k2) == 0)
			break;
	}

	close(s);
}
