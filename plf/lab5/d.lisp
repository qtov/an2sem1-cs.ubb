(defun reuniune (l1 l2)
	(append l1 l2))

(defun reuniune_recurs (l1 l2)
	(if (null l1)
		l2
		(cons (car l1) (reuniune_recurs (cdr l1) l2))))

(write (reuniune_recurs '(1 2 3 4) '(1 2 5 6 7)))