clear all
clf
hold on

rD = 2;

rectangle('Position', [0, 0, rD, rD])
axis([-1, rD + 1, -1, rD + 1])

A = [rand() * rD, rand() * rD];
B = [rand() * rD, rand() * rD];
C = [rand() * rD, rand() * rD];
D = [rand() * rD, rand() * rD];

plot(C(1), C(2), 'r.');
text(C(1), C(2), 'C')
plot(D(1), D(2), 'r.');
text(D(1), D(2), 'D')
line([C(1) D(1)], [C(2) D(2)]);
plot(A(1), A(2), 'r.');
text(A(1), A(2), 'A')
plot(B(1), B(2), 'r.');
text(B(1), B(2), 'B')

t = Aparte(A, B, C, D);
if t == 1
    title('Sunt pe aceiasi parte')
else
    title('Nu sunt pe aceiasi parte')
end