if (@ARGV > 1) {
	die "Treba biti samo jedan argument\n";
}

$broj = 1;
$broj2 = 1;
$prviRed;
while ($ulaz = <>) {

	if ($ulaz =~ m/<h1>\w+</) {				#1 red
	
	@temp = split />/, $ulaz;
	@temp2 = split /</, $temp[1];
	
	print $broj . ". " . $temp2[0] . "\n";
		
	$broj++;
	$broj2 = 1;
	}
	
	elsif ($ulaz =~ m/<h2>\w+</) {					#1 red
	
	@temp = split />/, $ulaz;
	@temp2 = split /</, $temp[1];
	
	print "      " . $broj . ". " . $broj2 . "." . $temp2[0] . "\n";
		
	$broj2++;
	}

	elsif ($ulaz =~ /^<h1>/) {			#2 reda, prvi red, kako pocinje
	
	$prviRed = $ulaz;
	
	}
	
	elsif ($ulaz =~ /^<h2>/) {			#2 reda, prvi red, kako pocinje
	
	$prviRed = $ulaz;
	
	}
	
	elsif ($ulaz =~ /<h1>$/) {			#2 reda, drugi red, kako zavrsava
	
	$naslov = $prviRed . $ulaz;
	
	@temp = split />/, $naslov;
	@temp2 = split /</, $temp[1];	
		
	print $broj . "      ." . $broj2 . "." . $temp2[0] . "\n";
		
	$broj++;
	$broj2 = 1;
	}
	
	elsif ($ulaz =~ /<h2>$/) {			#2 reda, drugi red, kako zavrsava
	
	$naslov = $prviRed . $ulaz;
	
	@temp = split />/, $naslov;
	@temp2 = split /</, $temp[1];	
		
	print $broj . "      ." . $broj2 . "." . $temp2[0] . "\n";
		
	$broj2++;
	}


}