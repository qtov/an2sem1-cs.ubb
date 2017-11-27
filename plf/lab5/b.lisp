(defun adancime (l)
	(if (null l)
		0
		(+ 1 (adancime (cdr l)))))

(write (adancime '(1 2 3 4)))