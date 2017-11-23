N = 8

arr = zeros(N, N);
arr(1, 1) = 1;
for i = 2:N
  arr(i, 1) = arr(i - 1, i - 1);
  for j = 2:i
    arr(i, j) = arr(i, j - 1) + arr(i - 1, j - 1);
  endfor
endfor

disp(arr)