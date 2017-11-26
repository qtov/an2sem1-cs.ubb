x = zeros(1, 52);
n = 1000;
for i = 1:length(x)
  x(i) = i;
endfor

Aces = [1, 2, 3, 4];
sumA = zeros(1, 4);

for N = 1:n
  Xrand = randperm(52, 4);
  for i = 1:length(Xrand)
    if (Xrand(i) == 1)
      sumA(1) += 1;
    endif
    if (Xrand(i) == 2)
      sumA(2) += 1;
    endif
    if (Xrand(i) == 3)
      sumA(3) += 1;
    endif
    if (Xrand(i) == 4)
      sumA(4) += 1;
    endif
  endfor
endfor

for i = 1:length(sumA)
  sumA(i) = sumA(i) / n;
endfor

n = 1000;
display(sumA);
AceOrNot = [1 2 3 4 5];
AcesProb = [1/52 1/52 1/52 1/52 48/52];

AceFin = prob(AceOrNot, AcesProb, n);

AcesNum = zeros(1,5);
for i = 1:length(AceFin)
  AcesNum(AceFin(i)) += 1;
endfor

for i = 1:length(AcesNum)
  AcesNum(i) /= n;
endfor

display(AcesNum);
