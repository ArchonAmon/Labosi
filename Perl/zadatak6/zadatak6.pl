#ako je rijec dulja ili jednaka zadanom argumentu onda se stavlja u listu i svaki put kad se dode do nove rijeci usporeduje se ima li ta rijec vec u listi
use open ':locale';
use locale;

if (@ARGV == 0) {
	die "Treba postojati barem jedan argument i to duljina prefiksa!\n";
}

$duljina = pop (@ARGV);			#uzima se zadnji argument
while ($ulaz = <>) {

	@temp = split /\s+/, $ulaz;
	
	foreach $tren (@temp) {
		$tren =~ s/[[:punct:]]//g;					#makne se interpunkcija
		if (length($tren) >= $duljina) {
			@rijeci{lc (substr($tren, 0, $duljina))}++;					#ignoriraju se velika i mala slova
		}
	}

}

foreach $temp (sort keys %rijeci) {
if ($rijeci{$temp}) {
print "$temp : $rijeci{$temp} \n";
}
}
