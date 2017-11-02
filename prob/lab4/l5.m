clear all
clf
hold on

n = 1;

for i = 1:n
    A = [rand() rand()];
    B = [rand() rand()];
    C = [rand() rand()];
    dAB = sqrt((A(1) - B(1)) ^ 2 + (A(2) - B(2)) ^ 2);
    dAC = sqrt((A(1) - C(1)) ^ 2 + (A(2) - C(2)) ^ 2);
    dBC = sqrt((C(1) - B(1)) ^ 2 + (C(2) - B(2)) ^ 2);
    r = 0;
    if (dAB > r)
        r = dAB;
    end
    if (dAC > r)
        r = dAC;
    end
    if (dBC > r)
        r = dBC;
    end
    rectangle('Position', [0, 0, r, r], 'Curvature', [1, 1])
    line([A(1) C(1)], [A(2), C(2)])
    line([A(1) B(1)], [A(2), B(2)])
    line([B(1) C(1)], [B(2), C(2)])
    axis([-1.1 2.1 -1.1 2.1])
    
end

