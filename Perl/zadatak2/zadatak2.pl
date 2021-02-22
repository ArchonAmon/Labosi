print "Upisite niz brojeva koji ce ici u listu:\n";

@niz = <STDIN>;			#ctrl+d da se stane s upisom

$i = 0;
$zbroj = 0;
foreach $trenutni (@niz) {
	$zbroj += $trenutni;
	$i++;
}

print "Artimeticka sredina brojeva je: " . ($zbroj/$i) . "\n";