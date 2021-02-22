#include "stdafx.h"
#include <stdio.h>
#include <stdlib.h>

struct st {
	float broj;
	struct st *sljed;
};
typedef struct st cvor;


int upis(cvor **glava, cvor **rep, float broj) {
	cvor *pom;
	if ((pom = (cvor *)malloc(sizeof(cvor))) == NULL) return 0;


	return 1;
}

int main(void) {
	cvor *glava = NULL;
	cvor *rep = NULL;
	float polje[] = { 1, 2, 3, 4, 5, 6 };
	int brojac = 0;
	do {
		upis(&glava, &rep, polje[brojac]);
		brojac++;
	} while (brojac != 6);
	return 0;
}