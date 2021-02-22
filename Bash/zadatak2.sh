#a)
grep -i "banana\|jabuka\|jagoda\|dinja\|lubenica" "/projekti(2.zadatak)/namirnice.txt"

#b)
grep -i -v "banana\|jabuka\|jagoda\|dinja\|lubenica" "/projekti(2.zadatak)/namirnice.txt" > "/projekti(2.zadatak)/ne-voce.txt"

#c)
grep -r -E "\<[A-Z]{3}[0-9]{6}\>" "C:\\Programiranje\\Bash_playground\\projekti(2.zadatak)"			#r cita rekurzivno sve poddatotke,also direktorij je trebao biti ~/projekti, \< to mora biti pocetak rijeci, \> to mora biti kraj rijeci

#d)
find ./ -mtime +7 -mtime -14 -ls

#e)
for i in {1..15}; do echo $i; done