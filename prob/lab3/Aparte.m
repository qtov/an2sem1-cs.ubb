function r = Aparte(A, B, C, D)
    yB_C = B(2) - C(2);
    yD_C = D(2) - C(2);
    xB_C = B(1) - C(1);
    xD_C = D(1) - C(1);
    cB = yB_C / yD_C - xB_C / xD_C;
    
    yA_C = A(2) - C(2);
    xA_C = A(1) - C(1);
    cA = yA_C / yD_C - xA_C / xD_C;
    if cB * cA > 0
        r = 1;
    else
        r = 0;
    end