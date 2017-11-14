% cmmdc(A:integer, B:integer, R:integer)
% (i,i,o)
% R - rezultat
% Returneaza cel mai mare divizor comun
% al numerelor A si B
cmmdc(A, A, A).
cmmdc(A, B, R):-
    A > B,!,
    A1 is A - B,
    cmmdc(A1, B, R).
cmmdc(A, B, R):-
    B1 is B - A,
    cmmdc(A, B1, R).

% cmmmc(A:integer, B:integer, R:integer)
% (i,i,o)
% R - rezultat
% Returneaza cel mai mic multiplu comun
% al numerelor A si B
cmmmc(0, _, 0).
cmmmc(_, 0, 0).
cmmmc(A, B, R):-
    cmmdc(A, B, K),
    R is A * B / K.

% cmmmcLst(L:list, R:integer)
% (i,o)
% L - lista inititala
% R - rezultat
% Returneaza cel mai mic multiplu comun
% al numerelor din lista L
cmmmcLst([], 1).
cmmmcLst([H|T], R):-
    cmmmcLst(T, R1),
    cmmmc(H, R1, R).











