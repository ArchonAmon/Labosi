if (@ARGV != 1) {
die "Mora postojati samo jedan argumment!";
}

$prvi = 0;
while ($ulaz = <>) {

	if ($prvi == 0) {
		$prvi = 1;
		@koeficijenti = split /;/, $ulaz;
	}
	
	else {
	$ind = 3;
	$zbroj = 0;
	@stud = split /;/, $ulaz;

	foreach $trenutni (@koeficijenti) {
	
		$zbroj += $stud[$ind] * $trenutni;
		$ind++;
			
	}
		
		unshift (@studenti, $zbroj . "|||" . $stud[1] . ", " . $stud[2] . " (" . $stud[0] . ")  : " . $zbroj);		#dodavanje $zbroj||| radi sortiranje, kasnije se splita pa |||
	}
}
		@studenti = reverse sort @studenti;
		$ind = 0;
		foreach $tren (@studenti) {
		@temp = split /\|\|\|/, $tren;
		$studenti[$ind] = $temp[1];
		$ind++;
		}
		
		$ind = 1;
		foreach $tren (@studenti) {
			print $ind . ". " . $tren , "\n";
			$ind++;
		}
