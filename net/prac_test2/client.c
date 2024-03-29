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
  int i, k;
  
  
  c = socket(AF_INET, SOCK_DGRAM, 0);
  if (c < 0)
  {
    printf("Eroare la crearea socketului client\n");
    return 1;
  }
  
  memset(&server, 0, sizeof(server));
  server.sin_port = htons(1235);
  server.sin_family = AF_INET;
  server.sin_addr.s_addr = inet_addr("127.0.0.1");
  
  scanf("%d", &k);
  k = htons(k);
  sendto(c, &k, sizeof(k), 0, (struct sockaddr *) &server, sizeof(server));
  
  int n;
  recvfrom(c, &n, sizeof(n), MSG_WAITALL,  &server, sizeof(server));
  n = ntohs(n);
  printf("Lungimea sirului: %d\n", n);

  int *a = (int*)malloc(n * sizeof(int));
  for (i = 0; i < n; i++)
  {
    recvfrom(c, &a[i], sizeof(a[i]), MSG_WAITALL, &server, sizeof(server));
    a[i] = ntohs(a[i]);
  }

  for (i = 0; i < n; i++)
    printf("%d ", a[i]);

  printf("\n");

  close(c);
}