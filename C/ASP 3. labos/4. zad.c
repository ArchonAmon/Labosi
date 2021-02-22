
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

struct st {
	int broj;
	struct st *sljed;
};
typedef struct st cvor;

typedef struct {
	cvor *ulaz, *izlaz;
} Red;


void init_red(Red *red) {	red->ulaz = NULL;	red->izlaz = NULL;}



int dodajURed(int broj, Red *red) {
	cvor *pom;
	if (pom = (cvor *)malloc(sizeof(cvor))) {
		pom->broj = broj;
		pom->sljed = NULL;
		if (red->izlaz == NULL) red->izlaz = pom;
		else (red->ulaz)->sljed = pom;
		red->ulaz = pom;
		return 1;
	}
	return 0;
}



int poljeURed(int polje[], int n, Red *red) {
	int smijem = 1;
	if (n > 0) smijem = poljeURed(polje + 1, n - 1, red);	//ide polje + 1, a ne polje [n]
	else return 1;
	if (smijem) {
		printf("%d ", polje[0]);				//ide polje[0], a ne polje[m]
	if (dodajURed(polje[n], red) == 0) return 0;
	else return 1;

	}
	else return 0;
}




int main(void) {
	Red red;
	int brojac = 0;
	int polje[10];
	srand((unsigned)time(NULL));
	do {
		polje[brojac] = rand() % 10 + 1;
		brojac++;
	} while (brojac < 10);

	init_red(&red);

	poljeURed(&polje[0], 10, &red);

	scanf("%d", &brojac);
	return 0;
}
