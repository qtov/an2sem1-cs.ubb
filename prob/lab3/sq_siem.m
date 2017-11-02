function r = sq_siem(A, B, C, D, n)
    if (n ~= 0)
        M = [A(1)+(C(1)/3) A(2)+(B(2)/3)];
        N = [M(1) M(2)*2];
        O = [N(1)*2 N(2)];
        P = [N(1)*2 A(2)+(B(2)/3)];
        fill([M(1) N(1) O(1) P(1)], [M(2) N(2) O(2) P(2)], 'w')
%         display(A)
%         display([A(1) M(2)])
%         display(M)
%         display([M(1) A(2)])
        sq_siem(A, [A(1) M(2)], M, [M(1) A(2)], n - 1)
%         sq_siem([A(1) M(2)], [A(1) N(2)], N, M, n - 1)
%         sq_siem([A(1) N(2)], B, [N(1) B(2)], N, n - 1)
%         sq_siem(N, [N(1) B(2)], [O(1) B(2)], O, n - 1)
%         sq_siem(O, [O(1) B(2)], C, [C(1) O(2)], n - 1)
%         sq_siem(P, O, [C(1) O(2)], [C(1) P(2)], n - 1)
%         sq_siem([P(1) D(2)], P, [D(1) P(2)], D, n - 1)
%         sq_siem([M(1) A(2)], M, P, [P(1) A(2)], n - 1)
    end