for i in $(pwd)/$1/*.jpg		#$1 je cmd argument broj 1, $2 je argument broj 2 itd
do
year=$(stat --format "%y" "$i" | cut  -f 1 -d " " | cut -f 1 -d "-")
month=$(stat --format "%y" "$i" | cut  -f 1 -d " " | cut -f 2 -d "-")
#echo $year-$month
if [ ! -d $(pwd)/$2/$year-$month ]
then
mkdir $(pwd)/$2/$year-$month
fi
mv "$i" "$(pwd)/$2/$year-$month"
done