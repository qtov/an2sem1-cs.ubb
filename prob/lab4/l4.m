clear all
clf
hold on

a = [rand() rand()];
b = [rand() rand()];
c = [rand() rand()];

line([a(1) c(1)], [a(2), c(2)])
line([a(1) b(1)], [a(2), b(2)])
line([b(1) c(1)], [b(2), c(2)])
rectangle('Position', [0 0 1 1])
axis([-0.1 1.1 -0.1 1.1])

n = 1000;
pcts = 0;
for i = 1:n
    p = [rand() rand()];
    plot(p(1), p(2), 'r.')
    c1 = Aparte(p, c, a, b);
    c2 = Aparte(p, b, a, c);
    c3 = Aparte(p, a, b, c);
    if c1 * c2 * c3 == 1
        pcts = pcts + 1;
    end
end

fprintf('%f\n', pcts / n)