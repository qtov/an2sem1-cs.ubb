function r = Bern(p)
  if (rand() < p)
    r = 0;
  else
    r = 1;
  endif