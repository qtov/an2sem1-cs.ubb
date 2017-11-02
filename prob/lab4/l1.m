clear all
clf
hold on

rD = 1;

rectangle('Position', [0, 0, rD, rD])

axis([-1, 2, -1, 2])

rectangle('Position', [0, 0, rD, rD], 'Curvature', [1, 1])
Cx = 0.5;
Cy = 0.5;
r = 0.5;
inC = 0;
ouC = 0;

n = 1000;
for i=1:n
    x = rand();
    y = rand();
    d = sqrt((x - Cx)^2 + (y - Cy)^2);
    if (d <= r)
        plot(x,y,'r.','MarkerSize', 10)
        inC = inC + 1;
    else
        plot(x,y,'b.','MarkerSize', 10)
        ouC = ouC + 1;
    end
end

fprintf('%f - practic\n', inC / n)
fprintf('%f - teoretic\n', pi / 4)