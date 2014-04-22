def getCategory(line):
	c = 0
	temp = ''
	if line.find('$$pos$$') != -1:
		c = 1
		temp = line.replace('$$pos$$','')
	elif line.find('$$neu$$') != -1:

		c = 2
		temp = line.replace('$$neu$$','')
	elif line.find('$$neg$$') != -1:
	
		c = 3
		temp = line.replace('$$neg$$','')
	print c, temp
	return c, temp
	

f = open('combinedCorpusFinal', 'r')


x = open('tweet_positive', 'w')
y = open('tweet_neutral', 'w')
z = open('tweet_negative', 'w')

for line in f:
	c, l  = getCategory(line)
	
	if c == 1:
		x.write(l)
	elif  c == 2:
		y.write(l)
	elif  c == 3:
		z.write(l)
		
