#citanje iz datoteka
import sys;

def ucitaj():
    dat = open(sys.argv[1], 'r');
    prvi = 0;
    drugi = 0;
    for redak in dat:
        l = redak.split();
        if(prvi == 0 and len(l) == 2 and drugi == 0): 
            prvi = 1
            red1.append(l[0]);
            stup1.append(l[1]);
            continue;
        elif(prvi == 1 and len(l) == 3 and drugi == 0):
            #print int(l[0])
            mat1[(int(l[0]),int(l[1]))] = float(l[2]);
            #print mat1[(int(l[0]),int(l[1]))]
        elif(prvi == 1 and drugi == 0 and len(l) == 2):
            drugi = 1
            red2.append(l[0]);
            stup2.append(l[1]);
            continue;
        elif(prvi == 1 and drugi == 1 and len(l) == 3):
            mat2[(int(l[0]),int(l[1]))] = float(l[2]);
        elif(redak == ""):
            return;
        elif(redak == "\n" or redak == "\r\n"):         #gleda je li novi red
            continue;
        else:
            print("Pogresno zadane matrice");
            exit();



mat1 = {};
mat2 = {};
mat3 = {};
red1 = []
red2 = [] 
stup1 = [] 
stup2 = [];

ucitaj();       #poziv funkcije

if stup1[0] != red2[0] :            #je li broj stupaca prve jedank broju redova druge
   print("Pogresno zadane matrice");
   exit();
   
for i in range(int(red1[0])):
    for j in range(int(stup2[0])):
        mat3[i,j] = 0
   
for i in range(int(red1[0])):
    for j in range(int(stup2[0])):
        for k in range(int(red2[0])):
            #print "{} '+=' {} '*' {}".format(mat3[i,j], mat1.get((i,k), 0), mat2.get((k,j), 0))
            mat3[i,j] += mat1.get((i,k), 0) * mat2.get((k,j), 0)
            

#mat3[0,0] = 1      #indeksiranje
dat = open(sys.argv[2], 'w');
s = "";
for i in range(int(red1[0])):
    s = "";
    for j in range(int(stup2[0])):
        s += str(mat3[i,j]) + " ";
    print s;
    dat.write(s + "\n");