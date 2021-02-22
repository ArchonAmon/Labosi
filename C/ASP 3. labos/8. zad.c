#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

#define m 128

struct st {
	int broj;
	struct st *sljed;
};
typedef struct st atom;



unsigned long int adresa(int k) {
	unsigned long long A = 2654435769;
	int pomak = 25;
	return ((A*k) >> pomak) % m;
}


int dodaj(atom **hash, int broj) {
	atom *pom;
	int adr;
	pom = (atom*)malloc(sizeof(atom));
	if (pom == NULL) return 0;
	adr = adresa(broj);
	pom->sljed = hash[adr];
	pom->broj = broj;
	hash[adr] = pom;
	return 1;
}






int main(void) {
	int i;
	atom *hash[m];
	atom *clan;

	srand((unsigned)time(NULL));

	for (i = 0; i < m; i++) hash[i] = NULL;
	for (i = 0; i < 129; i++) {
		dodaj(hash, rand());			//upis
	}

	for (i = 0; i < m; i++) {
		clan = hash[i];
		while (clan != 0) {
			printf("%d ", clan->broj);
			clan = clan->sljed;
		}
		printf("\n");
	}

	scanf("%d", &i);

	return 0;
}
