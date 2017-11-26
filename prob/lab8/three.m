x = [1,2];
y = [0.05, 0.95];
n = 1000;
def = zeros(1,2);

for i = 1:n
  prod = prob(x, y, 5);
  if ( any(prod(:) == 1) )
    def(1) += 1;
  else
    def(2) += 1;
  endif
endfor

def(1) /= n;
def(2) /= n;
display(def);