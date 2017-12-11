function y = Poissoninv(lam, N)
  q = zeros(1, N);
  suma = zeros(1, N);
  q0 = exp(-lam);
  
  for i=1:N
    U = rand();
    j = 0;
    q(i) = q0 * lam/(j);
    while (q(i) < U)
      q(i) = q(i) * lam/(j);
      #disp(q(i));
      j++;
    endwhile
    suma(i) = j;
  endfor
  
  #suma
  y = mean(suma); #medie