#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <arpa/inet.h>
 
int main() {
  int s;
  struct sockaddr_in server, client;
  int k, t, l;
  
  s = socket(AF_INET, SOCK_DGRAM, 0);
  if (s < 0) {
    printf("Eroare la crearea socketului server\n");
    return 1;
  }
  
  memset(&server, 0, sizeof(server));
  server.sin_port = htons(1235);
  server.sin_family = AF_INET;
  server.sin_addr.s_addr = INADDR_ANY;
  
  if (bind(s, (struct sockaddr *) &server, sizeof(server)) < 0) {
    printf("Eroare la bind\n");
    return 1;
  }
 
  l = sizeof(client);
  memset(&client, 0, sizeof(client));

	recvfrom(s, &k, sizeof(k), MSG_WAITALL, (struct sockaddr *) &client, &l);
  recvfrom(s, &t, sizeof(t), MSG_WAITALL, (struct sockaddr *) &client, &l);
	k = ntohs(k);
  t = ntohs(t);
  int suma = k + t;

	sendto(s, &suma, sizeof(suma), 0, (struct sockaddr *)&client, sizeof(client));

  close(s);
}
