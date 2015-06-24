from numpy import zeros
from scipy.linalg import svd
#following needed for TFIDF
from math import log, sqrt, pow, acos, pi
from numpy import asarray, sum

titles = ["The Neatest Little Guide to Stock Market Investing",
          "Investing For Dummies, 4th Edition",
          "The Little Book of Common Sense Investing: The Only Way to Guarantee Your Fair Share of Stock Market Returns",
          "The Little Book of Value Investing",
          "Value Investing: From Graham to Buffett and Beyond",
          "Rich Dad's Guide to Investing: What the Rich Invest in, That the Poor and the Middle Class Do Not!",
          "Investing in Real Estate, 5th Edition",
          "Stock Investing For Dummies",
          "Rich Dad's Advisors: The ABC's of Real Estate Investing: The Secrets of Finding Hidden Profits Most Investors Miss"
          ]
stopwords = ['and','edition','for','in','little','of','the','to']
ignorechars = ''',:'!'''

class LSA(object):
    def __init__(self, stopwords, ignorechars):
        self.stopwords = stopwords
        self.titles = titles
        self.ignorechars = ignorechars
        self.wdict = {}
        self.dcount = 0        
    def parse(self, doc):
        words = doc.split();
        for w in words:
            w = w.lower().translate(None, self.ignorechars)
            if w in self.stopwords:
                continue
            elif w in self.wdict:
                self.wdict[w].append(self.dcount)
            else:
                self.wdict[w] = [self.dcount]
        self.dcount += 1      
    def build(self):
        self.keys = [k for k in self.wdict.keys() if len(self.wdict[k]) > 1]
        self.keys.sort()
        self.A = zeros([len(self.keys), self.dcount])
        for i, k in enumerate(self.keys):
            for d in self.wdict[k]:
                self.A[i,d] += 1
    def calc(self):
        self.U, self.S, self.Vt = svd(self.A)
    def TFIDF(self):
        WordsPerDoc = sum(self.A, axis=0)        
        DocsPerWord = sum(asarray(self.A > 0, 'i'), axis=1)
        rows, cols = self.A.shape
        for i in range(rows):
            for j in range(cols):
                self.A[i,j] = (self.A[i,j] / WordsPerDoc[j]) * log(float(cols) / DocsPerWord[i])

    def cosine(self):

        word1 = raw_input("Ingrese una palabra: ")
        word2 = raw_input("Ingrese otra palabra: ")

        posicion1 = -1
        posicion2 = -1

        for i in range(len(self.keys)):
            if (word1 == self.keys[i]):
                posicion1 = i
            elif (word2 == self.keys[i]):
                posicion2 = i

        if (posicion1 != -1) or (posicion2 != -1):
            self.vector1 = [-1*self.U[posicion1,1], -1*self.U[posicion1,2]]
            self.vector2 = [-1*self.U[posicion2,1], -1*self.U[posicion2,2]]

            print 'vector', word1, '\t\t', self.vector1
            print 'vector', word2, '\t',self.vector2

            producto = (self.vector1[0]*self.vector2[0]) + (self.vector1[1]*self.vector2[1])

            valor1 = sqrt(pow(self.vector1[0], 2) +pow(self.vector1[1], 2))
            valor2 = sqrt(pow(self.vector2[0], 2) +pow(self.vector2[1], 2))
            cosine = producto / (valor1*valor2)
            arcCosRad = acos(cosine)
            arcCosine = 180*arcCosRad/pi

            print 'coseno del angulo entre ambos vectores', cosine
            print 'angulo entre ambos vectores', arcCosine

        else:
            print 'Ninguna palabra figura en la lista'
            print 'No se puede calcular ni el coseno ni su angulo ya que ninguna de las palabras ingresadas figura en la lista'


    def printA(self):
        print 'Here is the count matrix'
        print '\t\t d1, d2, d3, d4, d5, d6, d7, d8, d9'
        for i in range(len(self.A)):
            if(len(self.keys[i]) < 7):
                print self.keys[i], '\t\t', self.A[i]
            else:
                print self.keys[i], '\t', self.A[i]

    def printSVD(self):
        print 'Here are the singular values'
        print self.S
        print 'Here are the first 3 columns of the U matrix'
        for i in range(len(self.A)):
            if(len(self.keys[i]) < 7):
                print self.keys[i], '\t\t', -1*self.U[i, 0:3]
            else:
                print self.keys[i], '\t', -1*self.U[i, 0:3]
        print 'Here are the first 3 rows of the Vt matrix'
        print -1*self.Vt[0:3, :]
        print '\n\n'

mylsa = LSA(stopwords, ignorechars)
for t in titles:
    mylsa.parse(t)
mylsa.build()
mylsa.printA()
mylsa.calc()
mylsa.printSVD()
mylsa.cosine()