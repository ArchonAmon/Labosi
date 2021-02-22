print "Upisite niz znakova:\n";
chomp($text = <STDIN>);

print "Upisite broj ponavljanja\n";
chomp($ponavljanja = <STDIN>);

print (($text . "\n") x $ponavljanja);
