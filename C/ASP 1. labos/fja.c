#include "fja.h"
#include <stdio.h>
void ispis(float *polje, int n) {

ispis(&polje[n-1], n-1);
if (polje[n]<0) {
printf ("%f",polje[n]);
}
return;
}