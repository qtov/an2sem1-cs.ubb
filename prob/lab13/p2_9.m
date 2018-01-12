m = 100;
N = 100;
p = 0.5;
win = 0;
loss = 0;

for i = 1:N
  n = 50;
  while (n != 0 && n != m)
    r = rand();
    if (rand > p)
      n++;
    else
      n--;
    endif
  endwhile
  if (n == 0)
    loss++;
  else
    win++;
  endif
endfor

fprintf('%f -- prob win\n', win / N);
fprintf('%f -- prob loss\n', loss / N);