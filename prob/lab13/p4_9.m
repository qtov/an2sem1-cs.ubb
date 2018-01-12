k = 3;
n = 10;
N = 1000;
p = 0.5;
prob = 0;

for i = 1:N  
  n1 = n;
  n2 = n;
  while (n1 != 0 && n2 != 0)
    r = rand();
    if (r < p)
      n1--;
    else
      n2--;
    endif
  endwhile
  if (n1 == 2 || n2 == 2)
    prob++;
  endif
endfor

fprintf('%f - prob only %d tigari\n', prob / N, k);