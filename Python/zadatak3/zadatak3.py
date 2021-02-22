import sys
import os

if len(sys.argv) != 2:
    print "Treba postojati samo jedan argument"
    exit()


studenti = {};
trenLab = 0;
bodovi = {};
for file in os.listdir(sys.argv[1]):
    if file == "studenti.txt" :
        c = open(sys.argv[1]+"/"+file, 'r');
        for m in c:
            d = m.split()
            studenti[d[0]] = (d[1],d[2])
        continue;
    elif int(file.split("_")[0].split("Lab")[1] != trenLab):
        trenLab = int(file.split("_")[0].split("Lab")[1])
        dat = open(sys.argv[1]+"/"+file, 'r');                  #umjesto / staviti \ ako ne valja za linux
        tempTrenLab = trenLab
        for m in dat:
            i = m.split()
            if (i[0], tempTrenLab) in bodovi:
                print "Isti jmbag u vise vjezba!"
                exit();
            else :
                bodovi[i[0], tempTrenLab] = i[1];
                
li = "JMBAG         Prezime, Ime"
for i in range(trenLab):
    li += "    L"+str(i)
print li

for i in studenti:
    l = str(i) + "    " + str(studenti[i][0]) + "    " + str(studenti[i][1]);
    for j in range(trenLab):
        l += "    " + str(bodovi.get((i, j+1),0))
    print l