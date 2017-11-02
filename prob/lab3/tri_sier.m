function r = tri_sier(A, B, C, n)
    if (n ~= 0)
        M = (A+B)/2;
        N = (C+B)/2;
        P = (A+C)/2;
        fill([M(1) N(1) P(1) M(1)], [M(2) N(2) P(2) M(2)], 'w')
        tri_sier(A, P, M, n - 1)
        tri_sier(B, M, N, n - 1)
        tri_sier(C, N, P, n - 1)
    end