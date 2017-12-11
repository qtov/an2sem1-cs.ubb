(defun in (l e)
	(cond
		((null l) 0)
		((= (car l) e) 1)
		(T (in (cdr l) e))
	)
)

(defun inter (l1 l2)
	(cond
		((null l1) nil)
		((= (in l2 (car l1)) 1) (cons (car l1) (inter (cdr l1) l2)))
		(t (inter (cdr l1) l2))
	)
)

(write (inter '(1 2 3 4 4 5 7 6) '(1 2 3 6 8 9 0)))