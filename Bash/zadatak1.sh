#a)
proba="Ovo je proba" 

#b)
echo $proba

#c)
lista_datoteka=*
echo $lista_datoteka

#d)
proba3="$proba. $proba. $proba"

#e)
a=4
b=3
c=7
d=$((($a+4)*$b%$c))

#f)
broj_rijeci=$(wc -w *\.txt)

#g)
ls ~

#h)
cut -f 1,6,7 -d ':' /etc/passwd

#i)
ps -o uid,pid,comm -A