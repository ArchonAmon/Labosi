if (@ARGV > 1) {
	die "Broj argumenata nije valjan!";
}

else {
	while ($ulaz = <>) {
		@ulazSplit = split /;/, $ulaz;
		@termin = split / /, $ulazSplit[3];
		@terminVrijeme = split /:/, $termin[1];
		@zakljucao = split / /, $ulazSplit[4];
		@zakljucaoVrijeme = split /:/, $zakljucao[1];
		if ($termin[0] ne $zakljucao[0] || ($terminVrijeme[0] < $zakljucaoVrijeme[0] && $terminVrijeme[1] < $zakljucaoVrijeme[1]) || $terminVrijeme[0] + 1 < $zakljucaoVrijeme[0]) {
		#ako nije isti datum, ako je broj sati i minuta za 1 veci od pocetka i ako je broj sati za 2 veci od pocetka
		print $ulazSplit[0] . " " . $ulazSplit[2] . " " . $ulazSplit[1] . " - PROBLEM: " . $termin[0] . " " . $termin[1] . " --> " . $ulazSplit[4];
		}
	}

}