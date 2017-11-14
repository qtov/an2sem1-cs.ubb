subsir([], []).
subsir([_|T], R):-
    subsir(T, R).
subsir([H|T], [H|R]):-
    subsir2(T, R).

subsir2(_, []).
subsir2([H|T], [H|R]):-
    subsir2(T, R).







