broj_kopiranih=0

if [ ! -d ${!#} ]
then
mkdir ${!#}
echo Kazalo ${!#} kreirano!
fi

for i in "$@"
do
if [ ! -d "$i" ] && [ ! -e "$i" ]
then
echo Datoteka "$i" ne postoji ili se ne moze procitati!
elif [ "$i" = ${!#} ]				#ako dode do vec kreirane datoteke treba ju preskociti
then
continue
else
cp -r "$i" "./${!#}"
echo Datoteka "$i" kopirana!
broj_kopiranih=$((broj_kopiranih+1))
fi
done
echo $broj_kopiranih datoteka kopirano je u kazalo ${!#}