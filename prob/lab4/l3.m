clear all
clf
hold on

a = [rand() rand()];
b = [rand() rand()];
c = [rand() rand()];

line([a(1) c(1)], [a(2), c(2)])
line([a(1) b(1)], [a(2), b(2)])
line([b(1) c(1)], [b(2), c(2)])

p = [rand() rand()];
plot(p(1), p(2), 'r.')
c1 = Aparte(p, c, a, b);
c2 = Aparte(p, b, a, c);
c3 = Aparte(p, a, b, c);
if c1 * c2 * c3 == 1
    title('Yay');
else
    title('Nay');
end