#Accepts an array of string in std form decomposed after Deduction Theorem and Axiom 3
#Axiom 1: A>(B>A)
#Axiom 2: ((A>(B>C))>((A>B)>(A>C)))
#Axiom 3: (((A>F)>F)>A)
#from array import *

string = ['p>q', 'p']

exprs = ['p','p>q','(p>F)','((p>F))>q','(((p>F))>q)>q','(p>q)>((((p>F))>q)>q)']
#temp = expr[0:len(expr)]


def findLeftBrace(t):
	print 'This is t in left brace' , t
	count = 0
	flag = False

	for i in xrange(len(t)-1, -1, -1):
		#print i
		if t[i] == ')':
			count += 1
			flag = False
		elif t[i] == '(':
			if count == 0 :
				return i+1	
			else:
				count -= 1
			flag = True
	return 0			
	
def findRightBrace(t):
	print 'This is t in right brace' , t
	count = 0
	flag = True

	for i in xrange(0, len(t), 1):
		#print i
		if t[i] == '(':
			count += 1
			flag = True
		elif t[i] == ')':
			print count
			if count == 0 :
				return i
			count -= 1
			flag = False
	return len(t)	


#L = []#array('c')
#R = []#array('c')
#impl = []

#This function finds all positions of '>'
def getAllImpliesPos(temp):
	impl = []
	for i in xrange(0, len(temp), 1):
		if temp[i] == '>':
			impl.append(i)
	return impl
	
#This function finds the '>' where the expr will split for modus ponens
def getImpliesPos(temp):
	count = 0
	for i in xrange(0, len(temp), 1):
		if temp[i] == '(':
			count += 1
		elif temp[i] == ')':
			count -= 1
		elif temp[i] == '>' and count == 0:
			return i
		
	return -1

#redefined logic of strip braces to handle more cases 	
def stripBraces(temp):
	bPos = dict()
	count = 0
	for i in xrange(0, len(temp), 1):
		print count, ":", i, temp[0:i-1],"^",temp[i+1:len(temp)],"**",bPos
		if temp[i] == '(':
			count += 1
			bPos[i] = count
		elif temp[i] == ')':
			for k in bPos.keys():
				if bPos[k] == count:
					bPos[k] = i
			count -= 1
	print bPos
	
	it = iter(bPos)
	
	if 0 in bPos and bPos[0] == len(temp)-1:
		#print "*****found",temp[1:len(temp) - 1]	
		return stripBraces(temp[1:len(temp) - 1])
		
	return temp

'''
def stripBraces(temp):
	count1,count2 = 0,0
	for i in xrange(0, len(temp), 1):
		if temp[i] == '(':
			count1 += 1
		else:
			break
			
	for i in xrange(len(temp)-1, -1, -1):
		if temp[i] == ')':
			count2 += 1
		else:
			break
	
	index = min(count1, count2)
	temp = temp[index:len(temp)-index]		
	
	return temp
'''

def generateLR(temp):		
	if getImpliesPos(temp) == -1:
		return temp,''

	#L = []
	#R = []
	#for implies in :
	implies = getImpliesPos(temp)
		
	prev_subs = findLeftBrace(temp[0:implies])
	next_subs = findRightBrace(temp[implies+1:len(temp)])
		
	#print prev_subs
	#print next_subs
		
	#print implies
	#L.append( stripBraces (temp[prev_subs:implies]) )
	#R.append( stripBraces (temp[implies+1:implies+1+next_subs]) )

	#temp = temp[0:implies]+temp[implies+1:len(temp)]
	return stripBraces (temp[prev_subs:implies]), stripBraces (temp[implies+1:implies+1+next_subs])

def isMPPossible(L,R,i,j):
	if L[i] == L[j] and R[j] == '' and R[i] != '':
		return True, R[i]
	if L[i] == L[j] and R[i] == '' and R[j] != '':
		return True, R[j]
	return False, ''

def clauseExists(L,R,l,r):
	for i in range(0,len(L),1):
		if L[i] == L[j] and R[i] == R[j]:
			return True
	L.append(l)
	R.append(r)
	return False

#main code
L,R = [],[]
for expr in exprs:
	
	l,r = generateLR(stripBraces(expr))

	L.append(l)
	R.append(r)

print L,":", R

for i in range(0,len(L),1):
	for j in range(0,len(L),1):
		poss, result = isMPPossible(L,R,i,j)
		print poss, result

'''
print stripBraces('(p>q)>((((p>F))>q)>q)')
print stripBraces('(p)')
print stripBraces('(((p)))')
'''
