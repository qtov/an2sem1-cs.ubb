#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
 
int main() {
  int c;
  struct sockaddr_in server;
  int k;
  
  c = socket(AF_INET, SOCK_DGRAM, 0);
  if (c < 0) {
    printf("Eroare la crearea socketului client\n");
    return 1;
  }
  
  memset(&server, 0, sizeof(server));
  server.sin_port = htons(1235);
  server.sin_family = AF_INET;
  server.sin_addr.s_addr = inet_addr("127.0.0.1");
  
  int t;
  scanf("%d", &k);
  scanf("%d", &t);
  k = htons(k);
  t = htons(t);
  sendto(c, &k, sizeof(k), 0, (struct sockaddr *) &server, sizeof(server));
  sendto(c, &t, sizeof(t), 0, (struct sockaddr *) &server, sizeof(server));
  
  int n;
  recvfrom(c, &n, sizeof(n), MSG_WAITALL,  &server, sizeof(server));
  
  printf("Suma: %d\n", n);

  close(c);
}