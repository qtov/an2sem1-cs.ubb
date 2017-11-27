(defun prod (l1 l2)
	(if (or (null l1) (null l2))
		0
		(+ (* (car l1) (car l2))
			(prod (cdr l1) (cdr l2)))))

(write (prod '(1 2 3 4) '(1 2 3)))