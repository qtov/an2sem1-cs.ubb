function r = Prim(n)
  q = true;
  if (n == 1)
    q = false;
  endif
  for i = 2:n/2
    if (mod(n, i) == 0)
      q = false;
      break;
    end
  endfor
  r = q;