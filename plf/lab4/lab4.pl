%candidat(L:list, E:integer)
%(i,o)
candidat([E|_], E).
candidat([_|T], E):-
    candidat(T, E).

%invert(L:list, C:list, R:list)
%(i,i,o)
%L-lista inititala, C-var colectoare
invert([], C, C).
invert([H|T], C, R):-
    invert(T, [H|C], R).

%main2(L:list, C:list, F:list, R:list)
%(i,i,i,o)
%L-lista initiala
%C-var colectoare
%F-lista initiala neschimbata
%R-rezultat
main2(_, C, _, R):-
    invert(C, [], R1),
    R = R1.

main2([_|T], [L|LT], F, R):-
    candidat(F, E),
    E > L,
    main2(T, [E,L|LT], F, R).

%main(L:list, R:list)
%L-lista initiala
%R-rezultat
main([H|T], R):-
    candidat([H|T], M),
    main2([H|T], [M], [H|T], R).









