class Nod:
    def __init__(self, e):
        self.e = e
        self.urm = None
    
class Lista:
    def __init__(self):
        self.prim = None
        

'''
crearea unei liste din valori citite pana la 0
'''
def creareLista():
    lista = Lista()
    lista.prim = creareLista_rec()
    return lista

def creareLista_rec():
    x = int(input("x="))
    if x == 0:
        return None
    else:
        nod = Nod(x)
        nod.urm = creareLista_rec()
        return nod
    
'''
tiparirea elementelor unei liste
'''
def tipar(lista):
    tipar_rec(lista.prim)
    
def tipar_rec(nod):
    if nod != None:
        print (nod.e)
        tipar_rec(nod.urm)
        
'''
program pentru test
'''

def cmmdc(l1, l2):
    if (l1 == l2):
        return l1
    if (l1 > l2):
        return cmmdc(l1-l2, l2)
    if (l2 > l1):
        return cmmdc(l1, l2-l1)

def cmmmc_aux(l1, l2):
    return (l1 * l2) / cmmdc(l1, l2)

def cmmmc(list, c):
    if (list == None):
        return c
    else:
        return cmmmc(list.urm, cmmmc_aux(c, list.e))

def cmmmco(list):
    return cmmmc(list, 1)
        
def main():
    list = creareLista()
    tipar(list)
    print('cmmmc:', int(cmmmco(list.prim)))
    
main() 
    
    
    
    
    