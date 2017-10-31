#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <string.h>
 
int main() {
  int s;
  struct sockaddr_in server, client;
  int c, l, i;
  int k, old = 0;
  int a[];
  
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
	recvfrom(s, &k, sizeof(k), MSG_WAITALL, (struct sockaddr *) &client, &l);
	k = ntohs(k);
	int j = 0;
	a = (int)malloc(sizoof(int));
	for (i = 1; i < k / 2; i++)
	{
		if (k % i == 0)
			a[j++] = i;
		a = (int)realloc(sizeof(int) * (j + 1));
	}

	for (i = 0; i < j; i++)
		printf("%d ", a[i]);
  
  close(s);
}