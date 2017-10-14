n = 10000;

v = zeros(1,18);

for i=1:6
    for j=1:6
        for k=1:6
            v(i+j+k) = v(i+j+k) + 1;
        end
    end
end

A=[1:18;v];
sprintf('%d ', v);
c = 0;

B = [1:18;zeros(1,18)];
for i = 1:n
   r = randi(6,3,1);
   s = 0;
   for j=1:3
      s = s + r(j);
   end
   B(2,s) = B(2,s) + 1;
end

for i = 1:18
    B(2,i) = B(2,i) / n;
end

max_v = max(B(2,:));

for i = 3:18
    if B(2,i) == max_v
        fprintf('Max prob: %d => %f\n', i, max_v)
    end
end

min_v = min(B(2,3:18));

for i = 3:18
    if B(2,i) == min_v
        fprintf('Min prob: %d => %f\n', i, min_v)
    end
end
