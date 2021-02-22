x#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>

struct st {
	float broj;
	struct st *sljed;
};
typedef struct st cvor;

int ispis(cvor *glava, int n) {
	int i;
	for (i = 0; i < n; i++) {
		printf("%f ", glava->broj);
		glava = glava->sljed;
	}
	return 1;
}

int upis(cvor **glava, cvor **rep, float broj) {
	cvor *pom;
	if ((pom = (cvor *)malloc(sizeof(cvor))) == NULL) return 0;
	pom->broj = broj;
	pom->sljed = NULL;
	if (*glava == NULL)
	{
		*glava = pom;
		*rep = pom;

	}
	else
	{
		(*rep)->sljed = pom;
		*rep = pom;
	}
	return 1;
}

int brisi(cvor **glava, int n) {
	int i;
	cvor *pom;
	for (i = 0; i < n; i++) {
		if (*glava) {
			pom = *glava;
			*glava = ((*glava)->sljed);
			free (pom);
		}
	}
	return 1;
}

int main(void) {
	cvor *glava = NULL;
	cvor *rep = NULL;
	cvor *pom;
	float polje[] = { 1, 2, 3, 4, 5, 6 };
	int i;
	int brojac = 0;
	do {
		upis(&glava, &rep, polje[brojac]);
		brojac++;
	} while (brojac != 6);

	ispis(glava, brojac);
	
	do {
		brisi(&glava, brojac);
		brojac--;
	} while (brojac != 0);

	ispis(glava, brojac);

	scanf("%d", &brojac);
	return 0;
}