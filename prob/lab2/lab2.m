
n = 10000;
c=0;

for j = 1:n
    r = randi(6, 1, 4);
    for i = 1 : 4
        if (r(i) == 6)
            c = c+1;
            break
        end
    end
end

fprintf('First: %f\n', c/n)

c = 0;
for i = 1:n
    r = randi(6, 2, 24);
    for j = 1:24
        if (r(1,j) == 6) && (r(2,j) == 6)
            c = c+1;
            break
        end
    end
end

fprintf('Second: %f\n', c/n)
