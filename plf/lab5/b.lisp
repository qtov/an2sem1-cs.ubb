(defun max2 (a b)
	(cond
		((> a b) a)
		(t b)
	)
)

(defun adl (l m ma)
	(cond
		((and (null l) (> m ma)) m)
		((null l) ma)
		((listp (car l)) (adl (cdr l) m (max2 (adl (car l) (+ m 1) ma) m)))
		(t (adl (cdr l) m ma))
	)
)

(defun adancime (l)
	(adl l 0 0)
)

(write (adancime '(1 2 3 4)))
(write (adancime '(1 2 (1 2) 3 4)))
(write (adancime '(1 2 (1 2) 3 4 (1 (3 4) 2 (3 4)))))
(write (adancime '(1 2 ((1 2 (3 4)) 1 2 3 4) 3 4)))
(write (adancime '(0 0 ((2 2 (3 3)) 1 (2 2 (3 3 3 3 (4 4 4 4)) 2 2) 1 1 1) 0 0)))
