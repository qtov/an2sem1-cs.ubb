N = 10000;
xn = zeros(1, N);
yn = zeros(1, N);

a = 2;
b = 5;

for i = 1:N
  r1 = rand() * (b - a) + a;
  xn(i) = r1;
  yn(i) = (b - a) * gx25(xn(i));
endfor

fprintf('%f -- prob\n', 1 / N * sum(yn));
