clear all
clf
hold on

n = 8;
#for i = 1:n
#  for j = 1:n
#    rectangle('Position', [i - 1, j - 1, 1, 1])
#  endfor
#endfor

N = 20;
times = 0;
for t = 1:N
  x = rand();
  initPos = [0 0];
  subplot(4, N/4, t)
  hold on
  axis equal
  axis off
  for p = 1:n
    for o = 1:n
      rectangle('Position', [p - 1, o - 1, 1, 1])
    endfor
  endfor
  for i = 1:n
    if (Bern(x) == 1)
      finPos = [initPos(1)+1 initPos(2)];
    else
      finPos = [initPos(1) initPos(2)+1];
    endif
    #pause(0.02)
    plot([initPos(1) finPos(1)], [initPos(2) finPos(2)], 'r', "linewidth", 2);
    initPos = finPos;
  endfor
  if (initPos == [4 4])
    times += 1;
  endif
endfor

fprintf("%f -- times\n", times/n);