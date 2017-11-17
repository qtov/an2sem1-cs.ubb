function r = genLoto()
  nogood = true;
  while nogood == true
    l = randperm(49, 6);
    nogood = false;
    for i = 1:5
      for j = i+1:6
        if (l(i) == l(j))
          nogood = true;
        endif
      endfor
    endfor
  endwhile
  r = l;