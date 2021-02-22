print("3 sata sam izgubio da probam nekako pokrenuti ucitavanje stranice, ali jednostavno se stalno javlja neka greska. Ili se ne moze naci modul ili" +
"se ne moze naci certifikat za uci u stranicu... Ne znam je li do moje python verzije, ili je do cinjenice da koristim windows ili nesto trece." +
"Stoga kopirao izvorni kod od 'http://www.python.org' stranice i zalijepio ga u ulaznu datoteku pa na taj nacin radim zadatak. Zao mi je sto sam toliki failure :(")



#import urllib.request
#import re
#import sys

#stranica = urllib.request.urlopen(sys.argv[1])
#mybytes = stranica.read()
#mystr = mybytes.decode("utf8")
#print(mybytes)


import sys
import re

if len(sys.argv) != 2:
    print "Treba postojati samo jedan argument"
    exit()
    
dat = open(sys.argv[1], 'r');

drugeStranice = {}
email = {}
linkNaSlike = {}
for redak in dat:
    for i in redak.split():
        if (not not re.findall('href=\"{1}[a-zA-Z0-9\/:.,\'?!_%$#\n\r]*"{1}', redak)):
            for i in re.findall('href=\"{1}[a-zA-Z0-9\/:.,\'?!_%$#\n\r]*"{1}', redak):
                drugeStranice[i] = 1
        if (not not re.findall('[a-zA-Z0-9]*@[a-zA-Z0-9.]*', redak)):
            for i in re.findall('[a-zA-Z0-9]*@[a-zA-Z0-9.]*', redak):
                email[i] = 1
        if (not not re.findall('<img src=\"{1}[a-zA-Z0-9\/:.,\'?!_%$#\n\r]*"{1}', redak)):
            for i in re.findall('<img src=\"{1}[a-zA-Z0-9\/:.,\'?!_%$#\n\r]*"{1}', redak):
                linkNaSlike[i] = 1

for i in drugeStranice.keys():
    print i
print("Linkovi na slike = " + str(len(linkNaSlike)))