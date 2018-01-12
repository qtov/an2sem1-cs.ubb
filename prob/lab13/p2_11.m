N = 10000;
M = 10;
xn = zeros(1, N);
yn = zeros(1, N);

a = 0;
b = 1;

for i = 1:N
  r1 = rand() * (b - a) + a;
  xn(i) = r1;
  r2 = rand() * M;
  yn(i) = r2;
endfor

P = 0;

for i = 1:N
  if (yn(i) <= gx01(xn(i)))
    P++;
  endif
endfor

fprintf('%f -- res\n', M * (b - a) * (P / N));

