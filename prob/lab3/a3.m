clear all
clf
hold on

n = 6;
lu = 4;

A = [0 0];
B = [1/2 sqrt(3)/2];
C = [1, 0];

line([A(1) B(1)], [A(2) B(2)], 'Color', 'b');
line([A(1) C(1)], [A(2) C(2)], 'Color', 'b');
line([C(1) B(1)], [C(2) B(2)], 'Color', 'b');
fill([A(1) B(1) C(1) A(1)], [A(2) B(2) C(2) A(2)], 'black')
for i = 1:n
    subplot(1, n, i)
    hold on
    axis equal
    axis off
    tri_sier(A, B, C, i)
end
