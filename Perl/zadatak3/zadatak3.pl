if (@ARGV == 0) {	 #@ARGV je broj argumenata u cmd-u
	print "Upisite podatke o logovima:\n";
}
$datum = "";
$prosliSat = "00";
$satCounter = 0;
$trenutni = $ARGV[0];
while ($ulaz = <>) {   

	if ($trenutni ne $ARGV) {					#ako smo dosli na drugu datoteku onda se treba opet krenuti od 0, ali se treba i ispisati 23 iz prosle datoteke
	print $prosliSat . " : " . $satCounter . "\n";
	$prosliSat = $datumPom2[1];
	$trenutni = $ARGV;
	$prosliSat = "00";
	$satCounter = 0;
	}

	#ovdje ide pretrazivanje linija
	@datumPom = split /\[/, $ulaz;
	@datumPom2 = split /:/, $datumPom[1];
	
	if ($datum ne $datumPom2[0]) {
	$datum = $datumPom2[0];
	print "\nDatum: " . $datum . "\n";
	print "sat : broj pristupa\n";
	print "-----------------------------------\n";
	}
	if ($prosliSat eq $datumPom2[1]) {
	$satCounter++;
	}
	else {
	print $prosliSat . " : " . $satCounter . "\n";
	$satCounter = 1;
	$prosliSat = $datumPom2[1];
	}
}
	print $prosliSat . " : " . $satCounter . "\n";
