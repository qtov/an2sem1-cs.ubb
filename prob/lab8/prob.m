function r = prob(x, y, n)
  sum = 0;
  caz = 0;
  rez = [];
  for i = 1:n
    p = rand();
    for j = 1:length(y)
      if (p <= probadd(y, j))
        caz = x(j);
        break
      endif
    endfor
    rez = [caz, rez];
  endfor
  r = rez;