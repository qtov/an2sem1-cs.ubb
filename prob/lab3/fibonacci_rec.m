function r = fibonacci_rec(n)
    if (n == 0)
        r = 0;
    elseif (n == 1)
        r = 1;
    elseif (n == 2)
        r = 1;
    else
        r = fibonacci_rec(n - 1) + fibonacci_rec(n - 2);
    end