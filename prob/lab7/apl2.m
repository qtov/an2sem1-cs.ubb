sumA = 6;
sumB = 7;
winA = 0;
winB = 0;

n = 10000;
aruncari = 0;

for i = 1:n
  while true
    A = ceil(rand() * 6) + ceil(rand() * 6);
    B = ceil(rand() * 6) + ceil(rand() * 6);
    if (A == sumA)
      winA += 1;
      break;
    endif
    if (B == sumB)
      winB += 1;
      break;
    endif;
  endwhile
endfor

fprintf("%f - A win\n", winA / n);
fprintf("%f - B win\n", winB / n);