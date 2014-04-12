#Accepts an array of string in std form decomposed after Deduction Theorem and Axiom 3
#Axiom 1: A>(B>A)
#Axiom 2: ((A>(B>C))>((A>B)>(A>C)))
#Axiom 3: (((A>F)>F)>A)
#from array import *

str = ['p>q', 'p']

temp = '(p>(q>p))'

L = []#array('c')
R = []#array('c')
while temp != '':
    implies = temp.find('>')
    if implies == -1:
        break

    prev_subs = 1
    next_subs = len(temp)-1
    L.append(temp[prev_subs:implies])
    R.append(temp[implies+1:next_subs])

    temp = ''
print L,R
