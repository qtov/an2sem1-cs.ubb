p = 0.5;
pos = 0;

k = 6
n = 15
posV = zeros(1, k * 2 + 1);
posP = zeros(1, k * 2 + 1);
for i = 1:n
  pos = 0;
  for j = 1:k
    r = rand();
    if (r < p)
      pos -= 1;
    else
      pos += 1;
    endif
  endfor
  posV(pos + k + 1)++;
endfor

for i = 1:k*2+1
  posP(i) -= k - i + 1;
endfor
display(posP)
display(posV)

max = 0;
for i = 1:k*2+1
  if (posV(i) > max)
    max = posV(i);
  endif
endfor

fprintf('%f -- probmax\n', max / n);

for i = 1:k*2+1
  if (posV(i) == max)
    fprintf('%d - pos cu max\n', posP(i));
  endif
endfor

pos = 0;
pasi = 0;
while true
  r = rand();
  if (r < p)
    pos -= 1;
  else
    pos += 1;
  endif
  pasi++;
  if (pos == 0)
    fprintf('%d -- simulare pasi\n', pasi);
    break;
  endif
endwhile

k = 6;
pos = 0;
success = 0;
n = 10000;
for i = 1:n
  for j = 1:k
    r = rand();
    if (r < p)
      pos -= 1;
    else
      pos += 1;
    endif
  endfor
  if (pos == 0)
    success += 1;
  endif
endfor

fprintf('%f -- prob 6 pasi origine\n', success / n);

