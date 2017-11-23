N = 10000;
timesPrim = 0;
timesPar = 0;
times10 = 0;

for t = 1:N
  zar1 = ceil(rand() * 6);
  zar2 = ceil(rand() * 6);
  
  if (zar1 == 0)
    zar1 = 1;
  endif
  if (zar2 == 0)
    zar2 = 1;
  endif
  
  if (zar1 > 6 || zar2 > 6)
    fprintf("HOPA!!!")  
  endif
  
  if (Prim(zar1 + zar2) == true)
    timesPrim += 1;
  endif
  #if (isprime(zar1 + zar2) == true)
  #  timesPrim += 1;  
  #endif
  if (mod(zar1 + zar2, 2) == 0)
    timesPar += 1;
  endif
  if (zar1 + zar2 <= 10)
    times10 += 1;
  endif
endfor

for i = 1:12
  fprintf("%d -- %d\n", i, Prim(i));
endfor

fprintf("%f -- times prim, Teoretic %f\n", timesPrim/N, 15/36);
fprintf("%f -- times par, Teoretic %f\n", timesPar/N, 1/2);
fprintf("%f -- times <= 10, Teoretic %f\n\n", times10/N, 33/36);