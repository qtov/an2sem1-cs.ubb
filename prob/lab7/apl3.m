winNum = genLoto();
n = 1000;

winC1 = 0;
winC2 = 0;
winC3 = 0;
winC4 = 0;

for i = 1:n
  someNum = genLoto();
  sameNum = size(intersect(someNum, winNum))(2);
  if (sameNum == 6)
    winC1 += 1;
  endif
  if (sameNum == 5)
    winC2 += 1;
  endif
  if (sameNum == 4)
    winC3 += 1;
  endif
  if (sameNum == 3)
    winC4 += 1;
  endif
endfor

fprintf("%f - Categoria 1\n", winC1 / n);
fprintf("%f - Categoria 2\n", winC2 / n);
fprintf("%f - Categoria 3\n", winC3 / n);
fprintf("%f - Categoria 4\n", winC4 / n);