#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define max 100
typedef struct {
	int vrh, polje[max];
} stog;



int dodaj(stog *stog, int broj) {
	if (stog->vrh >= max - 1) return 0;
	stog->vrh++;
	stog->polje[stog->vrh] = broj;
	return 1;
}


void init_stog(stog *stog) {
	stog->vrh = -1;
}


int main(void) {
	int brojac = 0;
	stog stog;
	int broj;

	srand((unsigned)time(NULL));
	init_stog(&stog);

	do {
		broj = rand() % 100;
		dodaj(&stog, broj);
		brojac++;
	} while (brojac != 101);

	for (brojac = 0; brojac < max; brojac++)
	printf("%d ", stog.polje[brojac]);

	scanf("%d", &brojac);

	return 0;
}
