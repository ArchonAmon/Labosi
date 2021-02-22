for i in "C:/Programiranje/Bash_playground/logovi(3.zadatak)/log.20??-02-??.txt"			#u for petlju smiju ici pathovi i ako ima vise datoteka koji odgovaraju pathu ici ce se kroz njih
do
day=$(echo $i | cut -d "." -f 2 | cut -d "-" -f 3)						# | je pipeline i prenosi izlaz ispis lijeve strane na desnu
month=$(echo $i | cut -d "." -f 2 | cut -d "-" -f 2)
year=$(echo $i | cut -d "." -f 2 | cut -d "-" -f 1)
echo datum: $day.$month.$year

echo -------------------------------------

cut -d "\"" -f 2 $i | sort -r | uniq -c | sort -r -n

done
