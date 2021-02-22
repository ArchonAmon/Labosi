import sys

if len(sys.argv) != 2:
    print "Treba postojati samo jedan argument"
    exit()

b = 0;
l = [];
sorted = []
dat = open(sys.argv[1], 'r');
for redak in dat:
    l.append(redak.split());

for zapis in l:         #pretvaranje stringova u brojeve
    d = []
    for z in zapis:
        d.append(float(z))
    sorted.append(d)

print "Hyp#Q10#Q20#Q30#Q40#Q50#Q60#Q70#Q80#Q90"
ind = 0;
for z in sorted:            #sortiranje
    z.sort();               #sort sortira istu listu koju smo mu poslali
    velicina = len(z);
    l = []
    for i in range(9):
        l.append(str(z[int(round(velicina*((i+1)/10.)))-1]))
    ind = ind + 1;
    print str(ind) + "#" + "#".join(l)