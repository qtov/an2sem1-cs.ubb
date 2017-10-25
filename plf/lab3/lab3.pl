% inloc(L:list, X:integer, Y:integer, R:list)
% (i,i,i,o)
% L - lista initiala
% X - elementul de inlocuit
% Y - elementul cu care se inlocuieste
% R - lista rezultata dupa inlocuire
inloc([], _, _, []).
inloc([H|T], X, Y, R):-
    H =\= X,
    inloc(T, X, Y, R2),
    R = [H|R2].
inloc([H|T], X, Y, R):-
    H =:= X,
    inloc(T, X, Y, R2),
    R = [Y|R2].

% max(L:list, M:integer, R:integer)
% (i,i,o)
% L - lista initiala
% M - elementul maxim
% R - rezultat
max([], M, M).
max([H|T], M, R):-
    is_list(H),
    max(T, M, R).
max([H|T], M, R):-
    \+is_list(H),
    H > M,
    max(T, H, R).
max([H|T], M, R):-
    \+is_list(H),
    H @=< M,
    max(T, M, R).

% max_aux(L:list, R:integer)
% (i,o)
% L - lista initiala
% R - rezultat
max_aux([], 0).
max_aux([H|T], R):-
    max([H|T], H, R).

% aparmax_aux(L:list, M:integer, R:list)
% (i,i,o)
% L - lista initiala
% M - elementul maxim din lista
% R - lista rezultata
aparmax_aux([], _, []).
aparmax_aux([H|T], M, R):-
    \+is_list(H),
    aparmax_aux(T, M, R2),
    R = [H|R2].
aparmax_aux([H|T], M, R):-
    is_list(H),
    max_aux(H, MAX_H),
    inloc(H, M, MAX_H, R2),
    aparmax_aux(T, M, R3),
    R = [R2|R3].

% aparmax(L:list, R:list)
% (i,o)
% L - lista initiala
% R - lista rezultata
aparmax([], []).
aparmax(L, R):-
    max_aux(L, M),
    aparmax_aux(L, M, R).
