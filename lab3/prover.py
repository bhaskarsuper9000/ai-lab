#Accepts an array of string in std form decomposed after Deduction Theorem and Axiom 3
#Axiom 1: A>(B>A)
#Axiom 2: ((A>(B>C))>((A>B)>(A>C)))
#Axiom 3: (((A>F)>F)>A)
#from array import *
import subprocess
from subprocess import Popen, PIPE
import sys


#exprs = ['p','p>q','(p>F)','((p>F))>q','(((p>F))>q)>q','(p>q)>((((p>F))>q)>q)']
#temp = expr[0:len(expr)]


def findLeftBrace(t):
	#print 'This is t in left brace' , t
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
	#print 'This is t in right brace' , t
	count = 0
	flag = True

	for i in xrange(0, len(t), 1):
		#print i
		if t[i] == '(':
			count += 1
			flag = True
		elif t[i] == ')':
			#print count
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
		#print count, ":", i, temp[0:i-1],"^",temp[i+1:len(temp)],"**",bPos
		if temp[i] == '(':
			count += 1
			bPos[i] = count
		elif temp[i] == ')':
			for k in bPos.keys():
				if bPos[k] == count:
					bPos[k] = i
			count -= 1
	#print bPos
	
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

#If Modus Ponens possible, return True, result
def isMPPossible(L,R,i,j):
	if L[i] == L[j] and R[j] == '' and R[i] != '':
		print L[i],'>',R[i],'MP',L[j],'yields',R[i]
		return True, R[i]
	if L[i] == L[j] and R[i] == '' and R[j] != '':
		print L[j],'>',R[j],'MP',L[i],'yields',R[j]
		return True, R[j]
	return False, ''

#Check if l,r doesn't exist in L,R...If it doesn't, add it
def clauseExists(L,R,l,r):
	if l == '':
		return False

	for i in range(0,len(L),1):
		if L[i] == l and R[i] == r:
			return True
	L.append(l)
	R.append(r)
	return False

def parseExpr(t):
	stmt = Popen(['java','Parser', t], stdout = subprocess.PIPE).communicate()[0]
	return stmt.split()

#Returns the list of propositions that can be used as substitutions
#for axioms or theorems
def getPropositions(t):
	props = []
	for i in range(0,len(t),1):
		if t[i] != ')' and t[i] != '(' and t[i] != '>' and t[i] != '~' and t[i] != '^' and t[i] != 'v':
			props.append(t[i])

	temp = []	
	for p in props:
		if p != 'F':
			temp.append(p+'>F')
	
	for p in props:
		for q in props:
			if p != q:
				temp.append(p+'>'+q)
				temp.append(q+'>'+p)

	props = props + temp
	props = list(set(props))
	#print props
	return props

#parenthesizes exprs if reqd
def sanit(d):
	if len(d) == 1:
		return d
	return '('+d+')'

def getAxiom(thresh, iterations, default):
	axioms = ['#>($>#)','(#>($>@))>((#>$)>(#>@)))','((#>F)>F)>#']
	
	axiom = []
	
	if iterations < thresh[1]:
		#Apply axiom 1 combinations
		for i in range(0,len(default),1):
			for j in range(0,len(default),1):
				temp = axioms[0].replace('#', sanit(default[i]))
				temp = temp.replace('$',sanit(default[j]))
				axiom.append(temp)
	
		#Apply axiom 2 combinations		
		for i in range(0,len(default),1):
			for j in range(0,len(default),1):
				for k in range(0,len(default),1):
					temp = axioms[1].replace('#',sanit(default[i]))
					temp = temp.replace('$',sanit(default[j]))
					temp = temp.replace('@',sanit(default[k]))
					axiom.append(temp)
				
		for i in range(0,len(default),1):
			temp = axioms[2].replace('#',sanit(default[i]))
			axiom.append(temp)
	
		axiom = list(set(axiom))
		#print axiom		
	elif iterations > thresh[1]:
		print '\nWhich axiom do you want to use ?[1,2,3]'
		ch = raw_input()
		if ch == '1':
			print 'Enter 2 variables to be substituted'
			i = raw_input()
			j = raw_input()
			temp = axioms[0].replace('#', sanit(i))
			temp = temp.replace('$',sanit(j))
			axiom.append(temp)
		elif ch == '2':
			print 'Enter the 3 variables to be substituted'
			i = raw_input()
			j = raw_input()
			k = raw_input()
			temp = axioms[1].replace('#',sanit(i))
			temp = temp.replace('$',sanit(j))
			temp = temp.replace('@',sanit(k))
			axiom.append(temp)
		elif ch == '3':
			print 'Enter 1 variable to be substituted'
			i = raw_input()
			temp = axioms[2].replace('#',sanit(i))
			axiom.append(temp)
		else:
			print 'You selected an invalid operation'
	else:
		print "I don't think this is working. I suggest you try the theorem option instead!"
		iterations = 101		
	
	return axiom

def getTheorem(L,R):
	print 'Enter a theorem to be applied (with proper braces)'
	t = raw_input()
	exprs = parseExpr('('+t+')')
	#print exprs
	for expr in exprs:	
		l,r = generateLR(stripBraces(expr))
		#print l,r
		clauseExists(L,R,l,r)
	
	#for i in range(0, len(L),1):
		#print L[i],':',R[i]

#		***********************************
#main code
L,R = [],[]

#call(['./java Parser','((p>q)>(((~p)>q)>q))'])

exprs = parseExpr('((p>q)>(((~p)>q)>q))')
props = getPropositions('((p>q)>(((~p)>q)>q))')

#Need to remove this later
#clauseExists(L,R,'p','')

for expr in exprs:	
	l,r = generateLR(stripBraces(expr))
	clauseExists(L,R,l,r)	#This function also appends l,r to L,R

print L,":", R

done = False
iterations = 0
thresh = [100,200,300,400]

while not done:
	iterations += 1
	
	#Animation to show that process is still alive
	if iterations%20 == 0:
		sys.stdout.write('.')
		sys.stdout.flush()

	#Try applying modus ponens
	for i in range(0,len(L),1):
		for j in range(0,len(L),1):
			poss, result = isMPPossible(L,R,i,j)
			#print poss, result
			if poss == True:
				iterations = 0
			clauseExists(L,R,result,'')
			
	
	#Check for existance of 'F'
	for i in range(0,len(L),1):
		#print L[i], '>', R[i]
		if L[i] == 'F':
			done = True
			break
	#Redundancy to make sure, we dont go far!
	if done:
		break

	#Try applying axioms!!!
	
	if iterations > thresh[0]:
		#print L,':',R
		#thresh[0] = thresh[1]	
		choice = '0'
		
		if iterations < thresh[1]:
			choice = '1'
		
		if iterations > thresh[1]:
			print '\nWhat do you want me to do', '[1=Axiom, 2=Theorem, 3=Give up] ?'
			choice = raw_input()

		
		if choice == '1':
			newStmts = getAxiom(thresh,iterations,props)
			for s in newStmts:
				#iterations = 0
				#print s
				l,r = generateLR(stripBraces(s))
				clauseExists(L,R,l,r)
		elif choice == '2':
			getTheorem(L,R)
		elif choice == '3':
			break
	


if done:
	print 'I am done coz I reached F'
else:
	print 'I give up!'

'''
print stripBraces('(p>q)>((((p>F))>q)>q)')
print stripBraces('(p)')
print stripBraces('(((p)))')
'''
