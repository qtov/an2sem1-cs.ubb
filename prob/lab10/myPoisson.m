function y = myPoisson (lam, N)

suma=zeros(1,N);
for j=1:N

U = rand();

p=zeros(1,N);

p(1)=exp(-lam);

s=p(1);
%for i=1:N-1
i=0;
while (s<U)
    
    p(i+2)=p(i+1)*((lam)/(i+1));
    p(i+2);
    s=s+p(i+2);
    i++;
endwhile
suma(j)=i;
endfor
i;
media = mean(suma);

y=media;