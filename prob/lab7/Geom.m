function g = Geom(p, n)
  insucc = 0;
  for i = 1:n
    step = 0;
    r = -1;
    
    while true
      r = rand();
      
      if (r <= p)
        insucc += step;  
        break;  
      endif
      
      step += 1;
    endwhile
    
  endfor
  g = insucc;