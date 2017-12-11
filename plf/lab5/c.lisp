(defun elim (l e)
	(cond
		((null l) nil)
		((= (car l) e) (elim (cdr l) e))
		(t (cons (car l) (elim (cdr l) e)))
	)
)

(defun min2 (l m)
	(cond
		((null l) m)
		((>= (car l) m) (min2 (cdr l) m))
		(t (min2 (cdr l) (car l)))
	)
)

(defun sortare (l)
	(cond
		((null l) nil)
		((null (cdr l)) (list (car l)))
		(t (cons (min2 l (car l)) (sortare (elim l (min2 l (car l))))))
	)
)

(write (sortare '(6 5 4 3 2 1)))
(write-line "")
(write (sortare '(1 5 3 7 5 9 7 9)))
(write-line "")
