function r = probadd(y, n)
  sum = 0;
  for i = 1:n
    sum += y(i);
  endfor
  r = sum;