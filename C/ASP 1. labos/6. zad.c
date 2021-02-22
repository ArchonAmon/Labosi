#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <malloc.h>

char *ostaviSlova(char *niz) {
	char *p;
	int i;
	int brojac = 0;
	for (i = 0; niz[i] != 0; i++) {
		if ((niz [i] >= 'A' && niz [i] <= 'Z')  || (niz[i] >= 'a' && niz[i] <= 'z')  || (niz[i] >= '0' && niz[i] <= '9')) {
			brojac++;
		}
	}

	p = (char *)malloc(brojac * sizeof(char));
	for (i = 0; niz[i] != 0; i++) {
		if ((niz[i] >= 'A' && niz[i] <= 'Z') || (niz[i] >= 'a' && niz[i] <= 'z') || (niz[i] >= '0' && niz[i] <= '9')) {
			p[i] = niz[i];
			printf ("%c", p[i]);
		}
	}

	return &p[0];
}


int main(void) {
	char *niz2;
	char niz[] = "asp12_i_ASP13";
	niz2 = ostaviSlova(&niz[0]);
	printf("%c", niz2[0]);
	scanf("%c", &niz2[0]);
	return 0;
}
