function r = gx12(x)
  if (x >= -1 && x <= 0)
    r = 1 / (1 + x ^ 2);
  endif
  if (x > 0 && x < 1)
    r = (1 / (x ^ 2)) * sin(x / (1 - x)) ^ 2;
  endif
  if (x >= 1 && x <= 2)
    r = sqrt(2 * x - x ^ 2);
  endif