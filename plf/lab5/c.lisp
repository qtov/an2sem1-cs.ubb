(defun sortare (l)
	(sort (remove-duplicates l) #'<))

(write (sortare '(3 6 1 4 6)))