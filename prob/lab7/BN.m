function b = BN(p, r, n)
  aux = [];
  for i = 1:n
    aux = [aux , Geom(p, r)];
  endfor
  b = aux;