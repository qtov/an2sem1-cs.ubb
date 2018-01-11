(defun maxone (&rest l)
	(maxx l (car l))	
)

(defun maxx (l m)
	(cond
	  	((null l) m)
		((> (car l) m) (maxx (cdr l) (car l)))
		(t (maxx (cdr l) m))
	)
)

(defun adancime (l)
	(cond
		((null l) 1)
		((atom l) 0)
		(t (+ 1 (apply #'maxone (mapcar #'adancime l))))
	)
)

(write (adancime '(1 1 1 1 (2 2 (3 3 3 (4 4 (5 5 5 5) 4 4) 3) 2 (3 (4 4 4 (5 5 (6 6 (7 7 7 7) 6) 5 5) 4) 3) 2) 1 1 1)))
