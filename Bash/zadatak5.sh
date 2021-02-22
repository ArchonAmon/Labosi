echo $1
echo $2

ukupno=0

#echo $(echo $(pwd)/$1 | grep -c -r)

for i in $(find $(pwd)/$1 -name "$2")
do
ukupno=$((ukupno + $(wc -l $i | cut -d " " -f 1)))
#echo $i
done
echo $ukupno