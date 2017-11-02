clear all
clf
hold on

n = 6;
lu = 4;

A = [0 0];
B = [0 1];
C = [1 1];
D = [1 0];

fill([A(1) B(1) C(1) D(1)], [A(2) B(2) C(2) D(2)], 'black')

sq_siem(A, B, C, D, 2)