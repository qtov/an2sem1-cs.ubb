; (defun inorder (t1 t2)
;   (cond
;   	((and (null t1) (null t2)) nil)

; 	((and (not (null t2)) (null (cddr t2)) (= (second t2) 0)) 
; 	 (list (car t2)))
; 	((and (not (null t2)) (= (second t2) 0)) 
; 	 (append (list (first t2)) 
; 			 (inorder t1
; 					  (append (list (third t2) (- (fourth t2) 1))
; 							  (cddddr t2)))))
; 	(t (inorder (cddr t1)
; 		(append (list (first t1))
; 				(list (second t1))
;					 t2)))))
; 
; 
; (write (inorder '(A 2 B 0 C 2 D 0 E 0) nil))