% found(L:list, E:element, R:boolean)
% (i,i,o)
% L - lista de gasit in
% E - elementul de cautat
% R - rezultat
found([], _, false).
found([H|T], E, true):-
    E =:= H.
    % sau
    % , E = true.
found([H|T], E, R):-
    E =\= H,
    found(T, E, R).

% del(L:list, E:elem, R:list)
% (i,i,o)
% L - lista initiala
% E - elementul de eliminat
% R - lista rezultata dupa ce elementul E a fost sters
del([], _, []).
del([H|T], E, R):-
    E =\= H,
    del(T, E, R2),
    R = [H|R2].
del([H|T], E, R):-
    E =:= H,
    del(T, E, R).

% elim(L:list, R:list)
% (i,o)
% L - lista de initiala
% R - lista rezultata
elim([], []).
elim([H|T], R):-
    found(T, H, N),
    N = true,
    del([H|T], H, L),
    elim(L, R).
elim([H|T], R):-
    found(T, H, N),
    N = false,
    elim(T, R2),
    R = [H|R2].










