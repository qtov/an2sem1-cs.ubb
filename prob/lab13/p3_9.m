m = 6;
N = 1000;
p = 0.5;
k = 10;

posV = zeros(1, m);

for i = 1:N
  pos = 0;
  for j = 1:k
    r = rand();
    if (r < p)
      if (pos == m - 1)
        pos = 0;
      else
        pos++;
      endif
    else
      if (pos == 0)
        pos == m - 1;
      else
        pos--;
      endif
    endif
  endfor
  posV(pos + 1)++;
endfor

for i = 1:m
  posV(i) /= N;
endfor

max = 0;
for i = 1:m
  if (posV(i) > max)
    max = posV(i);
  endif
endfor

posV
for i = 1:m
  if (posV(i) == max)
    fprintf('%d - pos max prob\n', i - 1);
  endif
endfor
