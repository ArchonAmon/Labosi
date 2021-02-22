#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define max 100

struct at {
	int broj;
	struct at *sljed;
};

typedef struct at atom;
typedef struct {
	atom *vrh;
} stog;

void inic(stog *stog) {
	stog->vrh = NULL;
}

int dodaj(stog *stog, int broj) {
	atom *pom;
	if ((pom = (atom *) malloc (sizeof(atom))) != NULL) {
		pom->broj = broj;
		pom->sljed = stog->vrh;
		stog->vrh = pom;
		return 1;
	}
	else return 0;
}



int main(void) {
	int brojac = 0;
	stog stog;
	int broj;

	inic(&stog);

	srand((unsigned)time(NULL));

	do {
		broj = rand() % 101;
		dodaj(&stog, broj);
		brojac++;
	} while (brojac < 101);

	scanf("%d", &brojac);
	return 0;
}
