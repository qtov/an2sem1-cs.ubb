n = 10000;

r = prob([1, 2, 3, 4], [0.46, 0.4, 0.1, 0.04], n);

x = zeros(1, 4);
for i = 1:length(r)
  x(r(i)) += 1;
endfor

for i = 1:4
  x(i) = x(i) / n;
endfor

display(x);