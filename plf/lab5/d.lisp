(defun in (l1 e)
	(if (null l1)
		0)
	(if (= (car l1) e)
		1
	)
	(if (and (not (null l1)) (\= (car l1) e))
	    (in (cdr l1) e))
)

(defun in2 (l e)
	(cond
		((null l) 0)
		((= (car l) e) 1)
		(T (in2 (cdr l) e))
	)
)

(defun inter (l1 l2)
	(if (null l1)
		nil
		)
	(if (= (in l2 (car l1)) 1)
		(cons (car l1) (inter (cdr l1) l2))
		(inter (cdr l1) l2)))


(write (in '(1 2 3 4) 4))