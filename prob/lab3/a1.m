clear all
clf
hold on

lu = 4;

rectangle('Position', [0 0 lu lu]);
axis([-1 lu+1 -1 lu+1]);

n = 10000;
p1 = 0;
p2 = 0;
p3 = 0;
for i = 1:n
    x = [rand()*lu rand()*lu];
    distA = distPct(x, [0 0]);
    distB = distPct(x, [0 lu]);
    distC = distPct(x, [lu lu]);
    distD = distPct(x, [lu, 0]);
    dist = [distA, distB, distC, distD];
    if (sum(dist > 3) == 1)
        plot(x(1), x(2), 'r.')
        p1 = p1 + 1;
    end
    if (sum(dist > 3) == 0)
        plot(x(1), x(2), 'b.')
        p2 = p2 + 1;
    end
    distAB = distPct(x, [0 lu/2]);
    distBC = distPct(x, [lu, lu/2]);
    distCD = distPct(x, [lu/2 lu]);
    distAD = distPct(x, [lu/2 0]);
    dist_mij = [distAB distBC distCD distAD];
    if (sum(dist_mij < 2) == 1)
        plot(x(1), x(2), 'g.')
        p3 = p3 + 1;
    end
end

fprintf('%f - A1\n', p1/n)
fprintf('%f - A2\n', p2/n)
fprintf('%f - A3\n', p3/n)
fprintf('%f - B sup\n', p2/n * (lu^2))
