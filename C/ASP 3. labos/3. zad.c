#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>

struct st {	
double broj;	
struct st *sljed;};
typedef struct st cvor;

typedef struct {	
cvor *ulaz, *izlaz;
} Red;

void init_red(Red *red) {	
red->ulaz = NULL;	
red->izlaz = NULL;
}

int dodajURed(double broj, Red *red) {
	cvor *pom;
	if (pom = (cvor *)malloc(sizeof(cvor))) {
		pom->broj = broj;
		pom->sljed = NULL;
		if (red->izlaz == NULL) red->izlaz = pom;		//ako je red bio prazan
		else (red->ulaz)->sljed = pom;					//inace stavi na kraj			bread 'n' butter
		red->ulaz = pom;								//i zapamti zadnjeg
		return 1;
	}
	return 0;
}

int main(void) {
	Red red;
	int brojac = 0;
	double polje[]{ 1,2,3,4,5 };

	init_red(&red);

	do {
		dodajURed(polje[brojac], &red);
		printf("\nBroj %lf je upravo dodan u red", polje[brojac]);
		brojac++;
	} while (brojac != 5);
	scanf("%d", &brojac);
	return 0;
}